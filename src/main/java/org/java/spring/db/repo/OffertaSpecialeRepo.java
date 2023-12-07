package org.java.spring.db.repo;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaSpecialeRepo extends  JpaRepository<OffertaSpeciale, Integer> {

}
