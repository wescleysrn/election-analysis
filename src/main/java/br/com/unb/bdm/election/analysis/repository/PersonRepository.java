package br.com.unb.bdm.election.analysis.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.unb.bdm.election.analysis.node.Person;
import br.com.unb.bdm.election.analysis.queryresult.PersonLargestDonationData;

@Repository
public interface PersonRepository extends GraphRepository<Person> {

	@Query("MATCH (n)<-[r:DONATES_TO]-(p:Person) RETURN p as person, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT {limit}")
	List<PersonLargestDonationData> loadLargestDonors(@Param("limit") int limit);

	@Query("MATCH (c:Candidate)<-[r:DONATES_TO]-(p:Person) RETURN p as person, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT {limit}")
	List<PersonLargestDonationData> loadLargestDonorsCandidate(@Param("limit") int limit);

	@Query("MATCH (py:Party)<-[r:DONATES_TO]-(p:Person) RETURN p as person, sum(r.value) as totalDonation ORDER BY totalDonation DESC LIMIT {limit}")
	List<PersonLargestDonationData> loadLargestDonorsParty(@Param("limit") int limit);

}
