package br.com.unb.bdm.election.analysis.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unb.bdm.election.analysis.dto.PersonDonationDTO;
import br.com.unb.bdm.election.analysis.queryresult.PersonLargestDonationData;
import br.com.unb.bdm.election.analysis.repository.PersonRepository;

@Service
public class PersonService {

	private static final String DONATION_TO_PARTY = "party";
	private static final String DONATION_TO_CANDIDATE = "candidate";	
	
	@Autowired 
	PersonRepository repository;
	
	public List<PersonDonationDTO> loadLargestDonors(String destination, int limit) {
		List<PersonLargestDonationData> donations = new ArrayList<>(); 
		if(destination.equals(DONATION_TO_CANDIDATE)) {
			donations = repository.loadLargestDonorsCandidate(limit);					
		} else if (destination.equals(DONATION_TO_PARTY)) {
			donations = repository.loadLargestDonorsParty(limit);					
		} else {
			donations = repository.loadLargestDonors(limit);					
		}
		List<PersonDonationDTO> list = new ArrayList<PersonDonationDTO>();
		for (PersonLargestDonationData donation : donations) {
			ModelMapper modelMapper = new ModelMapper();
			PersonDonationDTO personDonationDTO = modelMapper.map(donation.getPerson(), PersonDonationDTO.class);
			personDonationDTO.setTotalDonation(donation.getTotalDonation());				
			list.add(personDonationDTO);							
		}
		return list;
	}

}
