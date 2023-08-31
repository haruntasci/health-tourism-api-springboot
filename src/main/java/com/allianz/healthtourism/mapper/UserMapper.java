package com.allianz.healthtourism.mapper;

import com.allianz.healthtourism.database.entity.User;
import com.allianz.healthtourism.model.UserDTO;
import com.allianz.healthtourism.model.requestDTO.UserRequestDTO;
import com.allianz.healthtourism.util.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends IBaseMapper<User, UserDTO, UserRequestDTO> {
}
