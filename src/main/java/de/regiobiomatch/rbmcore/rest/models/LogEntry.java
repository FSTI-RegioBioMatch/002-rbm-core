package de.regiobiomatch.rbmcore.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs")
public class LogEntry {

    @Id
    private String id;
    private String companyId;
    private String timestamp;  // Store timestamp in ISO 8601 format
    private String level;  // INFO, WARN, ERROR
    private String message;
    private String userId;  // Optional user identifier
    private Map<String, Object> additionalData;  // Optional JSON string for additional debug info

}