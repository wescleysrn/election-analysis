package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CandidateDonationValuesGraphDTO implements Serializable {

	private static final long serialVersionUID = -4569221242183613962L;

	@Getter
	@Setter
	private String label;
	
	@Getter
	@Setter
	private Double value;
	
}
