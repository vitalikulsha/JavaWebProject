package io.github.vitalikulsha.javawebproject.user.entity;

import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;

public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    @Override
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO.Builder()
                .fixId(user.getId())
                .fixFirstName(user.getFirstName())
                .fixLastName(user.getLastName())
                .fixPhoneNumber(user.getPhoneNumber())
                .fixEmail(user.getEmail())
                .fixRole(user.getRole())
                .build();
    }
}
