package br.com.unb.bdm.election.analysis.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CandidateDonationGraphDTO implements Serializable {

	private static final long serialVersionUID = 8146062521986301455L;

	@Getter
	@Setter
	private String key;
	
	@Getter
	@Setter
	private String color;
	
	@Getter
	@Setter
	List<CandidateDonationValuesGraphDTO> values = new ArrayList<CandidateDonationValuesGraphDTO>();
	
}
