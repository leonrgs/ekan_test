package br.com.ekan.beneficiary.keys;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestKeys {
	
	public static final String PROJECT_VERSION = "/v1";
	
	/*
	 * Beneficiary endpoints
	 */
	public static final String BENEFICIARY = "/beneficiary";
	public static final String BENEFICIARY_BY_ID = "/beneficiary/{id}";
	public static final String BENEFICIARIES = "/beneficiaries";
	public static final String BENEFICIARY_DOCUMENTS = "/beneficiary/documents";
	
	/*
	 * Document endpoints
	 */
	public static final String DOCUMENTS_BY_BENEFICIARY_ID = "/document/befeneficiary/{id}";

}
