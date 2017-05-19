package br.com.unb.bdm.election.analysis.node;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.unb.bdm.election.analysis.relationship.CandidareRunningInState;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="State")
public class State {

	@GraphId
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private String name;
	
	@Relationship(type = "IS_RUNNING_IN", direction = Relationship.INCOMING)
	@Getter
	@Setter
	private List<CandidareRunningInState> candidateIsRunningInState = new ArrayList<CandidareRunningInState>();
	
}
