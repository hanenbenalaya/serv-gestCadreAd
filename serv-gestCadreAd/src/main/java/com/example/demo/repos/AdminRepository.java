package com.example.demo.repos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Cadreadmin;

public interface AdminRepository extends JpaRepository<Cadreadmin, Long>{

	public Page<Cadreadmin> findByNomContaining(@Param("nom")String name,Pageable pageable);
	
	public Optional<Cadreadmin> findByEmail(@Param("email") String email);
	
	@Query("select e from Cadreadmin e where e.dateEmbauche > :x and e.dateEmbauche< :y")
	public Page<Cadreadmin> findByMc(@Param("x")Date date1, @Param("y")Date date2,Pageable pageable);
	
	@Query("SELECT count(*) FROM Cadreadmin")
	long countCadreadmin();
	
	@Query("SELECT nbrAbscence FROM Cadreadmin")
	ArrayList<Integer> findnbrAbscence();
	@Query("SELECT chargeHoraire FROM Cadreadmin")
	ArrayList<Integer> findnbrChargeh();
}
