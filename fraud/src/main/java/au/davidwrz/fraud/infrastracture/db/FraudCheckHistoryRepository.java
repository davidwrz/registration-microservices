package au.davidwrz.fraud.infrastracture.db;

import au.davidwrz.fraud.domain.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
