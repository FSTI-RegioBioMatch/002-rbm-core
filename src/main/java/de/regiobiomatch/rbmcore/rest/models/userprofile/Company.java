package de.regiobiomatch.rbmcore.rest.models.userprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String id;
    private String name;
    private String email;
    private String visibility;
    private String tenant;
}