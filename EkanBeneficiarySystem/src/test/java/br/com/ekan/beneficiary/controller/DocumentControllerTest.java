package br.com.ekan.beneficiary.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;

import br.com.ekan.beneficiary.dto.DocumentDTO;
import br.com.ekan.beneficiary.enumerator.DocumentEnum;
import br.com.ekan.beneficiary.keys.TestUtilKeys;
import br.com.ekan.beneficiary.service.impl.DocumentServiceImpl;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {
	
	@InjectMocks
	DocumentController documentController;
	
	@Mock
	DocumentServiceImpl documentService;
	
	@Test
	public void should_equals_when_findAllDocumentByBeneficiaryId() {
		doReturn(Lists.newArrayList(createDocumentDTO(true))).when(documentService).findAllDocumentByBeneficiaryId(anyLong());
		var documentos = documentController.findAllDocumentByBeneficiaryId(TestUtilKeys.DEFAULT_ID);
		assertEquals(TestUtilKeys.DEFAULT_ID, documentos.get(TestUtilKeys.INT_ZERO).getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_findAllDocumentByBeneficiaryId() {
		doThrow(createRestClientException()).when(documentService).findAllDocumentByBeneficiaryId(anyLong());
		assertThrows(RestClientException.class, () -> documentController.findAllDocumentByBeneficiaryId(TestUtilKeys.DEFAULT_ID), TestUtilKeys.STR_ASSERT_THROWS);
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
