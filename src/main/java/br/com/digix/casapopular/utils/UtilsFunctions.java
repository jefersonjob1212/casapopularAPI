package br.com.digix.casapopular.utils;

import java.lang.reflect.Method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.digix.casapopular.exception.CampoVazioOuNuloException;

public class UtilsFunctions {

	public static String convertToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JavaTimeModule module = new JavaTimeModule();
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(object);
	}

	public static boolean ehValidoCpf(String cpf) {
		if(cpf.equals("11111111111") ||
				cpf.equals("22222222222") ||
				cpf.equals("33333333333") ||
				cpf.equals("44444444444") ||
				cpf.equals("55555555555") ||
				cpf.equals("66666666666") ||
				cpf.equals("77777777777") ||
				cpf.equals("88888888888") ||
				cpf.equals("99999999999") ||
				cpf.equals("00000000000"))
			return false;

		char d10, d11;

		d10 = returnDigito(cpf, 10);
		d11 = returnDigito(cpf, 11);

		return d10 == cpf.charAt(9) && d11 == cpf.charAt(10);
	}

	private static char returnDigito(String cpf, int digito) {
		int sm, i, r, num, peso;

		sm = 0;
		peso = digito;
		i = 0;
		for (i=0; i < (digito - 1); i++) {
			num = (int)cpf.charAt(i) - 48;
			sm += num * peso;
			peso--;		
		}

		r = 11 - (sm % 11);
		return r == 10 || r == 11 ? '0' : (char)(r + 48);
	}

	public static String somenteNumeros(String numero) {
		return numero.replaceAll("[^0-9]", "");
	}

	public static void validarObjeto(Object dto) throws Exception {
		Class<?> clazz = dto.getClass();

		for (Method method : clazz.getDeclaredMethods()) {
			String methodName = method.getName().substring(3);
			if(!methodName.equalsIgnoreCase("id") && method.getName().startsWith("get")) {
				Object value = method.invoke(dto);
				if(value == null) {
					methodName = methodName.equalsIgnoreCase("valorRenda") ? "valor renda"
							    : (methodName.equalsIgnoreCase("dataNascimento") 
							    	? "data nascimento" : methodName);
					
					throw new CampoVazioOuNuloException(methodName);
				}
			}
		}
	}	

}
