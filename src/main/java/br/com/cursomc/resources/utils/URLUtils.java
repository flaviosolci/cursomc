package br.com.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

/**
 * Classe com métodos utilitários para manusear URL e seus parâmetros
 *
 * @author Flavio Solci
 *
 */
@UtilityClass
public class URLUtils {

	/**
	 * Recebe um parâmetro de URL e faz o decode dele
	 *
	 * @param param parâmetro para ser decodificado
	 * @return parâmetro decodificado ou vazio se algum problema de Encoding for
	 *         detectado
	 *
	 * @see URLDecoder
	 */
	public static String decodeParam(final String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			return "";
		}

	}

	/**
	 * Receive uma string com vários parâmetros, separados por virgula e retorna uma
	 * List de Integer
	 *
	 * @param parametros List da parâmetros. Todos os parâmetros devem ser valores
	 *                   inteiros, caso contrário uma exceção será lancada
	 * @return Lista de parâmetros do tipo Integer ou null, se o input for null ou
	 *         vazio
	 */
	public static List<Integer> decodeParametersIntoList(final String parametros) {
		final String[] splitParam = StringUtils.split(parametros, ',');
		List<Integer> list = null;
		if (splitParam != null) {
			list = new ArrayList<>(splitParam.length);
			for (final String param : splitParam) {
				if (!StringUtils.isNumeric(param)) {
					throw new IllegalArgumentException("Lista parametros " + parametros + " é inválida");
				}

				list.add(Integer.parseInt(param));
			}
		}

		return list;

	}

}
