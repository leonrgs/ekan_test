package br.com.ekan.beneficiary.enumerator;

import lombok.Getter;

@Getter
public enum DocumentEnum {
	
	RG(1l, "RG"),
	CPF(2l, "CPF"),
	CNH(3l, "CNH"),
	TITULO(4l, "Título de Eleitor"),
	OUTRO(5l, "Outros");
	
	private Long type;
	private String name;
	
	DocumentEnum(Long type, String name) {
		this.type  = type;
		this.name = name;
	}

	public static DocumentEnum fromType(Long type) {
		for (DocumentEnum documentEnum: DocumentEnum.values()) {
			if (type.equals(documentEnum.type)) {
				return documentEnum;
			}	
		}
		return DocumentEnum.OUTRO;
	}
}
