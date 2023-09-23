package au.davidwrz.domain;

import au.davidwrz.application.RegisterUserDto;
import au.davidwrz.clients.fraud.FraudCheckResponse;
import au.davidwrz.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisterUser {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public RegisterUser(UserRepository userRepository, RestTemplate restTemplate, FraudClient fraudClient) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
    }

    public void register(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .build();
        userRepository.saveAndFlush(user);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(user.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalCallerException("fraudster");
        }
        //todo send notification
    }
}
