package au.davidwrz.domain;

import au.davidwrz.application.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    public void register(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .build();
    }
}
