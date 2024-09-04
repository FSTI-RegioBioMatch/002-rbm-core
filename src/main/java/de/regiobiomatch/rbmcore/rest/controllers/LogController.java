package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logs")
@CrossOrigin(
        origins = {"https://regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @PostMapping
    public ResponseEntity<?> log(@RequestBody LogEntry logEntry, @RequestHeader("Current-Company") String currentCompany) {
        if (currentCompany == null || currentCompany.isEmpty()) {
            return ResponseEntity.badRequest().body("Current-Company header is required");
        }

        logEntry.setCompanyId(currentCompany);

        String logMessage = String.format(
                "CompanyId: %s | Timestamp: %s | Level: %s | Message: %s | UserId: %s | AdditionalData: %s",
                logEntry.getCompanyId(),
                logEntry.getTimestamp(),
                logEntry.getLevel(),
                logEntry.getMessage(),
                logEntry.getUserId(),
                logEntry.getAdditionalData()
        );

        switch (logEntry.getLevel()) {
            case "ERROR":
                logger.error(logMessage);
                break;
            case "WARN":
                logger.warn(logMessage);
                break;
            default:
                logger.info(logMessage);
                break;
        }

        // Return a success response with an empty JSON object
        return ResponseEntity.ok("{}");
    }
}
