package au.davidwrz.domain;

import au.davidwrz.application.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .build();
        userRepository.save(user);
    }
}
