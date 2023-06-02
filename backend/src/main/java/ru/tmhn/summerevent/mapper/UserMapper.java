package ru.tmhn.summerevent.mapper;

import org.springframework.stereotype.Component;
import ru.tmhn.summerevent.dto.UserDto;
import ru.tmhn.summerevent.model.User;

@Component
public class UserMapper {

    public User map(UserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto map(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
