package br.com.unb.bdm.election.analysis.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.unb.bdm.election.analysis.node.Candidate;

@RepositoryRestResource(collectionResourceRel = "candidates", path = "candidates")
public interface CandidateRepository extends GraphRepository<Candidate> {

	Candidate findByCpf(@Param("cpf") String cpf);
	
	Candidate findByName(@Param("name") String name);
	
	List<Candidate> findByPost(@Param("post") String post);
	
	@Query("MATCH (c:Candidate)<-[r:DONATES_TO]-(p:Person) RETURN c,r,p LIMIT {limit}")
	List<Candidate> personDonationGraph(@Param("limit") int limit);
	
	@Query("MATCH (c:Candidate)<-[r:DONATES_TO]-(pj:Company) RETURN c,r,pj LIMIT {limit}")
	List<Candidate> companyDonationGraph(@Param("limit") int limit);
	
}
