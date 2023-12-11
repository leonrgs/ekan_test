package br.com.ekan.beneficiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekan.beneficiary.dto.DocumentDTO;
import br.com.ekan.beneficiary.keys.RequestKeys;
import br.com.ekan.beneficiary.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = RequestKeys.PROJECT_VERSION)
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = RequestKeys.DOCUMENTS_BY_BENEFICIARY_ID, produces="application/json")
	@Operation(
		      summary = "Busca dos documentos",
		      description = "Listar todos os documentos de um beneficiário a partir de seu id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de documentos do beneficiário"),
			@ApiResponse(responseCode = "403", description = "Sem permissão para acesso"),
			@ApiResponse(responseCode = "500", description = "Um erro aconteceu")
			
	})
	List<DocumentDTO> findAllDocumentByBeneficiaryId(@PathVariable Long id) {
		log.info("[DocumentController] - (findAllDocumentByBeneficiaryId) : Iniciando a busca dos documentos pelo id do beneficiario! ID = {} ", id);
		return documentService.findAllDocumentByBeneficiaryId(id);
	}
}
