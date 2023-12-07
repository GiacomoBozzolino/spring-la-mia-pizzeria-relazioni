package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.repo.OffertaSpecialeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaSpecialeService {
	
	@Autowired
	private OffertaSpecialeRepo offertaSpecialeRepo;
	
public List<OffertaSpeciale> findAll() {
		
		return offertaSpecialeRepo.findAll();
	}
	public OffertaSpeciale findById(int id) {
		
		return offertaSpecialeRepo.findById(id).get();
	}
	public void save(OffertaSpeciale offertaSpeciale) {
		
		offertaSpecialeRepo.save(offertaSpeciale);
	}
	public void delete(OffertaSpeciale offertaSpeciale) {
		
		offertaSpecialeRepo.delete(offertaSpeciale);
	}
	
	
	

}
