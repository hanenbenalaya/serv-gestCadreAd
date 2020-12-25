package com.example.demo.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entities.Cadreadmin;
import com.example.demo.repos.AdminRepository;
import com.example.demo.service.CadreAdminService;

@RestController
@RequestMapping("/api/cd")
public class CadreAdminController {
	@Autowired
	private  AdminRepository CadreAdminRepos;
	@Autowired
	private CadreAdminService cadreAdminService;
	
	@GetMapping("/cads")
	public Page<Cadreadmin> getAllCadreAdmins(@RequestParam(name="page")int page,@RequestParam(name="size", defaultValue="5")int size) {

		Page<Cadreadmin> Cadreadmins = CadreAdminRepos.findAll(PageRequest.of(page, size));
		return Cadreadmins;
	}
	@GetMapping("/cads/tauxabs")
	public float gettauxabs() {

		ArrayList<Integer> abs = CadreAdminRepos.findnbrAbscence();
		int nb=0;
		for( int i:abs ) {nb+=i;
		System.out.println(" nb abs :"+i);}
		int n= (int) CadreAdminRepos.countCadreadmin();
		System.out.println("nbr des cad :"+n);
		float taux= (float)nb/n;
		System.out.println("taux d'abscence :"+taux);
		return taux;
	}
	@GetMapping("/cadsid/{id}")
	public ResponseEntity<Cadreadmin> getCadreAdminById(@PathVariable(value = "id") Long cadId) {
		Cadreadmin cadreadmin= cadreAdminService.getCadreAdminById(cadId).get();
		return ResponseEntity.ok().body(cadreadmin);
	}
	@GetMapping("/cadsemail/{email}")
	public ResponseEntity<Cadreadmin> getCadreAdminByEmail(@PathVariable(value = "email") String email) {
		Cadreadmin cadreadmin= cadreAdminService.getCadreAdminByemail(email).get();
		return ResponseEntity.ok().body(cadreadmin);
	}
	@GetMapping("/cadsnom/{nom}")
	public ResponseEntity<Cadreadmin> getCadreAdminByName(@PathVariable(value = "nom") String nom) {
		Cadreadmin cadreadmin= cadreAdminService.getCadreAdminByname(nom).get();
		return ResponseEntity.ok().body(cadreadmin);
	}

	
	  @GetMapping( "/cadsc/{dt1}/{dt2}") 
	  public ResponseEntity<List<Cadreadmin>>
	  getCadreAdminByMC(@PathVariable(value = "dt1") String dt1,@PathVariable(value
	  = "dt2") String dt2) throws ParseException {
	  
	  Date x1=new SimpleDateFormat("yyyy-MM-dd").parse(dt1); Date x2 =new
	  SimpleDateFormat("yyyy-MM-dd").parse(dt2);
	  System.out.println("intervale  recu :"+x1+","+x2); List<Cadreadmin>
	  cadreadmin= cadreAdminService.getCadreAdminByMc(x1,x2).orElse(null); if
	  (cadreadmin!=null) { return ResponseEntity.ok().body(cadreadmin);} else
	  return null; }
	 
	@PostMapping("/cads")
	public String  createCadreAdmin(@Valid @RequestBody Cadreadmin cadeg)throws ResourceNotFoundException {
		System.out.println("Cadreadmin recu :"+cadeg);

		Cadreadmin cadreadmin = cadreAdminService.getCadreAdminByemail(cadeg.getEmail()).orElse(null);
		System.out.println("Cadreadmin trouvé "+cadreadmin);
		String id =String.valueOf(cadeg.getId());

		if(cadreadmin==null) {
			cadreAdminService.createCadreAdmin(cadeg);
		 return(id);}
		return(null);
	}
	@PutMapping("/cads/{id}")
	public ResponseEntity<Cadreadmin> updateCadreAdmin(@PathVariable(value = "id") Long cadId,
			@Valid @RequestBody Cadreadmin cadDetails) throws ResourceNotFoundException {
		 Cadreadmin cad = cadreAdminService.getCadreAdminById(cadId)
		 .orElseThrow(() -> new ResourceNotFoundException("Cadreadmin not found for this id :: " + cadId));

		cad.setId(cadDetails.getId());
        cad.setEmail(cadDetails.getEmail());
        cad.setNom(cadDetails.getNom());
        cad.setDateEmbauche(cadDetails.getDateEmbauche());




		final Cadreadmin updatedCadreAdmin = cadreAdminService.updateCadreAdmin(cadDetails);
		return ResponseEntity.ok(updatedCadreAdmin);
	}
	@DeleteMapping("/cads/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long cadId)
			throws ResourceNotFoundException {
		Cadreadmin cad = cadreAdminService.getCadreAdminById(cadId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + cadId));
System.out.println("Cadreadmin a supprimé"+cad);
cadreAdminService.deleteCadreAdmin(cad);
		System.out.println("Cadreadmin  supprimé");

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


}
