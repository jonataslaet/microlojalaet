package br.com.jonataslaet.microlojalaet.services.validations.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodificaStringNumaListaDeInteiros(String s){
		return Arrays.asList(s.split(",")).stream().map(c -> Integer.parseInt(c)).collect(Collectors.toList());
	}
}
