package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.database.entity.TravelPlan;
import com.allianz.healthtourism.database.repository.FlightBookingRepository;
import com.allianz.healthtourism.database.repository.HotelBookingRepository;
import com.allianz.healthtourism.database.repository.PatientRepository;
import com.allianz.healthtourism.database.repository.TravelPlanRepository;
import com.allianz.healthtourism.database.specification.TravelPlanSpecification;
import com.allianz.healthtourism.mapper.TravelPlanMapper;
import com.allianz.healthtourism.model.TravelPlanDTO;
import com.allianz.healthtourism.model.requestDTO.TravelPlanRequestDTO;
import com.allianz.healthtourism.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TravelPlanService extends BaseService<TravelPlan, TravelPlanDTO, TravelPlanRequestDTO, TravelPlanRepository,
        TravelPlanMapper, TravelPlanSpecification> {
    private final PatientRepository patientRepository;
    private final FlightBookingRepository flightBookingRepository;
    private final HotelBookingRepository hotelBookingRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final TravelPlanMapper travelPlanMapper;


    public TravelPlanService(TravelPlanRepository repository, TravelPlanMapper mapper, TravelPlanSpecification specification,
                             PatientRepository patientRepository, FlightBookingRepository flightBookingRepository,
                             HotelBookingRepository hotelBookingRepository, TravelPlanRepository travelPlanRepository,
                             TravelPlanMapper travelPlanMapper) {
        super(repository, mapper, specification);
        this.patientRepository = patientRepository;
        this.flightBookingRepository = flightBookingRepository;
        this.hotelBookingRepository = hotelBookingRepository;
        this.travelPlanRepository = travelPlanRepository;
        this.travelPlanMapper = travelPlanMapper;
    }

    @Override
    public TravelPlanDTO save(TravelPlanRequestDTO requestDTO) {
        TravelPlan travelPlan = new TravelPlan();
        populateTravelPlanWithObjects(travelPlan, requestDTO);
        return travelPlanMapper.entityToDto(travelPlan);
    }

    @Override
    public TravelPlanDTO update(UUID uuid, TravelPlanRequestDTO requestDTO) {
        TravelPlan travelPlan = travelPlanRepository.findByUuid(uuid).orElse(null);
        if (travelPlan != null) {
            populateTravelPlanWithObjects(travelPlan, requestDTO);
            return travelPlanMapper.entityToDto(travelPlan);
        } else {
            return null;
        }
    }

    private void populateTravelPlanWithObjects(TravelPlan travelPlan, TravelPlanRequestDTO requestDTO) {
        Patient patient = patientRepository.findByUuid(requestDTO.getPatientUUID()).orElse(null);
        if (patient != null) {
            travelPlan.setPatient(patient);
        }
        FlightBooking flightBooking = flightBookingRepository.findByUuid(requestDTO.getFlightBookingUUID()).orElse(null);
        if (flightBooking != null) {
            travelPlan.setFlightBooking(flightBooking);
        }
        HotelBooking hotelBooking = hotelBookingRepository.findByUuid(requestDTO.getHotelBookingUUID()).orElse(null);
        if (hotelBooking != null) {
            travelPlan.setHotelBooking(hotelBooking);
        }
        travelPlanRepository.save(travelPlan);
    }
}
