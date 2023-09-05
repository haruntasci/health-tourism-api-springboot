package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.model.CityDTO;
import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CityRequestDTO;
import com.allianz.healthtourism.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @Mock
    CityService mockCityService;

    @InjectMocks
    CityController underTest;

    @Test
    void save_shouldSaveSuccessfully() {
        //given
        CityDTO expected = new CityDTO();
        expected.setName("Ankara");
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("Türkiye");
        expected.setCountry(countryDTO);

        when(mockCityService.save(any())).thenReturn(expected);

        //when
        CityRequestDTO request = new CityRequestDTO();
        ResponseEntity<CityDTO> response = underTest.save(request);
        CityDTO actual = response.getBody();

        //then

        assertAll(
                () -> assertEquals(actual, expected),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }


}