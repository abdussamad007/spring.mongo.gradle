package com.abdus.spring.mongo.gradle.example.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@ToString
@Document("Employee")
@Builder
@Setter
@Getter(AccessLevel.PUBLIC)
public class Employee {

  @Id
  String empId;
  String name;
  int age;
  double salary;
}
