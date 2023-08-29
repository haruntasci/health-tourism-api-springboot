package com.allianz.healthtourism.database.repository;

import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.util.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends IBaseRepository<MedicalRecord> {
}
