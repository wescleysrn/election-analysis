package br.com.unb.bdm.election.analysis.queryresult;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.QueryResult;

import br.com.unb.bdm.election.analysis.node.Company;
import lombok.Getter;
import lombok.Setter;

@QueryResult
public class CompanyLargestDonationData implements Serializable {

	private static final long serialVersionUID = 4310498624806715731L;

	@Getter
	@Setter
	private Company company;
	
	@Getter
	@Setter
	private Double totalDonation;
	
}
