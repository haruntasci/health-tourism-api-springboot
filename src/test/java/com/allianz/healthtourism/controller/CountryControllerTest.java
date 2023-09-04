package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CountryRequestDTO;
import com.allianz.healthtourism.service.CountryService;
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
class CountryControllerTest {
    @Mock
    CountryService mockCountryService;

    @InjectMocks
    CountryController underTest;

    @Test
    void save_shouldSaveSuccessfully() {
        //given
        CountryDTO expected = new CountryDTO();
        expected.setName("Türkiye");
        when(mockCountryService.save(any())).thenReturn(expected);

        //when
        CountryRequestDTO request = new CountryRequestDTO();
        ResponseEntity<CountryDTO> response = underTest.save(request);
        CountryDTO actual = response.getBody();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getName(), actual.getName())
        );

    }


}