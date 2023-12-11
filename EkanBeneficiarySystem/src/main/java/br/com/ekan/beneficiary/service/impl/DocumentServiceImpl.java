package br.com.ekan.beneficiary.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiary.dto.DocumentDTO;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.repository.DocumentRepository;
import br.com.ekan.beneficiary.service.DocumentService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentDTO> findAllDocumentByBeneficiaryId(Long id) {
		log.info("[DocumentServiceImpl] - (findAllDocumentByBeneficiaryId) : Iniciando a busca dos documentos pelo id do beneficiario! ID = {}", id);
		List<DocumentDTO> lsRetorno = new ArrayList<>();
		try {
			List<Document> lsDocument = documentRepository.findAllDocumentByBeneficiaryId(id);
			lsRetorno = (List<DocumentDTO>) modelMapper.map(lsDocument, ArrayList.class);
			log.info("[DocumentServiceImpl] - (findAllDocumentByBeneficiaryId) : Encerrando a busca dos documentos pelo id do beneficiario! ID = {}", id);
		} catch (Exception e) {
			log.error("[DocumentServiceImpl] - (findAllDocumentByBeneficiaryId) : Erro ao buscar os documentos pelo id do beneficiario!", e);
		}
		log.info("[DocumentServiceImpl] - (findAllDocumentByBeneficiaryId) : Retornando a busca dos documentos pelo id do beneficiario! ID = {}", id);
		return lsRetorno;
	}
}
