package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.model.CountryDTO;
import com.allianz.healthtourism.model.requestDTO.CountryRequestDTO;
import com.allianz.healthtourism.service.CountryService;
import com.allianz.healthtourism.util.constants.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

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

    @Test
    void getByUUID_shouldGetSuccessfully() {
        //given
        CountryDTO expected = new CountryDTO();
        expected.setName("Germany");
        when(mockCountryService.getByUUID(any())).thenReturn(expected);

        //when
        ResponseEntity<CountryDTO> response = underTest.getByUUID(UUID.randomUUID());
        CountryDTO actual = response.getBody();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getName(), actual.getName())
        );
    }


    @Test
    void deleteByUUID_shouldDeleteSuccessfully() {
        when(mockCountryService.deleteByUUID(any())).thenReturn(true);

        ResponseEntity<String> response = underTest.deleteByUUID(UUID.randomUUID());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->     assertEquals(Constants.DELETE_SUCCESS_MESSAGE, response.getBody())
        );
    }

    @Test
    void deleteByUUID_shouldNotDeleteSuccessfully() {
        when(mockCountryService.deleteByUUID(any())).thenReturn(false);

        ResponseEntity<String> response = underTest.deleteByUUID(UUID.randomUUID());

        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()),
                () ->     assertEquals(Constants.DELETE_ERROR_MESSAGE, response.getBody())
        );
    }

}