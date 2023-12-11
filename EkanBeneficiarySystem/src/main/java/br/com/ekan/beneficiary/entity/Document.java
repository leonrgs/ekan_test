package br.com.ekan.beneficiary.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_document")
public class Document implements Serializable {

	private static final long serialVersionUID = 7382035325901287565L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document_id")
	@SequenceGenerator(sequenceName = "seq_document_id", allocationSize = 1, name = "seq_document_id")
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "type")
	private Long type;
	
	@Size(min = 3, max = 20)
	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@NotNull
	@Column(name = "beneficiary_id")
	private Long beneficiaryId;

}
