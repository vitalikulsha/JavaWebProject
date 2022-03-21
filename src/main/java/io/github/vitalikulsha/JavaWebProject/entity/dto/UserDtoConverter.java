package io.github.vitalikulsha.JavaWebProject.entity.dto;

import io.github.vitalikulsha.JavaWebProject.entity.User;
import lombok.NonNull;

public class UserDtoConverter implements DtoConverter<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto.Builder()
                .fixId(user.getId())
                .fixUserName(user.getUserName())
                .fixPhoneNumber(user.getPhoneNumber())
                .fixEmail(user.getEmail())
                .fixRole(user.getRole())
                .build();
    }
}
