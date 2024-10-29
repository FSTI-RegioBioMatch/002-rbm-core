package de.regiobiomatch.rbmcore.rabbitMQ.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {
    private String requestType = "GET_ALL_PROFILES";
}
