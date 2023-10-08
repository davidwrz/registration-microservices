package au.davidwrz.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping("api/v1/notifications/send")
    void sendNotification(NotificationRequest notificationRequest);
}
