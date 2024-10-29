package de.regiobiomatch.rbmcore.rabbitMQ.services;

import de.regiobiomatch.rbmcore.rest.services.NewRecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitRecipeService {

  private static final Logger logger = LoggerFactory.getLogger(RabbitRecipeService.class);

  @Autowired
  private NewRecipeService newRecipeService;

  @RabbitListener(queues = RabbitMQConfig.RECIPE_REQUEST_QUEUE)
  public RecipeResponse handleRecipeRequest(RecipeRequest request) {
    logger.info("Received recipe request: {}", request);

    if ("GET_ALL_RECIPES".equalsIgnoreCase(request.getRequestType())) {
      RecipeResponse recipeResponse = new RecipeResponse(newRecipeService.getAllRecipes());
      logger.info("Sending recipe response: {}", recipeResponse.toString());
      return recipeResponse;
    }
    return new RecipeResponse();
  }
}
