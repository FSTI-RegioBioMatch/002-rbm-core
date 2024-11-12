package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.userprofile.UserProfileDTO;
import de.regiobiomatch.rbmcore.rest.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profiles")
@CrossOrigin(
        origins = {"https://app.regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkUserProfileExists(@RequestParam String email) {
        boolean exists = userProfileService.userProfileExists(email);
        return ResponseEntity.ok(exists);
    }

    @GetMapping
    public ResponseEntity<UserProfileDTO> getUserProfile(@RequestParam String email) {
        UserProfileDTO userProfile = userProfileService.getUserProfile(email);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserProfileDTO> saveOrUpdateUserProfile(@RequestBody UserProfileDTO userProfileDto) {
        UserProfileDTO savedOrUpdatedProfile = userProfileService.saveOrUpdateUserProfile(userProfileDto);
        return ResponseEntity.ok(savedOrUpdatedProfile);
    }
}