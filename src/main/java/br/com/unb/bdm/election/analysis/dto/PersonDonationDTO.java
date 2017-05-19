package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PersonDonationDTO implements Serializable {

	private static final long serialVersionUID = -8089864562478495385L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String cpf;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private Double totalDonation;
	
}
