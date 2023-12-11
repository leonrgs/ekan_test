package br.com.ekan.beneficiary.service;

import java.util.List;

import br.com.ekan.beneficiary.dto.DocumentDTO;

public interface DocumentService {

	List<DocumentDTO> findAllDocumentByBeneficiaryId(Long id);
}
