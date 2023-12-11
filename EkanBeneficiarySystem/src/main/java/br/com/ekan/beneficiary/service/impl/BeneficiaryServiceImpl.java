package br.com.ekan.beneficiary.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ekan.beneficiary.dto.BeneficiaryDTO;
import br.com.ekan.beneficiary.dto.BeneficiaryDocumentDTO;
import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.keys.MessageKeys;
import br.com.ekan.beneficiary.repository.BeneficiaryRepository;
import br.com.ekan.beneficiary.repository.DocumentRepository;
import br.com.ekan.beneficiary.service.BeneficiaryService;
import br.com.ekan.beneficiary.utils.GenericUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BeneficiaryDocumentDTO saveBeneficiaryWithDocument(BeneficiaryDocumentDTO beneficiaryDocumentDTO) {
		log.info("[BeneficiaryServiceImpl] - (saveBeneficiaryWithDocument) : Iniciando a gravacao do beneficiario e seus documentos!");
		validateBeneficiaryWithDocument(beneficiaryDocumentDTO);
		BeneficiaryDTO beneficiaryDto = saveOrUpdate(modelMapper.map(beneficiaryDocumentDTO, BeneficiaryDTO.class));
		List<Document> lsDocument = new ArrayList<>();
		beneficiaryDocumentDTO.getDocuments().stream().forEach(doc -> {
			doc.setCreateDate(LocalDateTime.now());
			doc.setBeneficiaryId(beneficiaryDto.getId());
			lsDocument.add(modelMapper.map(doc, Document.class));
		});
		documentRepository.saveAll(lsDocument);
		beneficiaryDocumentDTO.setId(beneficiaryDto.getId());
		beneficiaryDocumentDTO.setCreateDate(beneficiaryDto.getCreateDate());
		return beneficiaryDocumentDTO; 
	}
	
	private void validateBeneficiaryWithDocument(BeneficiaryDocumentDTO beneficiaryDocumentDTO) {
		log.info("[BeneficiaryServiceImpl] - (validateBeneficiaryWithDocument) : Iniciando a validação do beneficiario e documentos!");
		if (null == beneficiaryDocumentDTO.getDocuments() || 1 > beneficiaryDocumentDTO.getDocuments().size()) {
			log.error("[BeneficiaryServiceImpl] - (validateBeneficiaryWithDocument) : ", StringUtils.join(MessageKeys.VALIDATION_SERVICE_BENEFICIARY_CODE_0001, 
					MessageKeys.STR_HIFEN_BETWEEN_SPACES, 
					MessageKeys.VALIDATION_SERVICE_BENEFICIARY_CODE_0001));
			throw new ValidationException(MessageKeys.VALIDATION_SERVICE_BENEFICIARY_CODE_0001);
		}
		log.info("[BeneficiaryServiceImpl] - (validateBeneficiaryWithDocument) : Encerrando a validação do beneficiario e documentos!");
	}
	
	@Override
	public BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiaryDTO) {
		log.info("[BeneficiaryServiceImpl] - (updateBeneficiary) : Iniciando a atualizacao do beneficiario!");
		validateBeneficiaryWithId(beneficiaryDTO);
		return saveOrUpdate(beneficiaryDTO); 
	}
	
	private void validateBeneficiaryWithId(BeneficiaryDTO beneficiaryDTO) {
		log.info("[BeneficiaryServiceImpl] - (validateBeneficiaryWithId) : Iniciando a validacao do beneficiario!");
		if (null == beneficiaryDTO.getId()) {
			log.error("[BeneficiaryServiceImpl] - (validateBeneficiaryWithId) : ", StringUtils.join(MessageKeys.VALIDATION_SERVICE_BENEFICIARY_CODE_0002, 
					MessageKeys.STR_HIFEN_BETWEEN_SPACES, 
					MessageKeys.VALIDATION_SERVICE_BENEFICIARY_CODE_0002));
			throw new ValidationException(MessageKeys.VALIDATION_SERVICE_BENEFICIARY_CODE_0002);
		}
		log.info("[BeneficiaryServiceImpl] - (validateBeneficiaryWithId) : Encerrando a validacao do beneficiario!");
	}
	
	private BeneficiaryDTO saveOrUpdate(BeneficiaryDTO beneficiaryDTO) {
		log.info("[BeneficiaryServiceImpl] - (saveOrUpdate) : Iniciando a gravacao ou atualizacao do beneficiario!");
		if(null != beneficiaryDTO.getId()) {
			beneficiaryDTO.setUpdateDate(LocalDateTime.now());
		} else {
			beneficiaryDTO.setCreateDate(LocalDateTime.now());
		}
		return modelMapper.map(beneficiaryRepository.save(modelMapper.map(beneficiaryDTO, Beneficiary.class)), BeneficiaryDTO.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDTO> findAll(String page, String limit) {
		log.info("[BeneficiaryServiceImpl] - (findAll) : Iniciando a busca de todos os beneficiarios!");
		Page<Beneficiary> pgBeneficiary = beneficiaryRepository.findAll(GenericUtils.createPageable(Integer.valueOf(page), Integer.valueOf(limit)));
		return modelMapper.map(pgBeneficiary.getContent(), ArrayList.class);
	}
	
	@Override
	public void delete(Long id) {
		log.info("[BeneficiaryServiceImpl] - (delete) : Iniciando a exclusao do beneficiario pelo ID!");
		beneficiaryRepository.deleteById(id);
		log.info("[BeneficiaryServiceImpl] - (delete) : Encerrando a exclusao do beneficiario pelo ID!");
	}
}
