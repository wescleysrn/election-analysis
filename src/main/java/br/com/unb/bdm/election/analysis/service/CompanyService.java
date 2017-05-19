package br.com.unb.bdm.election.analysis.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unb.bdm.election.analysis.dto.CompanyDonationDTO;
import br.com.unb.bdm.election.analysis.queryresult.CompanyLargestDonationData;
import br.com.unb.bdm.election.analysis.repository.CompanyRepository;

@Service
public class CompanyService {

	private static final String DONATION_TO_PARTY = "party";
	private static final String DONATION_TO_CANDIDATE = "candidate";	
	
	@Autowired 
	CompanyRepository repository;
	
	public List<CompanyDonationDTO> loadLargestDonors(String destination, int limit) {		
		List<CompanyLargestDonationData> donations = new ArrayList<>();		
		if(destination.equals(DONATION_TO_CANDIDATE)) {
			donations = repository.loadLargestDonorsCandidate(limit);					
		} else if (destination.equals(DONATION_TO_PARTY)) {
			donations = repository.loadLargestDonorsParty(limit);					
		} else {
			donations = repository.loadLargestDonors(limit);					
		}
		List<CompanyDonationDTO> list = new ArrayList<CompanyDonationDTO>();
		for (CompanyLargestDonationData donation : donations) {
			ModelMapper modelMapper = new ModelMapper();
			CompanyDonationDTO companyDonationDTO = modelMapper.map(donation.getCompany(), CompanyDonationDTO.class);
			companyDonationDTO.setTotalDonation(donation.getTotalDonation());				
			list.add(companyDonationDTO);							
		}
		return list;
	}

}
