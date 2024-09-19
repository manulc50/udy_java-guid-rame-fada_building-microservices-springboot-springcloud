package net.mlorenzo.springbootrestfulws.service;

import net.mlorenzo.springbootrestfulws.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);
    UserDto updateUserByFields(Long id, Map<String, Object> fields);
    void deleteUserById(Long id);
}
