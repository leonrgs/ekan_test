package br.com.ekan.beneficiary.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "tb_beneficiary")
public class Beneficiary implements Serializable {

	private static final long serialVersionUID = -258517992686402891L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_beneficiary_id")
	@SequenceGenerator(sequenceName = "seq_beneficiary_id", allocationSize = 1, name = "seq_beneficiary_id")
	private Long id;
	
	@Size(min = 3 , max = 50)
	private String name;
	
	@Size(min = 10 , max = 11)
	private String phone;
	
	@NotNull
	private LocalDate birthday;
	
	@NotNull
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
}
