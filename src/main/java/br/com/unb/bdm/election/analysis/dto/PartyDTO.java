package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PartyDTO implements Serializable {

	private static final long serialVersionUID = 101371301033775636L;

	@Getter
	@Setter
	private Long id;	
	
	@Getter
	@Setter
	private String cnpj;	
	
}
