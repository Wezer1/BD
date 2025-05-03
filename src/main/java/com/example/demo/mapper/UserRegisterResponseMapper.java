package com.example.demo.mapper;

import com.example.demo.dto.UserRegistrationRequestDto;
import com.example.demo.dto.UserRegistrationResponseDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserRegisterResponseMapper {

    public abstract User toEntity(UserRegistrationRequestDto userRegistrationRequestDto);

//    @Mapping(target = "password", ignore = true)
    public abstract UserRegistrationResponseDto toRegistrationResponseDto(User user);
}
