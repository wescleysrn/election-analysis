package br.com.unb.bdm.election.analysis.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unb.bdm.election.analysis.exception.BusinessException;
import br.com.unb.bdm.election.analysis.service.PartyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/party/")
@Slf4j
public class PartyRest {

	@Autowired 
	PartyService service;	
	
	@RequestMapping(value = "/person/donation/graph", method = RequestMethod.GET)
	public HttpEntity<Map<String, Object>> personDonationGraph(@RequestParam(value = "limit",required = false) Integer limit) throws BusinessException {
		try {
			Map<String, Object> graph = this.service.personDonationGraph(limit == null ? 2000 : limit);
			return new ResponseEntity<Map<String, Object>>(graph, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error creating physical person donation graph", ex);
		}
	}	

	@RequestMapping(value = "/company/donation/graph", method = RequestMethod.GET)
	public HttpEntity<Map<String, Object>> companyDonationGraph(@RequestParam(value = "limit",required = false) Integer limit) throws BusinessException {
		try {
			Map<String, Object> graph = this.service.companyDonationGraph(limit == null ? 2000 : limit);
			return new ResponseEntity<Map<String, Object>>(graph, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error creating physical person donation graph", ex);
		}
	}	

}
