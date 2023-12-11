package br.com.ekan.beneficiary.service;

import java.util.List;

import br.com.ekan.beneficiary.dto.BeneficiaryDTO;
import br.com.ekan.beneficiary.dto.BeneficiaryDocumentDTO;

public interface BeneficiaryService {

	BeneficiaryDocumentDTO saveBeneficiaryWithDocument(BeneficiaryDocumentDTO beneficiaryDocumentDTO);
	BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiaryDTO);
	List<BeneficiaryDTO> findAll(String page, String limit);
	void delete(Long id);
	
}
