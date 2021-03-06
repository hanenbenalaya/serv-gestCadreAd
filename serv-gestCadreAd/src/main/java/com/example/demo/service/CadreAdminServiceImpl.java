package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cadreadmin;
import com.example.demo.repos.AdminRepository;
@Service
public class CadreAdminServiceImpl implements CadreAdminService{
	@Autowired
	private AdminRepository  CadreAdminrepos;
	
	@Override
	public List<Cadreadmin> getAllCadreAdmins() {

		return this.CadreAdminrepos.findAll();
	}

	@Override
	public Optional<Cadreadmin> getCadreAdminById(Long caId) {
		
		return this.CadreAdminrepos.findById(caId);
	}

	@Override
	public Cadreadmin createCadreAdmin(Cadreadmin ca) {
		
		return  this.CadreAdminrepos.save(ca);
	}

	@Override
	public Cadreadmin updateCadreAdmin(Cadreadmin caDetails) {
        
		
		return this.CadreAdminrepos.save(caDetails);
	}

	@Override
	public void deleteCadreAdmin(Cadreadmin ca) {
		
		this.CadreAdminrepos.delete(ca);
		
	}

	@Override
	public Optional<Cadreadmin> getCadreAdminByemail(String email) {
		
		return this.CadreAdminrepos.findByEmail(email);
	}

	

	
	

	
	

	@Override
	public Long countnbcad() {
		// TODO Auto-generated method stub
		return  this.CadreAdminrepos.countCadreadmin();
	}

	@Override
	public ArrayList<Integer> findnbabs() {
		// TODO Auto-generated method stub
		return this.CadreAdminrepos.findnbrAbscence();
	}
}
