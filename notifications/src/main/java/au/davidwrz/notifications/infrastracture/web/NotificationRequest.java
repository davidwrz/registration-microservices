package au.davidwrz.notifications.infrastracture.web;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message
) {
}
