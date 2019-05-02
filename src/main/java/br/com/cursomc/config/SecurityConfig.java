package br.com.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.cursomc.security.JWTAuthenticationFilter;
import br.com.cursomc.security.JWTAuthorizationFilter;
import br.com.cursomc.security.JWTUtil;

/**
 * Classe de configuração de segurança
 *
 * @author Flavio Solci
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/** Pega os profiles ativos do env */
	@Autowired
	private Environment environment;

	/** Autenticação do usuário */
	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private JWTUtil jwtUtil;

	/** Páginas acessíveis */
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	/** Páginas acessíveis somente leitura */
	private static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/categorias/**", "/clientes/**" };

	/**
	 * Configura acesso as páginas
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// se for um profile de test, libera o h2-console
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, detailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	/**
	 * Configura o acesso dos usuários
	 */
	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	/**
	 * "By default a newly created CorsConfiguration does not permit any
	 * cross-origin requests and must be configured explicitly to indicate what
	 * should be allowed."
	 *
	 * "Adds a CorsFilter to be used. If a bean by the name of corsFilter is
	 * provided, that CorsFilter is used. Else if corsConfigurationSource is
	 * defined, then that CorsConfiguration is used. Otherwise, if Spring MVC is on
	 * the classpath a HandlerMappingIntrospector is used."
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	/**
	 * @return Password encoder
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
