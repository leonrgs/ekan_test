package br.com.ekan.beneficiary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestClientException;

import br.com.ekan.beneficiary.dto.DocumentDTO;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.enumerator.DocumentEnum;
import br.com.ekan.beneficiary.keys.TestUtilKeys;
import br.com.ekan.beneficiary.repository.DocumentRepository;
import br.com.ekan.beneficiary.service.impl.DocumentServiceImpl;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {
	
	@InjectMocks
	DocumentServiceImpl documentService;
	
	@Mock
	DocumentRepository documentRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@Test
	public void should_equals_when_findAllDocumentByBeneficiaryId() {
		List<Document> lsDocument = Lists.newArrayList(createDocument(true));
		List<DocumentDTO> lsDocumentDTO = Lists.newArrayList(createDocumentDTO(true));
		doReturn(lsDocument).when(documentRepository).findAllDocumentByBeneficiaryId(anyLong());
		doReturn(lsDocumentDTO).when(modelMapper).map(lsDocument, ArrayList.class);
		var documentos = documentService.findAllDocumentByBeneficiaryId(TestUtilKeys.DEFAULT_ID);
		assertEquals(TestUtilKeys.DEFAULT_ID, documentos.get(TestUtilKeys.INT_ZERO).getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_empty_when_findAllDocumentByBeneficiaryId_trhows() {
		doThrow(createRestClientException()).when(documentRepository).findAllDocumentByBeneficiaryId(anyLong());
		assertEquals(Lists.newArrayList(), documentService.findAllDocumentByBeneficiaryId(TestUtilKeys.DEFAULT_ID), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	private Document createDocument(boolean withId) {
		return Document.builder()
				.id(withId ? TestUtilKeys.DEFAULT_ID : null)
				.type(DocumentEnum.RG.getType())
				.description(TestUtilKeys.STR_DEFAULT_DOCUMENT_RG_NUMBER_UNMASKED)
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
