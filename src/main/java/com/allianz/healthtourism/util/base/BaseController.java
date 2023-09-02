package com.allianz.healthtourism.util.base;


import com.allianz.healthtourism.model.PageDTO;
import com.allianz.healthtourism.util.constants.Constants;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import com.allianz.healthtourism.util.repository.IBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
public abstract class BaseController<
        Entity extends BaseEntity,
        DTO extends BaseDTO,
        RequestDTO extends BaseRequestDTO,
        Mapper extends IBaseMapper<Entity, DTO, RequestDTO>,
        Repository extends IBaseRepository<Entity>,
        Specification extends BaseSpecification<Entity>,
        Service extends BaseService<Entity, DTO, RequestDTO, Repository, Mapper, Specification>
        > {

    private final Service service;

    @PostMapping
    public ResponseEntity<DTO> save(@RequestBody RequestDTO requestDTO) {
        return new ResponseEntity<>(service.save(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping("get-all-filter")
    public ResponseEntity<PageDTO<DTO>> getAll(@RequestBody BaseFilterRequestDTO baseFilterRequestDTO) {
        return new ResponseEntity<>(service.getAll(baseFilterRequestDTO), HttpStatus.OK);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<DTO> update(@PathVariable UUID uuid, @RequestBody RequestDTO requestDTO){
        return new ResponseEntity<>(service.update(uuid, requestDTO), HttpStatus.OK);
    }


    @DeleteMapping("{uuid}")
    public ResponseEntity<String> deleteByUUID(@PathVariable UUID uuid) {
        Boolean isDeleted = service.deleteByUUID(uuid);
        if (isDeleted) {
            return new ResponseEntity<>(Constants.DELETE_SUCCESS_MESSAGE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Constants.DELETE_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{uuid}")
    public ResponseEntity<DTO> getByUUID(@PathVariable UUID uuid) {
        DTO dto = service.getByUUID(uuid);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
