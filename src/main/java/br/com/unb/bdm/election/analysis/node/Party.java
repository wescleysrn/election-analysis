package br.com.unb.bdm.election.analysis.node;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.relationship.CandidateMemberOfParty;
import br.com.unb.bdm.election.analysis.relationship.CompanyDonationParty;
import br.com.unb.bdm.election.analysis.relationship.PersonDonationParty;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Party")
public class Party {

	@GraphId
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String initials;	

	@Relationship(type = "DONATES_TO", direction = Relationship.INCOMING)
	@Getter
	@Setter
	private List<PersonDonationParty> personDonationParty = new ArrayList<PersonDonationParty>();
	
	@Relationship(type = "DONATES_TO", direction = Relationship.INCOMING)
	@Getter
	@Setter
	private List<CompanyDonationParty> companyDonationParty = new ArrayList<CompanyDonationParty>();
	
	@Relationship(type = "MEMBER_OF", direction = Relationship.INCOMING)
	@Getter
	@Setter
	private List<CandidateMemberOfParty> candidateMemberOfParty = new ArrayList<CandidateMemberOfParty>();
	
}
