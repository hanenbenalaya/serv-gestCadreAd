package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Cadreadmin;
@EnableDiscoveryClient
@SpringBootApplication
public class ServGestCadreAdApplication implements CommandLineRunner{

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
   
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
    

	public static void main(String[] args) {
		SpringApplication.run(ServGestCadreAdApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		restConfiguration.exposeIdsFor(Cadreadmin.class);
	}
	
}
