package au.davidwrz.fraud.infrastracture.web;

import au.davidwrz.clients.fraud.FraudCheckResponse;
import au.davidwrz.fraud.domain.FraudCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
class FraudController {

    private final FraudCheck fraudCheck;

    FraudController(FraudCheck fraudCheck) {
        this.fraudCheck = fraudCheck;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable Integer userId) {
        boolean isFraudulentUser = fraudCheck.isFraudulentUser(userId);
        log.info("fraud check request for user{}", userId);
        return ResponseEntity.of(Optional.of(new FraudCheckResponse(isFraudulentUser)));
    }
}
