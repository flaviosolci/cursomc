package br.com.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Configurações do amazon s3
 *
 * @author Flavio Solci
 *
 */
@Configuration
public class S3Config {

	/** From application.properties */
	@Value("${aws.access_key_id}")
	private String acessKeyId;

	/** From application.properties */
	@Value("${aws.secret_access_key}")
	private String secretAcessKey;

	/** From application.properties */
	@Value("${s3.region}")
	private String region;

	/**
	 * Conecta com o amazon s3
	 *
	 * @return conexão
	 */
	@Bean
	public AmazonS3 s3client() {
		final BasicAWSCredentials awsCred = new BasicAWSCredentials(acessKeyId, secretAcessKey);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
	}

}
