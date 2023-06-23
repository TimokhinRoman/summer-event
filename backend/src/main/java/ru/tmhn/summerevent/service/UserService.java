package ru.tmhn.summerevent.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tmhn.summerevent.dto.UserDetailsImpl;
import ru.tmhn.summerevent.dto.UserDto;
import ru.tmhn.summerevent.exception.PasswordDoesNotMatchException;
import ru.tmhn.summerevent.exception.UserAlreadyExistException;
import ru.tmhn.summerevent.mapper.UserMapper;
import ru.tmhn.summerevent.model.User;
import ru.tmhn.summerevent.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public UserDto register(UserDto dto) {
        // todo: validate

        if (!dto.getPasswordConfirmation().equals(dto.getPassword())) {
            throw new PasswordDoesNotMatchException();
        }

        if (isEmailExist(dto.getEmail())) {
            throw new UserAlreadyExistException(dto.getEmail());
        }

        User user = userMapper.map(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        int id = userRepository.addUser(user);
        user.setId(id);
        return userMapper.map(user);
    }

    public UserDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return userMapper.map(userDetails.getUser());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserDetailsImpl(user);
    }

    private boolean isEmailExist(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }
}
