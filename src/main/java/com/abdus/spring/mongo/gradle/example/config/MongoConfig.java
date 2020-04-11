package com.abdus.spring.mongo.gradle.example.config;


import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(basePackages = "com.abdus.spring.mongo.gradle.example.repository")
public class MongoConfig  extends AbstractMongoClientConfiguration {

  //@Autowired
  //private Environment env;

  @Autowired
 private MongoProperties mongoProperties;



  @Bean
  public MongoClient mongoClient() {

    String user= mongoProperties.getUsername(); // the user name
   // String database= mongoProperties.getDatabase(); // the name of the database in which the user is defined
    char[] password= mongoProperties.getPassword(); // the password as a character array
    String host = mongoProperties.getHost();
    int port = mongoProperties.getPort();
    String authDatabase = mongoProperties.getAuthenticationDatabase();

    MongoClientSettings settings = MongoClientSettings.builder()
      .credential(MongoCredential.createCredential(user, authDatabase, password))
      .applyToClusterSettings(builder  -> {
        builder.hosts(singletonList(new ServerAddress(host, port)));
      })
      .build();

    return MongoClients.create(settings);
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(),getDatabaseName());

    return mongoTemplate;

  }


  @Override
  protected String getDatabaseName() {
    return mongoProperties.getDatabase();
  }
}
