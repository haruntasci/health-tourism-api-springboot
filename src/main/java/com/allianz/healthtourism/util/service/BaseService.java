package com.allianz.healthtourism.util.service;

import com.allianz.healthtourism.model.PageDTO;
import com.allianz.healthtourism.util.model.BaseDTO;
import com.allianz.healthtourism.util.model.BaseRequestDTO;
import com.allianz.healthtourism.util.entity.BaseEntity;
import com.allianz.healthtourism.util.mapper.IBaseMapper;
import com.allianz.healthtourism.util.repository.IBaseRepository;
import com.allianz.healthtourism.util.specification.BaseFilterRequestDTO;
import com.allianz.healthtourism.util.specification.BaseSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public abstract class BaseService<
        Entity extends BaseEntity,
        DTO extends BaseDTO,
        RequestDTO extends BaseRequestDTO,
        Repository extends IBaseRepository<Entity>,
        Mapper extends IBaseMapper<Entity, DTO, RequestDTO>,
        Specification extends BaseSpecification<Entity>> {

    private final Repository repository;
    private final Mapper mapper;
    private final Specification specification;

    @Transactional
    public DTO save(RequestDTO requestDTO){
        Entity entity = mapper.requestDtoToEntity(requestDTO);
        repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Transactional
    public DTO update(UUID uuid, RequestDTO requestDTO){
        Entity existEntity = repository.findByUuid(uuid).orElse(null);
        if (existEntity != null) {
            Entity entity = mapper.requestDtoToExistEntity(requestDTO, existEntity);
            repository.save(entity);
            return mapper.entityToDto(entity);
        } else {
            return null;
        }

    }


    public PageDTO<DTO> getAll(BaseFilterRequestDTO baseFilterRequestDTO) {
        Pageable pageable;
        if (baseFilterRequestDTO.getSortDTO() != null) {
            if (baseFilterRequestDTO.getSortDTO().getDirectionEnum() == Sort.Direction.ASC) {
                pageable = PageRequest.of(baseFilterRequestDTO.getPageNumber(), baseFilterRequestDTO.getPageSize(),
                        Sort.by(baseFilterRequestDTO.getSortDTO().getColumnName()).ascending());
            } else {
                pageable = PageRequest.of(baseFilterRequestDTO.getPageNumber(), baseFilterRequestDTO.getPageSize(),
                        Sort.by(baseFilterRequestDTO.getSortDTO().getColumnName()).descending());
            }
        } else {
            pageable = PageRequest.of(baseFilterRequestDTO.getPageNumber(), baseFilterRequestDTO.getPageSize(),
                    Sort.by("id").ascending());
        }

        specification.setCriteriaList(baseFilterRequestDTO.getSearchCriteriaList());
        Page<Entity> entityPage = repository.findAll(specification, pageable);
        return mapper.pageEntityToPageDTO(entityPage);
    }

    public DTO getByUUID(UUID uuid) {
        Entity entity = repository.findByUuid(uuid).orElse(null);
        if (entity != null) {
            return mapper.entityToDto(entity);
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean deleteByUUID(UUID uuid) {
        if (repository.deleteByUuid(uuid) == 1) {
            return true;
        } else {
            return false;
        }
    }


}
