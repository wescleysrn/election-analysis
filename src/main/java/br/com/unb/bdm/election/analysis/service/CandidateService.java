package br.com.unb.bdm.election.analysis.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unb.bdm.election.analysis.dto.CandidateDonationGraphDTO;
import br.com.unb.bdm.election.analysis.dto.CandidateDonationValuesGraphDTO;
import br.com.unb.bdm.election.analysis.node.Candidate;
import br.com.unb.bdm.election.analysis.relationship.CompanyDonationCandidate;
import br.com.unb.bdm.election.analysis.relationship.PersonDonationCandidate;
import br.com.unb.bdm.election.analysis.repository.CandidateRepository;

@Service
public class CandidateService {

	private static final String DONOR_PERSON = "PERSON";
	private static final String DONOR_COMPANY = "COMPANY";
	
	@Autowired 
	CandidateRepository repositoryCandidate;

	@Transactional(readOnly = true)
	public Map<String, Object> personDonationGraph(int limit) {
		List<Candidate> candidates = repositoryCandidate.personDonationGraph(limit);
		return convert3DFormat(candidates, DONOR_PERSON);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> companyDonationGraph(int limit) {
		List<Candidate> candidates = repositoryCandidate.companyDonationGraph(limit);
		return convert3DFormat(candidates, DONOR_COMPANY);
	}
	
	private Map<String, Object> convert3DFormat(Collection<Candidate> candidate, String tipoDoador) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Candidate> result = candidate.iterator();
		while (result.hasNext()) {
			Candidate candidateIt = result.next();
			nodes.add(mapNodeCandidate("Name", candidateIt.getName(), 
									   "Post", candidateIt.getPost(),
									   "CPF", candidateIt.getCpf(),
									   "label", "Candidate", "group", 1));
			int target = i;
			i++;
			if(tipoDoador.equals(DONOR_PERSON)) {
				for (PersonDonationCandidate donation : candidateIt.getDonationForCandidateFromPerson()) {
					Map<String, Object> donor = mapNodeDoadorPerson("Name", donation.getPhysicalPerson().getName(), 
															  "CPF", donation.getPhysicalPerson().getCpf(),
															  "label", "Physical Person Donor", "group", 2);
					int source = nodes.indexOf(donor);
					if (source == -1) {
						nodes.add(donor);
						source = i++;
					}
					rels.add(mapRelationship("source", source, "target", target));
				}				
			} else if(tipoDoador.equals(DONOR_COMPANY)){
				for (CompanyDonationCandidate donation : candidateIt.getDonationForCandidateFromCompany()) {
					Map<String, Object> donor = mapNodeDoadorCompany("Name", donation.getCompany().getName(), 
															  "CNPJ", donation.getCompany().getCnpj(),
															  "Economic Sector", donation.getCompany().getEconomicSector(),
															  "label", "Physical Person Donor", "group", 2);
					int source = nodes.indexOf(donor);
					if (source == -1) {
						nodes.add(donor);
						source = i++;
					}
					rels.add(mapRelationship("source", source, "target", target));
				}								
			}
			
			
		}
		return mapRelationship("nodes", nodes, "links", rels);
	}

