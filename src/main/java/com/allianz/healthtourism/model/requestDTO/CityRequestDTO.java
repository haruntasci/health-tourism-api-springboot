package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.database.entity.Country;
import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class CityRequestDTO extends BaseRequestDTO {
    private String name;
    private UUID countryUUID;
}
