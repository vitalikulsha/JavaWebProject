package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.service.validator.ValidationPattern;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.constant.Value;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EditReaderCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals(Value.POST)) {
            return getCommandInfoPost(request);
        }
        return new CommandInfo(Page.EDIT, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(Attribute.USER);
        UserService userService = ServiceFactory.instance().userService();
        String firstName = request.getParameter(Parameter.FIRST_NAME);
        String lastName = request.getParameter(Parameter.LAST_NAME);
        long phoneNumber = Long.parseLong(request.getParameter(Parameter.PHONE_NUMBER));
        String email = request.getParameter(Parameter.EMAIL);
        log.info("firstName: " + firstName + ", lastName: " + lastName
                + ", phoneNumber: " + phoneNumber + ", email: " + email);
        if (!userService.editUser(firstName, lastName, phoneNumber, email, userDto.getId())) {
            log.info("Failed to update user data.");
            List<String> invalidFields = getInvalidFields(firstName, lastName, phoneNumber, email);
            request.setAttribute(Attribute.INVALID_FIELD, invalidFields);
            return new CommandInfo(Page.EDIT, RoutingType.FORWARD);
        }
        UserDto newUserDto = userService.getById(userDto.getId());
        session.setAttribute(Attribute.USER, newUserDto);
        return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
    }

    private List<String> getInvalidFields(String firstName, String lastName, long phoneNumber, String email) {
        List<String> invalidFields = new ArrayList<>();
        if (!firstName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(Parameter.FIRST_NAME);
        }
        if (!lastName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(Parameter.LAST_NAME);
        }
        if (!String.valueOf(phoneNumber).matches(ValidationPattern.PHONE_PATTERN)) {
            invalidFields.add(Parameter.PHONE_NUMBER);
        }
        if (!email.matches(ValidationPattern.EMAIL_PATTERN)) {
            invalidFields.add(Parameter.EMAIL);
        }
        return invalidFields;
    }
}
