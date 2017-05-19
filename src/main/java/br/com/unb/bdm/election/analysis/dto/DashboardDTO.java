package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class DashboardDTO implements Serializable {

	private static final long serialVersionUID = -9001378710328627439L;

	@Getter
	@Setter
	private Long id;	
	
	@Getter
	@Setter
	private String pt;	

}
