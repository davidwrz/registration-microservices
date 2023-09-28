package au.davidwrz.notifications.domain;

import au.davidwrz.notifications.infrastracture.db.NotificationRepository;
import au.davidwrz.notifications.infrastracture.web.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class SendNotification {

    private final NotificationRepository repository;

    public SendNotification(NotificationRepository repository) {
        this.repository = repository;
    }

    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .build();
        repository.save(notification);
    }
}
