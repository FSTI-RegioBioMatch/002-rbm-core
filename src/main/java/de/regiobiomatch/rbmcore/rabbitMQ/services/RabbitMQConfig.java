package de.regiobiomatch.rbmcore.rabbitMQ.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String RECIPE_REQUEST_EXCHANGE = "rbm-core.recipe.exchange";
  public static final String RECIPE_REQUEST_QUEUE = "rbm-core.recipe.request.queue";
  public static final String RECIPE_REQUEST_ROUTING_KEY = "rbm-core.recipe.request";

  public static final String USER_PROFILE_REQUEST_EXCHANGE = "rbm-core.userprofile.exchange";
  public static final String USER_PROFILE_REQUEST_QUEUE = "rbm-core.userprofile.request.queue";
  public static final String USER_PROFILE_REQUEST_ROUTING_KEY = "rbm-core.userprofile.request";

  // Recipe exchange, queue, and binding
  @Bean
  public DirectExchange recipeRequestExchange() {
    return new DirectExchange(RECIPE_REQUEST_EXCHANGE);
  }

  @Bean
  public Queue recipeRequestQueue() {
    return new Queue(RECIPE_REQUEST_QUEUE);
  }

  @Bean
  public Binding recipeRequestBinding(Queue recipeRequestQueue, DirectExchange recipeRequestExchange) {
    return BindingBuilder.bind(recipeRequestQueue).to(recipeRequestExchange).with(RECIPE_REQUEST_ROUTING_KEY);
  }

  // User profile exchange, queue, and binding
  @Bean
  public DirectExchange userProfileRequestExchange() {
    return new DirectExchange(USER_PROFILE_REQUEST_EXCHANGE);
  }

  @Bean
  public Queue userProfileRequestQueue() {
    return new Queue(USER_PROFILE_REQUEST_QUEUE);
  }

  @Bean
  public Binding userProfileRequestBinding(Queue userProfileRequestQueue, DirectExchange userProfileRequestExchange) {
    return BindingBuilder.bind(userProfileRequestQueue).to(userProfileRequestExchange).with(USER_PROFILE_REQUEST_ROUTING_KEY);
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    objectMapper.deactivateDefaultTyping();
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
    return rabbitTemplate;
  }
}
