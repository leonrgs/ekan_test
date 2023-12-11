package br.com.ekan.beneficiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ekan.beneficiary.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>, PagingAndSortingRepository<Document, Long> {
	List<Document> findAllDocumentByBeneficiaryId(Long id);

}
