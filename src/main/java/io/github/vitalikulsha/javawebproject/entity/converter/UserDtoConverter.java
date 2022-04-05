package io.github.vitalikulsha.javawebproject.entity.converter;

import io.github.vitalikulsha.javawebproject.entity.User;
import io.github.vitalikulsha.javawebproject.entity.dto.UserDto;

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
