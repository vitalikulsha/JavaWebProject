package io.github.vitalikulsha.javawebproject.user.entity.dto;

import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DtoConverter;

public class UserDtoConverter implements DtoConverter<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto.Builder()
                .fixId(user.getId())
                .fixFirstName(user.getFirstName())
                .fixLastName(user.getLastName())
                .fixPhoneNumber(user.getPhoneNumber())
                .fixEmail(user.getEmail())
                .fixRole(user.getRole())
                .build();
    }
}
