package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PartyDonationGraphDTO implements Serializable {

	private static final long serialVersionUID = -4051093858128829642L;

	@Getter
	@Setter
	private String key;
	
	@Getter
	@Setter
	private Double y;
	
}
