package au.davidwrz.notifications.infrastracture.db;

import au.davidwrz.notifications.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
