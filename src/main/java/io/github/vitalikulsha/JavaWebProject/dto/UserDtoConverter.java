package io.github.vitalikulsha.JavaWebProject.dto;

import io.github.vitalikulsha.JavaWebProject.entity.User;

public class UserDtoConverter implements DtoConverter<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        return new UserDto.Builder()
                .fixId(user.getId())
                .fixUserName(user.getUserName())
                .fixPhoneNumber(user.getPhoneNumber())
                .fixEmail(user.getEmail())
                .fixRole(user.getRole())
                .build();
    }
}
