package br.com.unb.bdm.election.analysis.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.node.Company;
import br.com.unb.bdm.election.analysis.node.Party;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "DONATES_TO")
public class CompanyDonationParty {

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
	private Party party;
	
	@Getter
	@Setter
	private Double value;
	
	public CompanyDonationParty() {}

	public CompanyDonationParty(Party party, Company company){
		this.party = party;
		this.company = company;
	}
	
}
