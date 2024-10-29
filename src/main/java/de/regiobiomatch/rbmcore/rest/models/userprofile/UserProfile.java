package de.regiobiomatch.rbmcore.rest.models.userprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_profiles")
public class UserProfile {
    @Id
    private String id;
    private Person person;
    private List<Employment> employments;
    private List<Company> companies;
    private List<String> companyIds;
}