package br.com.ekan.beneficiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekan.beneficiary.dto.BeneficiaryDTO;
import br.com.ekan.beneficiary.dto.BeneficiaryDocumentDTO;
import br.com.ekan.beneficiary.keys.RequestKeys;
import br.com.ekan.beneficiary.service.BeneficiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = RequestKeys.PROJECT_VERSION)
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping(value = RequestKeys.BENEFICIARY_DOCUMENTS, produces="application/json")
	@Operation(
		      summary = "Grava um beneficiário",
		      description = "Cadastrar um beneficiário junto com seus documentos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Gravou o beneficiário"),
			@ApiResponse(responseCode = "403", description = "Sem permissão para acesso"),
			@ApiResponse(responseCode = "500", description = "Um erro aconteceu")
			
	})
	BeneficiaryDocumentDTO saveBeneficiaryWithDocument(@Valid @RequestBody BeneficiaryDocumentDTO beneficiaryDocumentDTO) {
		log.info("[BeneficiaryController] - (saveBeneficiaryWithDocument) : Iniciando a gravacao do beneficiario e seus documentos!");
		return beneficiaryService.saveBeneficiaryWithDocument(beneficiaryDocumentDTO);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = RequestKeys.BENEFICIARY, produces="application/json")
	@Operation(
		      summary = "Edita um beneficiário",
		      description = "Atualizar os dados cadastrais de um beneficiário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Editou o beneficiário"),
			@ApiResponse(responseCode = "403", description = "Sem permissão para acesso"),
			@ApiResponse(responseCode = "500", description = "Um erro aconteceu")
			
	})
	BeneficiaryDTO updateBeneficiary(@Valid @RequestBody BeneficiaryDTO beneficiaryDTO) {
		log.info("[BeneficiaryController] - (updateBeneficiary) : Iniciando a atualizacao do beneficiario!");
		return beneficiaryService.updateBeneficiary(beneficiaryDTO);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = RequestKeys.BENEFICIARIES, produces="application/json")
	@Operation(
		      summary = "Lista os beneficiários",
		      description = "Listar todos os beneficiários cadastrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de beneficiários"),
			@ApiResponse(responseCode = "403", description = "Sem permissão para acesso"),
			@ApiResponse(responseCode = "500", description = "Um erro aconteceu")
			
	})
	List<BeneficiaryDTO> findAll(@RequestParam(value = "_page", required = true) String page,
			@RequestParam(value = "_limit", required = true) String limit) {
		log.info("[BeneficiaryController] - (findAll) : Iniciando a busca de todos os beneficiarios!");
		return beneficiaryService.findAll(page, limit);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(value = RequestKeys.BENEFICIARY_BY_ID, produces="application/json")
	@Operation(
		      summary = "Exclui um beneficiário",
		      description = "Remover um beneficiário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Excluiu o beneficiário"),
			@ApiResponse(responseCode = "403", description = "Sem permissão para acesso"),
			@ApiResponse(responseCode = "500", description = "Um erro aconteceu")
			
	})
	void delete(@PathVariable Long id) {
		log.info("[BeneficiaryController] - (delete) : Iniciando a exclusao do beneficiario pelo ID! ID = {}", id);
		beneficiaryService.delete(id);
		log.info("[BeneficiaryController] - (delete) : Encerrando a exclusao do beneficiario pelo ID! ID = {}", id);
	}
}
