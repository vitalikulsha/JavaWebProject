package io.github.vitalikulsha.JavaWebProject.entity.converter;

import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;

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
