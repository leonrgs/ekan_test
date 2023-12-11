package br.com.ekan.beneficiary.keys;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtilKeys {
	
	public static final Long DEFAULT_ID	 = 1l;
	public static final int INT_THIRTY = 30;
	public static final int INT_ZERO = 0;
	public static final String STR_DEFAULT_BENEFICIARY_NAME = "Leonardo Santos de Andrade";
	public static final LocalDate LD_DEFAULT_BIRTHDAY = LocalDate.now().minusYears(INT_THIRTY);
	public static final String STR_DEFAULT_CELLPHONE_DDD_UNMASKED = "11999999999";
	public static final String STR_DEFAULT_DOCUMENT_RG_NUMBER_UNMASKED = "333333333";
	public static final String STR_DEFAULT_MSG_ERROR_REST_CLIENT = "Erro ao executar a chamada!";
	
	public static final String STR_ASSERT_EQUALS = "Assert equals!";
	public static final String STR_ASSERT_THROWS = "Assert throws!";
	public static final String STR_ASSERT_NOT_THROWS = "Assert not throws!";
	
}