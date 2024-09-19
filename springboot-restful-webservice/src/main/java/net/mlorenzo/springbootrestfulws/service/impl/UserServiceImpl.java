package net.mlorenzo.springbootrestfulws.service.impl;

import lombok.RequiredArgsConstructor;
import net.mlorenzo.springbootrestfulws.dto.UserDto;
import net.mlorenzo.springbootrestfulws.exception.ResourceNotFoundException;
import net.mlorenzo.springbootrestfulws.repository.UserRepository;
import net.mlorenzo.springbootrestfulws.entity.User;
import net.mlorenzo.springbootrestfulws.exception.EmailAlreadyExistsException;
import net.mlorenzo.springbootrestfulws.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = UserMapper.mapToUserEntity(userDto);
        User user = modelMapper.map(userDto, User.class);
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email already exists for User");
        User savedUser = userRepository.save(user);
        //return UserMapper.mapToUserDto(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                // Versión simplificada de la expresión "user -> UserMapper.mapToUserDto(user)"
                //.map(UserMapper::mapToUserDto)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                // Versión simplificada de la expresión "user -> UserMapper.mapToUserDto(user)"
                //.map(UserMapper::mapToUserDto)
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    BeanUtils.copyProperties(userDto, user, "id");
                    //return UserMapper.mapToUserDto(userRepository.save(user));
                    return modelMapper.map(userRepository.save(user), UserDto.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDto updateUserByFields(Long id, Map<String, Object> fields) {
        return userRepository.findById(id)
                .map(user -> {
                    fields.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(User.class, key);
                        if(field != null) {
                            field.setAccessible(true);
                            ReflectionUtils.setField(field, user, value);
                        }
                    });
                    return modelMapper.map(userRepository.save(user), UserDto.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }
}
