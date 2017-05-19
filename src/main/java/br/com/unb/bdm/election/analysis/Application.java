package br.com.unb.bdm.election.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "br.com.unb.bdm.election.analysis.repository")
@EntityScan({"br.com.unb.bdm.election.analysis.node", "br.com.unb.bdm.election.analysis.relationship", 
			 "br.com.unb.bdm.election.analysis.queryresult", 
			 "br.com.unb.bdm.election.analysis.rest", "br.com.unb.bdm.election.analysis.service"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
