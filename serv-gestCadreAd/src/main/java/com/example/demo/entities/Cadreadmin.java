package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cadreadmin  {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String prenom;
	private String Fonction;
	private Long chargeHoraire;
	private Long nbrAbscence;
	private Long salaire;
	private Long  telephone;
	
	/*
	 * @DateTimeFormat(pattern="yyyy-mm-dd")
	 */
	private Date dateEmbauche;
	
	private String email;
	}
	
