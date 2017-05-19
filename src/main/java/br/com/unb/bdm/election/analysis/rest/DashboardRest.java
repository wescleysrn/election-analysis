package br.com.unb.bdm.election.analysis.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.unb.bdm.election.analysis.dto.CandidateDonationGraphDTO;
import br.com.unb.bdm.election.analysis.dto.PartyDonationGraphDTO;
import br.com.unb.bdm.election.analysis.exception.BusinessException;
import br.com.unb.bdm.election.analysis.service.CandidateService;
import br.com.unb.bdm.election.analysis.service.DashboardServie;
import br.com.unb.bdm.election.analysis.service.PartyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dashboard/")
@Slf4j
public class DashboardRest {
	
	@Autowired 
	DashboardServie service;	
	
	@Autowired
	CandidateService serviceCandidate;
	
	@Autowired
	PartyService serviceParty;

	@RequestMapping(value = "/candidate/donation/graph", method = RequestMethod.GET)
	public HttpEntity<List<CandidateDonationGraphDTO>> loadCandidateDonationGraph() throws BusinessException {
		try {
			List<CandidateDonationGraphDTO> graph = this.serviceCandidate.loadCandidateDonationGraph();
			return new ResponseEntity<List<CandidateDonationGraphDTO>>(graph, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error in recovering dashboard", ex);
		}
	}	
	
	@RequestMapping(value = "/party/donation/graph", method = RequestMethod.GET)
	public HttpEntity<List<PartyDonationGraphDTO>> loadPartyDonationGraph() throws BusinessException {
		try {
			List<PartyDonationGraphDTO> graph = this.serviceParty.loadPartyDonationGraph();
			return new ResponseEntity<List<PartyDonationGraphDTO>>(graph, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error in recovering dashboard", ex);
		}
	}	
	
	/*
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public HttpEntity<DashboardDTO> load() throws BusinessException {
		try {
			DashboardDTO dashboard = this.service.load();
			return new ResponseEntity<DashboardDTO>(dashboard, HttpStatus.OK);
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			throw new BusinessException("Error in recovering dashboard", ex);
		}
	}	
	*/
	
}
