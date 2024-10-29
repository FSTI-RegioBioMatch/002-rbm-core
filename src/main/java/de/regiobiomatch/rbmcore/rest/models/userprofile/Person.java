package de.regiobiomatch.rbmcore.rest.models.userprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstname;
    private String lastname;
    private String email;
    private String messageTermsStatus;
    private String language;
    private boolean trackingEnabled;
}
