package com.allianz.healthtourism.database.repository;

import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.util.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends IBaseRepository<Patient> {
}
