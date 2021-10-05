package com.academico.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalhesDTO {

	
	private int status;
	private String msg;
	private String date;
	
	public DetalhesDTO(int status, String msg, String date) {
		//super();
		this.status = status;
		this.msg = msg;
		this.date = date;
	}
}