package br.com.ekan.beneficiary.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericUtils {

	public static Pageable createPageable(int page, int limit) {
		return PageRequest.of(page, limit);
	}
	
}
