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

import br.com.unb.bdm.election.analysis.dto.PersonDonationDTO;
import br.com.unb.bdm.election.analysis.exception.BusinessException;
import br.com.unb.bdm.election.analysis.service.PersonService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/person/")
@Slf4j
public class PersonRest {

	@Autowired 
	PersonService service;	

	@RequestMapping(value = "/largestDonors/{destination}/person", method = RequestMethod.GET)
	public HttpEntity<List<PersonDonationDTO>> loadLargestDonors(@PathVariable("destination") String destination, @RequestParam(value = "limit",required = false) Integer limit) throws BusinessException {
		try {
			List<PersonDonationDTO> list = this.service.loadLargestDonors(destination, limit == null ? 15 : limit);
			return new ResponseEntity<List<PersonDonationDTO>>(list, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error in recovering dashboard", ex);
		}
	}	
	
}
