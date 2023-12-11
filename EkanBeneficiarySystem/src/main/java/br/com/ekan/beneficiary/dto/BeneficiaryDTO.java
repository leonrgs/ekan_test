package br.com.ekan.beneficiary.dto;

import java.time.LocalDate;
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
public class BeneficiaryDTO {
	
	private Long id;
	
	@Size(min = 3 , max = 50)
	private String name;
	
	@Size(min = 10 , max = 11)
	private String phone;
	
	@NotNull
	private LocalDate birthday;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
}
