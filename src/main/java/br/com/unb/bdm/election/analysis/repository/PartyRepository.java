package br.com.unb.bdm.election.analysis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.unb.bdm.election.analysis.node.Party;

@Repository
public interface PartyRepository extends GraphRepository<Party> {

	@Query("MATCH (p:Party)<-[r:DONATES_TO]-(pf:Person) RETURN p,r,pf LIMIT {limit}")
	List<Party> personDonationGraph(@Param("limit") int limit);
	
	@Query("MATCH (p:Party)<-[r:DONATES_TO]-(pj:Company) RETURN p,r,pj LIMIT {limit}")
	List<Party> companyDonationGraph(@Param("limit") int limit);
	
	@Query("MATCH (p:Party)<-[r:MEMBER_OF]-(c:Candidate) RETURN p,r,c LIMIT {limit}")
	List<Party> partyCandidateGraph(@Param("limit") int limit);

	@Query("MATCH (p:Party)<-[r:DONATES_TO]-(n) RETURN p, sum(r.value) as totalDonation")
	List<Map<Party, Double>> loadPartyDonationGraph();
	
}
