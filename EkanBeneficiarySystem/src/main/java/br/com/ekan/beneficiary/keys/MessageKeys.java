package br.com.ekan.beneficiary.keys;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageKeys {
	
	public static final String STR_HIFEN_BETWEEN_SPACES = " - ";
	
	public static final String VALIDATION_SERVICE_BENEFICIARY_CODE_0001 = "BEN_0001";
	public static final String VALIDATION_SERVICE_BENEFICIARY_MSG_0001 =  "Nenhum documento foi enviado!";
	
	public static final String VALIDATION_SERVICE_BENEFICIARY_CODE_0002 = "BEN_0002";
	public static final String VALIDATION_SERVICE_BENEFICIARY_MSG_0002 =  "O campo id foi enviado!";

}
