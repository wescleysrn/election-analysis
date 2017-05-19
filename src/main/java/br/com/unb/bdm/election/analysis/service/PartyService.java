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

import br.com.unb.bdm.election.analysis.dto.PartyDonationGraphDTO;
import br.com.unb.bdm.election.analysis.node.Party;
import br.com.unb.bdm.election.analysis.relationship.CompanyDonationParty;
import br.com.unb.bdm.election.analysis.relationship.PersonDonationParty;
import br.com.unb.bdm.election.analysis.repository.PartyRepository;

@Service
public class PartyService {

	private static final String DONOR_PERSON = "PERSON";
	private static final String DONOR_COMPANY = "COMPANY";
	
	@Autowired 
	PartyRepository repositoryParty;

	@Transactional(readOnly = true)
	public Map<String, Object> personDonationGraph(int limit) {
		List<Party> partys = repositoryParty.personDonationGraph(limit);
		return convert3DFormat(partys, DONOR_PERSON);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> companyDonationGraph(int limit) {
		List<Party> partys = repositoryParty.companyDonationGraph(limit);
		return convert3DFormat(partys, DONOR_COMPANY);
	}
	
	private Map<String, Object> convert3DFormat(Collection<Party> partys, String tipoDoador) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Party> result = partys.iterator();
		while (result.hasNext()) {
			Party partyIt = result.next();
			nodes.add(mapNodeParty("Name", partyIt.getName(), 
								   "Initials", partyIt.getInitials(),
								   "label", "Party", "group", 1));
			int target = i;
			i++;
			if(tipoDoador.equals(DONOR_PERSON)) {
				for (PersonDonationParty donation : partyIt.getPersonDonationParty()) {
					Map<String, Object> donor = mapNodeDoadorPerson("Name", donation.getPerson().getName(), 
															  "CPF", donation.getPerson().getCpf(),
															  "label", "Physical Person Donor", "group", 2);
					int source = nodes.indexOf(donor);
					if (source == -1) {
						nodes.add(donor);
						source = i++;
					}
					rels.add(mapRelationship("source", source, "target", target));
				}				
			} else if(tipoDoador.equals(DONOR_COMPANY)){
				for (CompanyDonationParty donation : partyIt.getCompanyDonationParty()) {
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

	private Map<String, Object> mapNodeParty(String key1, Object value1, 
												 String key2, Object value2, 
												 String key3, Object value3, 
												 String key4, Object value4) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		result.put(key4, value4);
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

	public List<PartyDonationGraphDTO> loadPartyDonationGraph() {
		
		List<Map<Party, Double>> donations = repositoryParty.loadPartyDonationGraph();
		
		List<PartyDonationGraphDTO> graph = new ArrayList<PartyDonationGraphDTO>();
		PartyDonationGraphDTO partyDonation1 = new PartyDonationGraphDTO();
		partyDonation1.setKey("PT");
		partyDonation1.setY(new Double(142685));
		graph.add(partyDonation1);
		PartyDonationGraphDTO partyDonation2 = new PartyDonationGraphDTO();		
		
		partyDonation2.setKey("PSDB");
		partyDonation2.setY(new Double(112035));		
		graph.add(partyDonation2);
		PartyDonationGraphDTO partyDonation3 = new PartyDonationGraphDTO();
		partyDonation3.setKey("PMDB");
		partyDonation3.setY(new Double(98475));
		
		graph.add(partyDonation3);
		PartyDonationGraphDTO partyDonation4 = new PartyDonationGraphDTO();
		partyDonation4.setKey("PTR");
		partyDonation4.setY(new Double(73785));
		
		graph.add(partyDonation4);
		PartyDonationGraphDTO partyDonation5 = new PartyDonationGraphDTO();
		partyDonation5.setKey("DEM");
		partyDonation5.setY(new Double(59348));
		
		graph.add(partyDonation5);
		PartyDonationGraphDTO partyDonation6 = new PartyDonationGraphDTO();
		partyDonation6.setKey("PV");
		partyDonation6.setY(new Double(43758));
		
		graph.add(partyDonation6);		
		PartyDonationGraphDTO partyDonation7 = new PartyDonationGraphDTO();
		partyDonation7.setKey("Outros");
		partyDonation7.setY(new Double(12745));
		
		graph.add(partyDonation7);
		
		return graph;
	}
	
}
