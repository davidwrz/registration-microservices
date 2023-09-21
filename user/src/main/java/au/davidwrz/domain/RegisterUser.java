package au.davidwrz.domain;

import au.davidwrz.application.RegisterUserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisterUser {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public RegisterUser(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public void register(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .build();
        userRepository.saveAndFlush(user);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8082/api/v1/fraud-check/{userId}",
                FraudCheckResponse.class,
                user.getId()
        );
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalCallerException("fraudster");
        }
        //todo send notification
    }
}
