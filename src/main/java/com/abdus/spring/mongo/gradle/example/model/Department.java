package com.abdus.spring.mongo.gradle.example.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@ToString
@Builder
@Setter
@Getter(AccessLevel.PUBLIC)
@Document("Department")
public class Department {
  @Id
  String id;

  @Indexed(name = "deptName")
  String name;
  String desc;
  List employees;

}
