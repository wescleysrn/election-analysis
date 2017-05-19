package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CompanyDonationDTO implements Serializable {

	private static final long serialVersionUID = -6722508219983525340L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String cnpj;
	
	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String economicSector;
	
	@Getter
	@Setter
	private Double totalDonation;
	
}
