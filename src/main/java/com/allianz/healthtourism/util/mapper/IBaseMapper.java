package com.allianz.healthtourism.util.mapper;

import com.allianz.healthtourism.model.PageDTO;
import com.allianz.healthtourism.util.base.BaseDTO;
import com.allianz.healthtourism.util.base.BaseEntity;
import com.allianz.healthtourism.util.base.BaseRequestDTO;
import jakarta.persistence.MappedSuperclass;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@MappedSuperclass
public interface IBaseMapper<Entity extends BaseEntity, DTO extends BaseDTO, RequestDTO extends BaseRequestDTO> {

    Entity dtoToEntity(DTO dto);

    DTO entityToDto(Entity entity);

    Set<DTO> entitySetToDtoSet(Set<Entity> entitySet);

    Set<Entity> dtoSetToEntitySet(Set<DTO> dtoSet);

    Entity requestDtoToEntity(RequestDTO requestDTO);

    PageDTO<DTO> pageEntityToPageDTO(Page<Entity> entityPage);

    List<DTO> entityListToDtoList(List<Entity> entitityList);
    List<Entity> dtoListToEntityList(List<DTO> dtoList);

    Entity requestDtoToExistEntity(RequestDTO requestDTO, @MappingTarget Entity entity);


}
