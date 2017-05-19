package br.com.unb.bdm.election.analysis.queryresult;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.QueryResult;

import br.com.unb.bdm.election.analysis.node.Person;
import lombok.Getter;
import lombok.Setter;

@QueryResult
public class PersonLargestDonationData implements Serializable {

	private static final long serialVersionUID = 857465619425975337L;

	@Getter
	@Setter
	private Person person;
	
	@Getter
	@Setter
	private Double totalDonation;
	
}
