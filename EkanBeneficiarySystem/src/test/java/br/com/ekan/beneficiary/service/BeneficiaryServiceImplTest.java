package br.com.ekan.beneficiary.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.web.client.RestClientException;

import br.com.ekan.beneficiary.dto.BeneficiaryDTO;
import br.com.ekan.beneficiary.dto.BeneficiaryDocumentDTO;
import br.com.ekan.beneficiary.dto.DocumentDTO;
import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.enumerator.DocumentEnum;
import br.com.ekan.beneficiary.keys.TestUtilKeys;
import br.com.ekan.beneficiary.repository.BeneficiaryRepository;
import br.com.ekan.beneficiary.repository.DocumentRepository;
import br.com.ekan.beneficiary.service.impl.BeneficiaryServiceImpl;
import br.com.ekan.beneficiary.utils.GenericUtils;
import jakarta.validation.ValidationException;

@ExtendWith(MockitoExtension.class)
class BeneficiaryServiceImplTest {
	
	@InjectMocks
	BeneficiaryServiceImpl beneficiaryService;
	
	@Mock
	BeneficiaryRepository beneficiaryRepository;
	
	@Mock
	DocumentRepository documentRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@Test
	public void should_equals_when_saveBeneficiaryWithDocument() {
		BeneficiaryDocumentDTO beneficiaryDocumentDTO = createBeneficiaryDocumentDTO(false, true);
		Beneficiary beneficiary = createBeneficiary(true);
		BeneficiaryDTO beneficiaryDTO = createBeneficiaryDTO(false);
		doReturn(beneficiaryDTO).when(modelMapper).map(beneficiaryDocumentDTO, BeneficiaryDTO.class);
		doReturn(beneficiary).when(beneficiaryRepository).save(any());
		doReturn(beneficiary).when(modelMapper).map(beneficiaryDTO, Beneficiary.class);
		doReturn(createBeneficiaryDTO(true)).when(modelMapper).map(beneficiary, BeneficiaryDTO.class);
		doReturn(createDocument(false)).when(modelMapper).map(beneficiaryDocumentDTO.getDocuments().get(TestUtilKeys.INT_ZERO), Document.class);
		var retorno = beneficiaryService.saveBeneficiaryWithDocument(beneficiaryDocumentDTO);
		assertEquals(TestUtilKeys.DEFAULT_ID, retorno.getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_saveBeneficiaryWithDocument_withEmptyDocument() {
		BeneficiaryDocumentDTO beneficiaryDocumentDTO = createBeneficiaryDocumentDTO(false, false);
		assertThrows(ValidationException.class, () -> beneficiaryService.saveBeneficiaryWithDocument(beneficiaryDocumentDTO), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_throws_when_saveBeneficiaryWithDocument() {
		BeneficiaryDocumentDTO beneficiaryDocumentDTO = createBeneficiaryDocumentDTO(false, true);
		doThrow(createRestClientException()).when(beneficiaryRepository).save(any());
		BeneficiaryDTO beneficiaryDTO = createBeneficiaryDTO(false);
		doReturn(beneficiaryDTO).when(modelMapper).map(beneficiaryDocumentDTO, BeneficiaryDTO.class);
		assertThrows(RestClientException.class, () -> beneficiaryService.saveBeneficiaryWithDocument(beneficiaryDocumentDTO), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_equals_when_updateBeneficiary() {
		Beneficiary beneficiary = createBeneficiary(true);
		BeneficiaryDTO beneficiaryDTO = createBeneficiaryDTO(true);
		doReturn(beneficiary).when(beneficiaryRepository).save(any());
		doReturn(beneficiary).when(modelMapper).map(beneficiaryDTO, Beneficiary.class);
		doReturn(createBeneficiaryDTO(true)).when(modelMapper).map(beneficiary, BeneficiaryDTO.class);
		var retorno = beneficiaryService.updateBeneficiary(beneficiaryDTO);
		assertEquals(TestUtilKeys.DEFAULT_ID, retorno.getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_updateBeneficiaryEmptyId() {
		assertThrows(ValidationException.class, () -> beneficiaryService.updateBeneficiary(createBeneficiaryDTO(false)));
	}
	
	@Test
	public void should_throws_when_updateBeneficiary() {
		doThrow(createRestClientException()).when(beneficiaryRepository).save(any());
		assertThrows(RestClientException.class, () -> beneficiaryService.updateBeneficiary(createBeneficiaryDTO(true)), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_equals_when_findAll() {
		List<Beneficiary> lsBeneficiary = Lists.newArrayList(createBeneficiary(true));
		doReturn(new PageImpl<>(lsBeneficiary)).when(beneficiaryRepository).findAll(GenericUtils.createPageable(TestUtilKeys.INT_ZERO, TestUtilKeys.INT_THIRTY));
		doReturn(Lists.newArrayList(createBeneficiaryDTO(true))).when(modelMapper).map(lsBeneficiary, ArrayList.class);
		var retorno = beneficiaryService.findAll(String.valueOf(TestUtilKeys.INT_ZERO), String.valueOf(TestUtilKeys.INT_THIRTY));
		assertEquals(TestUtilKeys.DEFAULT_ID, retorno.get(TestUtilKeys.INT_ZERO).getId(), TestUtilKeys.STR_ASSERT_EQUALS);
	}
	
	@Test
	public void should_throws_when_findAll() {
		doThrow(createRestClientException()).when(beneficiaryRepository).findAll(GenericUtils.createPageable(TestUtilKeys.INT_ZERO, TestUtilKeys.INT_THIRTY));
		assertThrows(RestClientException.class, () -> beneficiaryService.findAll(String.valueOf(TestUtilKeys.INT_ZERO), String.valueOf(TestUtilKeys.INT_THIRTY)), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	@Test
	public void should_doesntTrhows_when_delete() {
		doNothing().when(beneficiaryRepository).deleteById(any());
		assertDoesNotThrow(() -> beneficiaryService.delete(TestUtilKeys.DEFAULT_ID), TestUtilKeys.STR_ASSERT_NOT_THROWS);
	}
	
	@Test
	public void should_throws_when_delete() {
		doThrow(createRestClientException()).when(beneficiaryRepository).deleteById(any());
		assertThrows(RestClientException.class, () -> beneficiaryService.delete(TestUtilKeys.DEFAULT_ID), TestUtilKeys.STR_ASSERT_THROWS);
	}
	
	private Beneficiary createBeneficiary(boolean withId) {
		return Beneficiary.builder()
				.id(withId ? TestUtilKeys.DEFAULT_ID : null)
				.name(TestUtilKeys.STR_DEFAULT_BENEFICIARY_NAME)
				.birthday(TestUtilKeys.LD_DEFAULT_BIRTHDAY)
				.phone(TestUtilKeys.STR_DEFAULT_CELLPHONE_DDD_UNMASKED)
				.build();
	}
	
	private Document createDocument(boolean withId) {
		return Document.builder()
				.id(withId ? TestUtilKeys.DEFAULT_ID : null)
				.type(DocumentEnum.RG.getType())
				.description(TestUtilKeys.STR_DEFAULT_DOCUMENT_RG_NUMBER_UNMASKED)
				.build();
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
