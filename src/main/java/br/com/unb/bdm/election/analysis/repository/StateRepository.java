package br.com.unb.bdm.election.analysis.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.unb.bdm.election.analysis.node.State;

@Repository
public interface StateRepository extends GraphRepository<State> {

	@Query("MATCH (s:State)<-[r:IS_RUNNING_IN]-(c:Candidate) RETURN s,r,c LIMIT {limit}")
	List<State> candidateRunningState(@Param("limit") int limit);
	
}
