package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.userprofile.UserProfile;
import de.regiobiomatch.rbmcore.rest.models.userprofile.UserProfileDTO;
import de.regiobiomatch.rbmcore.rest.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public boolean userProfileExists(String email) {
        return userProfileRepository.existsByPersonEmail(email);
    }

    public UserProfileDTO getUserProfile(String email) {
        Optional<UserProfile> userProfile = userProfileRepository.findByPersonEmail(email);
        return userProfile.map(this::convertToDto).orElse(null);
    }

    public UserProfileDTO saveOrUpdateUserProfile(UserProfileDTO userProfileDto) {
        if (userProfileExists(userProfileDto.getPerson().getEmail())) {
            // Update existing profile
            return updateUserProfile(userProfileDto.getPerson().getEmail(), userProfileDto);
        } else {
            // Create new profile
            UserProfile userProfile = convertToEntity(userProfileDto);
            UserProfile savedProfile = userProfileRepository.save(userProfile);
            return convertToDto(savedProfile);
        }
    }

    public UserProfileDTO updateUserProfile(String email, UserProfileDTO userProfileDto) {
        Optional<UserProfile> existingProfile = userProfileRepository.findByPersonEmail(email);

        if (existingProfile.isPresent()) {
            UserProfile userProfile = existingProfile.get();
            updateProfileFromDto(userProfile, userProfileDto);
            UserProfile updatedProfile = userProfileRepository.save(userProfile);
            return convertToDto(updatedProfile);
        } else {
            return null;
        }
    }

    // Helper methods for conversion
    private UserProfileDTO convertToDto(UserProfile userProfile) {
        return new UserProfileDTO(
                userProfile.getPerson(),
                userProfile.getEmployments(),
                userProfile.getCompanies(),
                userProfile.getCompanyIds()
        );
    }

    private UserProfile convertToEntity(UserProfileDTO userProfileDto) {
        return new UserProfile(
                null,
                userProfileDto.getPerson(),
                userProfileDto.getEmployments(),
                userProfileDto.getCompanies(),
                userProfileDto.getCompanyIds()
        );
    }

    private void updateProfileFromDto(UserProfile userProfile, UserProfileDTO userProfileDto) {
        userProfile.setPerson(userProfileDto.getPerson());
        userProfile.setEmployments(userProfileDto.getEmployments());
        userProfile.setCompanies(userProfileDto.getCompanies());
        userProfile.setCompanyIds(userProfileDto.getCompanyIds());
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }
}