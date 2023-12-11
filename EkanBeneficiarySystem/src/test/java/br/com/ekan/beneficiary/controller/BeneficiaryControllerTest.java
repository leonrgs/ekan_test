package br.com.ekan.beneficiary.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;

import br.com.ekan.beneficiary.dto.BeneficiaryDTO;
import br.com.ekan.beneficiary.dto.BeneficiaryDocumentDTO;
import br.com.ekan.beneficiary.dto.DocumentDTO;
import br.com.ekan.beneficiary.enumerator.DocumentEnum;
import br.com.ekan.beneficiary.keys.TestUtilKeys;
import br.com.ekan.beneficiary.service.impl.BeneficiaryServiceImpl;

@ExtendWith(MockitoExtension.class)
class BeneficiaryControllerTest {
	
	@InjectMocks
	BeneficiaryController beneficiaryController;
	
	@Mock
	BeneficiaryServiceImpl beneficiaryService;
	
	@Test
	public void should_equals_when_saveBeneficiaryWithDocument() {
		doReturn(createBeneficiaryDocumentDTO(true, true)).when(beneficiaryService).saveBeneficiaryWithDocument(any());
		var retorno = beneficiaryController.saveBeneficiaryWithDocument(createBeneficiaryDocumentDTO(false, true));
		assertEquals(TestUtilKeys.DEFAULT_ID, retorno.getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_saveBeneficiaryWithDocument() {
		doThrow(createRestClientException()).when(beneficiaryService).saveBeneficiaryWithDocument(any());
		assertThrows(RestClientException.class, () -> beneficiaryController.saveBeneficiaryWithDocument(createBeneficiaryDocumentDTO(false, true)), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_equals_when_updateBeneficiary() {
		doReturn(createBeneficiaryDTO(true)).when(beneficiaryService).updateBeneficiary(any());
		var retorno = beneficiaryController.updateBeneficiary(createBeneficiaryDTO(true));
		assertEquals(TestUtilKeys.DEFAULT_ID, retorno.getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_updateBeneficiary() {
		doThrow(createRestClientException()).when(beneficiaryService).updateBeneficiary(any());
		assertThrows(RestClientException.class, () -> beneficiaryController.updateBeneficiary(createBeneficiaryDTO(true)), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_equals_when_findAll() {
		doReturn(Lists.newArrayList(createBeneficiaryDTO(true))).when(beneficiaryService).findAll(anyString(),anyString());
		var retorno = beneficiaryController.findAll(String.valueOf(TestUtilKeys.INT_ZERO), String.valueOf(TestUtilKeys.INT_THIRTY));
		assertEquals(TestUtilKeys.DEFAULT_ID, retorno.get(TestUtilKeys.INT_ZERO).getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_findAll() {
		doThrow(createRestClientException()).when(beneficiaryService).findAll(anyString(),anyString());
		assertThrows(RestClientException.class, () -> beneficiaryController.findAll(String.valueOf(TestUtilKeys.INT_ZERO), String.valueOf(TestUtilKeys.INT_THIRTY)), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_doesntTrhows_when_delete() {
		doNothing().when(beneficiaryService).delete(any());
		assertDoesNotThrow(() -> beneficiaryController.delete(TestUtilKeys.DEFAULT_ID), TestUtilKeys.STR_ASSERT_NOT_THROWS);
	}
	
	@Test
	public void should_throws_when_delete() {
		doThrow(createRestClientException()).when(beneficiaryService).delete(any());
		assertThrows(RestClientException.class, () -> beneficiaryController.delete(TestUtilKeys.DEFAULT_ID), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	private BeneficiaryDTO createBeneficiaryDTO(boolean withId) {
		return BeneficiaryDTO.builder()
				.id(withId ? TestUtilKeys.DEFAULT_ID : null)
				.name(TestUtilKeys.STR_DEFAULT_BENEFICIARY_NAME)
				.birthday(TestUtilKeys.LD_DEFAULT_BIRTHDAY)
				.phone(TestUtilKeys.STR_DEFAULT_CELLPHONE_DDD_UNMASKED)
				.build();
	}
	
	private BeneficiaryDocumentDTO createBeneficiaryDocumentDTO(boolean withId, boolean withDocument) {
		return BeneficiaryDocumentDTO.builder()
				.id(withId ? TestUtilKeys.DEFAULT_ID : null)
				.name(TestUtilKeys.STR_DEFAULT_BENEFICIARY_NAME)
				.birthday(TestUtilKeys.LD_DEFAULT_BIRTHDAY)
				.phone(TestUtilKeys.STR_DEFAULT_CELLPHONE_DDD_UNMASKED)
				.documents(withDocument ? Lists.newArrayList(createDocumentDTO(withId)) : null)
				.build();
	}
	
	private DocumentDTO createDocumentDTO(boolean withId) {
		return DocumentDTO.builder()
				.id(withId ? TestUtilKeys.DEFAULT_ID : null)
				.type(DocumentEnum.RG.getType())
				.description(TestUtilKeys.STR_DEFAULT_DOCUMENT_RG_NUMBER_UNMASKED)
				.build();
	}
	
	private RestClientException createRestClientException() {
		return new RestClientException(TestUtilKeys.STR_DEFAULT_MSG_ERROR_REST_CLIENT);
	}
	
}
