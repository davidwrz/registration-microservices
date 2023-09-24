package au.davidwrz.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        value = "notifications"
)
public interface NotificationClient {

    @PostMapping("api/v1/notifications/send")
    void sendNotification(NotificationRequest notificationRequest);
}
