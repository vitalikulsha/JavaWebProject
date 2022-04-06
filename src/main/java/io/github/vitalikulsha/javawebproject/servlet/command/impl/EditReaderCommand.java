package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.user.service.UserService;
import io.github.vitalikulsha.javawebproject.util.service.validator.ValidationPattern;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.JspValue;
import io.github.vitalikulsha.javawebproject.util.path.UserPath;
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
        if (method.equals(JspValue.POST)) {
            try {
                return getCommandInfoPost(request);
            } catch (ServiceException e) {
                log.error("Unable to update user: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        return new CommandInfo(Page.EDIT, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        UserDTO userDto = (UserDTO) session.getAttribute(SessionAttribute.USER);
        UserService userService = ServiceFactory.instance().userService();
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        long phoneNumber = Long.parseLong(request.getParameter(RequestParameter.PHONE_NUMBER));
        String email = request.getParameter(RequestParameter.EMAIL);
        log.info("firstName: " + firstName + ", lastName: " + lastName
                + ", phoneNumber: " + phoneNumber + ", email: " + email);
        if (!userService.editUser(firstName, lastName, phoneNumber, email, userDto.getId())) {
            log.info("Failed to update user data.");
            List<String> invalidFields = getInvalidFields(firstName, lastName, phoneNumber, email);
            request.setAttribute(SessionAttribute.INVALID_FIELD, invalidFields);
            return new CommandInfo(Page.EDIT, RoutingType.FORWARD);
        }
        UserDTO newUserDto = userService.getById(userDto.getId());
        session.setAttribute(SessionAttribute.USER, newUserDto);
        return new CommandInfo(UserPath.READER.getPath(), RoutingType.REDIRECT);
    }

    private List<String> getInvalidFields(String firstName, String lastName, long phoneNumber, String email) {
        List<String> invalidFields = new ArrayList<>();
        if (!firstName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(RequestParameter.FIRST_NAME);
        }
        if (!lastName.matches(ValidationPattern.NAME_PATTERN)) {
            invalidFields.add(RequestParameter.LAST_NAME);
        }
        if (!String.valueOf(phoneNumber).matches(ValidationPattern.PHONE_PATTERN)) {
            invalidFields.add(RequestParameter.PHONE_NUMBER);
        }
        if (!email.matches(ValidationPattern.EMAIL_PATTERN)) {
            invalidFields.add(RequestParameter.EMAIL);
        }
        return invalidFields;
    }
}
