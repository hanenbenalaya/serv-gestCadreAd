package com.example.demo.repos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Cadreadmin;

public interface AdminRepository extends JpaRepository<Cadreadmin, Long>{
	public Optional<Cadreadmin> findByNom(@Param("nom")String name);
	
	public Optional<Cadreadmin> findByEmail(@Param("email") String email);
	
	@Query("select e from Cadreadmin e where e.dateEmbauche > :x and e.dateEmbauche< :y")
	public Optional<List<Cadreadmin>> findByMc(@Param("x")Date date1, @Param("y")Date date2);
	
	@Query("SELECT count(*) FROM Cadreadmin")
	long countCadreadmin();
	
	@Query("SELECT nbrAbscence FROM Cadreadmin")
	ArrayList<Integer> findnbrAbscence();
}
