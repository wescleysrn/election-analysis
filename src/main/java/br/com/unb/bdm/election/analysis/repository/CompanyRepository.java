package br.com.unb.bdm.election.analysis.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.unb.bdm.election.analysis.node.Company;
import br.com.unb.bdm.election.analysis.queryresult.CompanyLargestDonationData;

@Repository
public interface CompanyRepository extends GraphRepository<Company> {

	@Query("MATCH (n)<-[r:DONATES_TO]-(c:Company) RETURN c as company, sum(r.value) as totalDonation "
			+ "ORDER BY totalDonation DESC LIMIT {limit}")
	List<CompanyLargestDonationData> loadLargestDonors(@Param("limit") int limit);

	@Query("MATCH (can:Candidate)<-[r:DONATES_TO]-(c:Company) RETURN c as company, sum(r.value) "
			+ "as totalDonation ORDER BY totalDonation DESC LIMIT {limit}")
	List<CompanyLargestDonationData> loadLargestDonorsCandidate(@Param("limit") int limit);

	@Query("MATCH (p:Party)<-[r:DONATES_TO]-(c:Company) RETURN c as company, sum(r.value) "
			+ "as totalDonation ORDER BY totalDonation DESC LIMIT {limit}")
	List<CompanyLargestDonationData> loadLargestDonorsParty(@Param("limit") int limit);

}
