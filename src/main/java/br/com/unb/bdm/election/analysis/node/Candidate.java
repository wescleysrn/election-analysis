package br.com.unb.bdm.election.analysis.node;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.relationship.CompanyDonationCandidate;
import br.com.unb.bdm.election.analysis.relationship.PersonDonationCandidate;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Candidate")
public class Candidate {

	@GraphId
	@Getter
	@Setter
	private Long id;
		
	@Getter
	@Setter
	private String cnpj;
	
	@Getter
	@Setter
	private String number;
	
	@Getter
	@Setter
	private String name;
		
	@Getter
	@Setter
	private String cpf;
	
	@Getter
	@Setter
	private String post;	
	
	@Relationship(type = "DONATES_TO", direction = Relationship.INCOMING)
	@Getter
	@Setter
	private List<PersonDonationCandidate> donationForCandidateFromPerson = new ArrayList<PersonDonationCandidate>();

	@Relationship(type = "DONATES_TO", direction = Relationship.INCOMING)
	@Getter
	@Setter
	private List<CompanyDonationCandidate> donationForCandidateFromCompany = new ArrayList<CompanyDonationCandidate>();
	
	@Relationship(type = "IS_RUNNING_IN")
	private List<State> states = new ArrayList<>();

	@Relationship(type = "MEMBER_OF")
	private List<Party> partys = new ArrayList<>();

}
