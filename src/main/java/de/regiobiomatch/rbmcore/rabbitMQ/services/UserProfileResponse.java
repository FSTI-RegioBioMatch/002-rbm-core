package de.regiobiomatch.rbmcore.rabbitMQ.services;

import de.regiobiomatch.rbmcore.rest.models.userprofile.UserProfile;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class UserProfileResponse {
    private List<UserProfile> userProfiles;
}
