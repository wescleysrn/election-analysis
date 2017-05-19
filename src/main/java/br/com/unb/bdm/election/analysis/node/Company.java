package br.com.unb.bdm.election.analysis.node;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity(label="Company")
public class Company {

	@GraphId
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String cnpj;
	
	@Getter
	@Setter
	private String economicSector;
	
	@Getter
	@Setter
	private String name;
	
	@Relationship(type = "DONATES_TO")
	private List<Candidate> candidates = new ArrayList<>();
	
	@Relationship(type = "DONATES_TO")
	private List<Party> partys = new ArrayList<>();
	
}
