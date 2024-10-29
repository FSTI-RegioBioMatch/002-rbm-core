package de.regiobiomatch.rbmcore.rest.models.userprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private Person person;
    private List<Employment> employments;
    private List<Company> companies;
    private List<String> companyIds;
}