package au.davidwrz.domain;

import au.davidwrz.application.RegisterUserDto;
import au.davidwrz.clients.fraud.FraudCheckResponse;
import au.davidwrz.clients.fraud.FraudClient;
import au.davidwrz.clients.notifications.NotificationClient;
import au.davidwrz.clients.notifications.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    private final UserRepository userRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public RegisterUser(UserRepository userRepository, FraudClient fraudClient, NotificationClient notificationClient) {
        this.userRepository = userRepository;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
    }

    public void register(RegisterUserDto registerUserDto) {
        User user = createUser(registerUserDto);
        userRepository.saveAndFlush(user);

        checkIfUserIsFraudster(user);
        sendNotification(user);
    }

    private User createUser(RegisterUserDto registerUserDto) {
        return User.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .build();
    }

    private void checkIfUserIsFraudster(User user) {
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(user.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalCallerException("fraudster");
        }
    }

    private void sendNotification(User user) {
        notificationClient.sendNotification(new NotificationRequest(
                user.getId(),
                user.getEmail(),
                "User registered"));
    }
}