	private Map<String, Object> mapNodeCandidate(String key1, Object value1, 
												 String key2, Object value2, 
												 String key3, Object value3, 
												 String key4, Object value4, 
												 String key5, Object value5) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		result.put(key4, value4);
		result.put(key5, value5);
		return result;
	}

	private Map<String, Object> mapNodeDoadorPerson(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		result.put(key4, value4);
		return result;
	}
	
	private Map<String, Object> mapNodeDoadorCompany(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4, String key5, Object value5) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		result.put(key4, value4);
		result.put(key5, value5);
		return result;
	}
	
	private Map<String, Object> mapRelationship(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

	public List<CandidateDonationGraphDTO> loadCandidateDonationGraph() {
		List<CandidateDonationGraphDTO> graph = new ArrayList<CandidateDonationGraphDTO>();
		
		CandidateDonationGraphDTO candidateDonation1 = new CandidateDonationGraphDTO();
		candidateDonation1.setKey("Person");
		candidateDonation1.setColor("#d62728");
		
		List<CandidateDonationValuesGraphDTO> values1 = new ArrayList<CandidateDonationValuesGraphDTO>();
				
		CandidateDonationValuesGraphDTO value11 = new CandidateDonationValuesGraphDTO();
		value11.setLabel("Dilma Vana Rousseff - PT");
		value11.setValue(new Double(1.8746444827653));	
		values1.add(value11);
		
		CandidateDonationValuesGraphDTO value12 = new CandidateDonationValuesGraphDTO();
		value12.setLabel("Aécio Neves da Cunha - PSDB");
		value12.setValue(new Double(8.0961543492239));	
		values1.add(value12);

		CandidateDonationValuesGraphDTO value13 = new CandidateDonationValuesGraphDTO();
		value13.setLabel("José Sarney Filho - PV");
		value13.setValue(new Double(1.8746444827653));	
		values1.add(value13);

		CandidateDonationValuesGraphDTO value14 = new CandidateDonationValuesGraphDTO();
		value14.setLabel("Marconi Ferreira Perillho Junior - PSDB");
		value14.setValue(new Double(2.4174010336624));	
		values1.add(value14);
		
		CandidateDonationValuesGraphDTO value15 = new CandidateDonationValuesGraphDTO();
		value15.setLabel("Geraldo José Rodrigues Alckmin Filho - PSDB");
		value15.setValue(new Double(0.72009071426284));	
		values1.add(value15);
		
		CandidateDonationValuesGraphDTO value16 = new CandidateDonationValuesGraphDTO();
		value16.setLabel("Luiz Fernando de Sousa - PMDB");
		value16.setValue(new Double(0.77154485523777));	
		values1.add(value16);
		
		candidateDonation1.setValues(values1);
		graph.add(candidateDonation1);
		

		CandidateDonationGraphDTO candidateDonation2 = new CandidateDonationGraphDTO();
		candidateDonation2.setKey("Company");
		candidateDonation2.setColor("#1f77b4");

		List<CandidateDonationValuesGraphDTO> values2 = new ArrayList<CandidateDonationValuesGraphDTO>();

		CandidateDonationValuesGraphDTO value21 = new CandidateDonationValuesGraphDTO();
		value21.setLabel("Dilma Vana Rousseff - PT");
		value21.setValue(new Double(25.307646510375));	
		values2.add(value21);
		
		CandidateDonationValuesGraphDTO value22 = new CandidateDonationValuesGraphDTO();
		value22.setLabel("Aécio Neves da Cunha - PSDB");
		value22.setValue(new Double(16.756779544553));	
		values2.add(value22);

		CandidateDonationValuesGraphDTO value23 = new CandidateDonationValuesGraphDTO();
		value23.setLabel("José Sarney Filho - PV");
		value23.setValue(new Double(18.451534877007));	
		values2.add(value23);

		CandidateDonationValuesGraphDTO value24 = new CandidateDonationValuesGraphDTO();
		value24.setLabel("Marconi Ferreira Perillho Junior - PSDB");
		value24.setValue(new Double(8.6142352811805));	
		values2.add(value24);
		
		CandidateDonationValuesGraphDTO value25 = new CandidateDonationValuesGraphDTO();
		value25.setLabel("Geraldo José Rodrigues Alckmin Filho - PSDB");
		value25.setValue(new Double(7.8082472075876));	
		values2.add(value25);
		
		CandidateDonationValuesGraphDTO value26 = new CandidateDonationValuesGraphDTO();
		value26.setLabel("Luiz Fernando de Sousa - PMDB");
		value26.setValue(new Double(5.259101026956));	
		values2.add(value26);
		
		candidateDonation2.setValues(values2);
		graph.add(candidateDonation2);
		
		return graph;
	}

}
