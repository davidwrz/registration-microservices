package au.davidwrz.notifications.application.rabbitmq;

import au.davidwrz.notifications.domain.SendNotification;
import au.davidwrz.notifications.infrastracture.web.NotificationRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final SendNotification sendNotification;

    public NotificationConsumer(SendNotification sendNotification) {
        this.sendNotification = sendNotification;
    }

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        sendNotification.send(notificationRequest);
    }
}
