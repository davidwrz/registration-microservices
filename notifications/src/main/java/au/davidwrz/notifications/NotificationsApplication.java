package au.davidwrz.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "au.davidwrz.notifications",
                "au.davidwrz.amqp",
        }
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
class NotificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationsApplication.class, args);
    }
}
