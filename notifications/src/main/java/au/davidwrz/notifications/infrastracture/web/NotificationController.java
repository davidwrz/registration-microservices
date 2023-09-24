package au.davidwrz.notifications.infrastracture.web;

import au.davidwrz.notifications.domain.SendNotification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/notifications")
class NotificationController {

    private final SendNotification sendNotification;

    NotificationController(SendNotification sendNotification) {
        this.sendNotification = sendNotification;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(NotificationRequest notificationRequest) {
        sendNotification.save(notificationRequest);
        return ResponseEntity.ok().build();
    }
}
