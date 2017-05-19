package br.com.unb.bdm.election.analysis.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.node.Candidate;
import br.com.unb.bdm.election.analysis.node.State;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "IS_RUNNING_IN")
public class CandidareRunningInState {

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
	private State state;
	
	public CandidareRunningInState() {}

	public CandidareRunningInState(State state, Candidate candidate){
		this.state = state;
		this.candidate = candidate;
	}
	
}
