package com.allianz.healthtourism;

import com.allianz.healthtourism.database.repository.CityRepository;
import com.allianz.healthtourism.database.repository.DoctorRepository;
import com.allianz.healthtourism.database.repository.HospitalRepository;
import com.allianz.healthtourism.database.repository.PatientRepository;
import com.allianz.healthtourism.util.DataSeeder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@AllArgsConstructor
@EnableJpaAuditing
@EnableScheduling
public class HealthTourismApplication{


    public static void main(String[] args) {
        SpringApplication.run(HealthTourismApplication.class, args);
    }


}
