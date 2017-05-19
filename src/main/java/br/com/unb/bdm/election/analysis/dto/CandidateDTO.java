package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CandidateDTO implements Serializable {

	private static final long serialVersionUID = -8728631614462943173L;

	@Getter
	@Setter
	private Long id;	
	
	@Getter
	@Setter
	private String cpf;	
	
}
