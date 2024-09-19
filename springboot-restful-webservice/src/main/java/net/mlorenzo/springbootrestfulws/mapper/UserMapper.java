package net.mlorenzo.springbootrestfulws.mapper;

import net.mlorenzo.springbootrestfulws.dto.UserDto;
import net.mlorenzo.springbootrestfulws.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User mapToUserEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
