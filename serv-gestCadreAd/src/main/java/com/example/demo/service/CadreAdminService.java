package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Cadreadmin;

public interface CadreAdminService {
	List<Cadreadmin> getAllCadreAdmins();

	 Optional<Cadreadmin> getCadreAdminById(Long caId);

	 Cadreadmin createCadreAdmin(Cadreadmin ca);	

	 Cadreadmin updateCadreAdmin(Cadreadmin caDetails);

	  void deleteCadreAdmin(Cadreadmin ca);
	  
	 Optional<Cadreadmin> getCadreAdminByemail(String email );
	 Optional<Cadreadmin> getCadreAdminByname(String name );
		
     Optional<List<Cadreadmin>> getCadreAdminByMc(Date date1,Date date2 );
     Long countnbcad();
     ArrayList<Integer> findnbabs();
		 

}
