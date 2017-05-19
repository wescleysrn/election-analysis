package br.com.unb.bdm.election.analysis.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.node.Candidate;
import br.com.unb.bdm.election.analysis.node.Company;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "DONATES_TO")
public class CompanyDonationCandidate {

	@GraphId
	@Getter
	@Setter
	private Long id;
	
	@StartNode
	@Getter
	@Setter
	private Company company;
	
	@EndNode
	@Getter
	@Setter
	private Candidate candidate;
	
	@Getter
	@Setter
	private Double value;
	
	public CompanyDonationCandidate() {}

	public CompanyDonationCandidate(Candidate candidate, Company company){
		this.candidate = candidate;
		this.company = company;
	}
	
}
