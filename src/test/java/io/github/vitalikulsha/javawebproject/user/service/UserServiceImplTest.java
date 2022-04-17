package io.github.vitalikulsha.javawebproject.user.service;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Paging;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {
    UserService userService;
    DTOConverter<UserDTO, User> dtoConverter;
    List<UserDTO> userDTOList;
    Paging<UserDTO> paging;
    Pagination pagination;

    @Before
    public void init() {
        userService = ServiceFactory.instance().userService();
        dtoConverter = DTOConverterFactory.instance().userDtoConverter();
        userDTOList = DataBase.USER_TABLE.stream()
                .map(u -> dtoConverter.toDto(u))
                .collect(Collectors.toList());
        paging = new Paging<>(1, ConfigParameter.ITEMS_ON_PAGE);
        pagination = new Pagination(1, ConfigParameter.ITEMS_ON_PAGE);
    }

    @Test
    public void getById() throws ServiceException {
        UserDTO expected = userDTOList.stream()
                .filter(u -> u.getId() == 3)
                .findFirst()
                .get();
        assertEquals(expected, userService.getById(3));
        assertNotEquals(expected, userService.getById(2));
        assertNull(userService.getById(10));
    }

    @Test
    public void getAll() throws ServiceException {
        assertEquals(paging.paginate(userDTOList), userService.getAll(pagination));
        pagination.setFromIndex(10);
        assertTrue(userService.getAll(pagination).isEmpty());
    }

    @Test
    public void getUsersByRole() throws ServiceException {
        List<UserDTO> expected = userDTOList.stream()
                .filter(u -> u.getRole() == Role.READER)
                .collect(Collectors.toList());
        assertEquals(paging.paginate(expected), userService.getUsersByRole(Role.READER, pagination));
        assertNotEquals(paging.paginate(expected), userService.getUsersByRole(Role.ADMIN, pagination));
        assertTrue(userService.getUsersByRole(Role.GUEST, pagination).isEmpty());
    }

    @Test
    public void countAllAndByRole() throws ServiceException {
        long expected = userDTOList.stream()
                .filter(u -> u.getRole() == Role.READER)
                .count();
        assertEquals(expected, userService.countByRole(Role.READER));
        assertEquals(0, userService.countByRole(Role.GUEST));
        assertEquals(userDTOList.size(), userService.countAll());
    }

    @Test
    public void isExists() throws ServiceException {
        assertTrue(userService.isExists("Admin", "admin"));
        assertFalse(userService.isExists("Admin", "librarian"));
    }

    @Test
    public void getByLogin() throws ServiceException {
        UserDTO expected = DataBase.USER_TABLE.stream()
                .filter(u -> u.getLogin().equals("User"))
                .findFirst()
                .map(u -> dtoConverter.toDto(u))
                .get();
        assertEquals(expected, userService.getByLogin("User"));
        assertNotEquals(expected, userService.getByLogin("Admin"));
        assertNull(userService.getByLogin("Test"));
    }

    @Test
    public void getByEmail() throws ServiceException {
        UserDTO expected = userDTOList.stream()
                .filter(u -> u.getEmail().equals("user@gmail.com"))
                .findFirst()
                .get();
        assertEquals(expected, userService.getByEmail("user@gmail.com"));
        assertNotEquals(expected, userService.getByEmail("admin@gmail.com"));
        assertNull(userService.getByEmail("test@gmail.com"));
    }

    @Test
    public void updateMethods() throws ServiceException {
        User saveUser = new User(5, "Test", "TTtt1234", "Test", "Test",
                375291234567L, "test@test.org", Role.READER);
        assertTrue(userService.createUser(saveUser.getLogin(), saveUser.getPassword(), saveUser.getFirstName(),
                saveUser.getLastName(), saveUser.getPhoneNumber(), saveUser.getEmail()));
        assertEquals(dtoConverter.toDto(saveUser), userService.getById(5));
        assertFalse(userService.createUser(null, saveUser.getPassword(), saveUser.getFirstName(),
                saveUser.getLastName(), saveUser.getPhoneNumber(), saveUser.getEmail()));
        assertFalse(userService.createUser(saveUser.getLogin(), "test", saveUser.getFirstName(),
                saveUser.getLastName(), saveUser.getPhoneNumber(), saveUser.getEmail()));
        assertFalse(userService.createUser(saveUser.getLogin(), saveUser.getPassword(), saveUser.getFirstName(),
                "t", saveUser.getPhoneNumber(), saveUser.getEmail()));

        userService.deleteById(5);
        assertNull(userService.getById(5));

        User updateUser = DataBase.USER_TABLE.stream()
                .filter(u -> u.getId() == 3)
                .findFirst()
                .get();
        assertTrue(userService.editUser(updateUser.getFirstName(), updateUser.getLastName(),
                updateUser.getPhoneNumber(), "test@test.org", updateUser.getId()));
        assertFalse(userService.editUser(updateUser.getFirstName(), updateUser.getLastName(),
                123, updateUser.getEmail(), updateUser.getId()));
        assertFalse(userService.editUser(updateUser.getFirstName(), "t",
                updateUser.getPhoneNumber(), updateUser.getEmail(), updateUser.getId()));
        assertFalse(userService.editUser(updateUser.getFirstName(), updateUser.getLastName(),
                updateUser.getPhoneNumber(), "t@t.t", updateUser.getId()));
    }
}