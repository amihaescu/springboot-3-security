package com.amihaescu.hellosecurity.service;

import com.amihaescu.hellosecurity.controller.dto.UserDto;
import com.amihaescu.hellosecurity.model.UserModel;
import com.amihaescu.hellosecurity.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                       .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDetails createUser(UserDto userDto) {
        UserModel userDetails = new UserModel(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDetails);
    }
}
