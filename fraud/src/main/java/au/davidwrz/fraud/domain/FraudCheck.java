package au.davidwrz.fraud.domain;

import au.davidwrz.fraud.infrastracture.db.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheck {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    FraudCheck(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentUser(Integer userId) {
        //TODO implement 3rd party fraud check
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder().
                        userId(userId).
                        isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
