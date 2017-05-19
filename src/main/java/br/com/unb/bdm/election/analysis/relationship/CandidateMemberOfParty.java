package br.com.unb.bdm.election.analysis.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.node.Candidate;
import br.com.unb.bdm.election.analysis.node.Party;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "MEMBER_OF")
public class CandidateMemberOfParty {

	@GraphId
	@Getter
	@Setter
	private Long id;
	
	@StartNode
	@Getter
	@Setter
	private Candidate candidate;
	
	@EndNode
	@Getter
	@Setter
	private Party party;
	
	public CandidateMemberOfParty() {}

	public CandidateMemberOfParty(Party party, Candidate candidate){
		this.party = party;
		this.candidate = candidate;
	}

}
