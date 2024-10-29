package de.regiobiomatch.rbmcore.rabbitMQ.services;

import de.regiobiomatch.rbmcore.rest.services.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitUserProfileService {
    private static final Logger logger = LoggerFactory.getLogger(RabbitUserProfileService.class);

    @Autowired
    private UserProfileService userProfileService;

    @RabbitListener(queues = RabbitMQConfig.USER_PROFILE_REQUEST_QUEUE)
    public UserProfileResponse handleUserProfileRequest(UserProfileRequest request) {
        logger.info("Received userProfile request: {}", request);

        if ("GET_ALL_PROFILES".equalsIgnoreCase(request.getRequestType())) {
            UserProfileResponse userProfileResponse = new UserProfileResponse(userProfileService.getAllUserProfiles());
            logger.info("Sending userProfile response: {}", userProfileResponse.toString());
            return userProfileResponse;
        }
        return new UserProfileResponse();
    }

}