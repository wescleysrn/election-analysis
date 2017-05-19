package br.com.unb.bdm.election.analysis.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unb.bdm.election.analysis.dto.CompanyDonationDTO;
import br.com.unb.bdm.election.analysis.exception.BusinessException;
import br.com.unb.bdm.election.analysis.service.CompanyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/company/")
@Slf4j
public class CompanyRest {

	@Autowired 
	CompanyService service;	
	
	@RequestMapping(value = "/largestDonors/{destination}/company", method = RequestMethod.GET)
	public HttpEntity<List<CompanyDonationDTO>> loadLargestDonors(@PathVariable("destination") String destination, @RequestParam(value = "limit",required = false) Integer limit) throws BusinessException {
		try {
			List<CompanyDonationDTO> largestDonationList = this.service.loadLargestDonors(destination, limit == null ? 15 : limit);
			return new ResponseEntity<List<CompanyDonationDTO>>(largestDonationList, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error in recovering larger donors", ex);
		}
	}	
	
}
