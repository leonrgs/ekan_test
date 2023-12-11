package br.com.ekan.beneficiary.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {

	private Long id;
	
	@NotNull
	private Long type;
	
	@Size(min = 3, max = 20)
	private String description;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
	private Long beneficiaryId;
}
