package com.allianz.healthtourism.database.repository;

import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.util.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends IBaseRepository<Country> {
}
