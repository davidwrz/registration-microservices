package au.davidwrz.user.domain;

import au.davidwrz.amqp.RabbitMQMessageProducer;
import au.davidwrz.clients.fraud.FraudCheckResponse;
import au.davidwrz.clients.fraud.FraudClient;
import au.davidwrz.clients.notifications.NotificationRequest;
import au.davidwrz.user.application.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    private final UserRepository userRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer messageProducer;

    public RegisterUser(UserRepository userRepository, FraudClient fraudClient, RabbitMQMessageProducer messageProducer) {
        this.userRepository = userRepository;
        this.fraudClient = fraudClient;
        this.messageProducer = messageProducer;
    }

    public void register(RegisterUserDto registerUserDto) {
        User user = createUser(registerUserDto);
        userRepository.saveAndFlush(user);

//        checkIfUserIsFraudster(user);
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
        NotificationRequest notificationRequest = new NotificationRequest(
                user.getId(),
                user.getEmail(),
                "User registered");
        messageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }
}
