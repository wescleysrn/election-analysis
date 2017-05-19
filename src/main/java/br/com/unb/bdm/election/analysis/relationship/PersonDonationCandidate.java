package br.com.unb.bdm.election.analysis.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.node.Candidate;
import br.com.unb.bdm.election.analysis.node.Person;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "DONATES_TO")
public class PersonDonationCandidate {

	@GraphId
	@Getter
	@Setter
	private Long id;
	
	@StartNode
	@Getter
	@Setter
	private Person physicalPerson;
	
	@EndNode
	@Getter
	@Setter
	private Candidate candidate;
	
	@Getter
	@Setter
	private Double value;
	
	public PersonDonationCandidate() {}

	public PersonDonationCandidate(Candidate candidate, Person physicalPerson){
		this.candidate = candidate;
		this.physicalPerson = physicalPerson;
	}
	
}
