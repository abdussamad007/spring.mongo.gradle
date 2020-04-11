package com.abdus.spring.mongo.gradle.example;

import com.abdus.spring.mongo.gradle.example.model.Department;
import com.abdus.spring.mongo.gradle.example.model.Employee;
import com.abdus.spring.mongo.gradle.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,MongoAutoConfiguration.class})
public class Application implements CommandLineRunner {

 // @Autowired
  //private CustomerRepository repository;
  @Autowired
  DepartmentRepository departmentRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

@Override
  public void run(String... args) throws Exception {

	  List<Employee> employeeList = new ArrayList<>();
    Employee employee1 = Employee.builder().empId("ABC123").name("Abdus").age(40).salary(123456.00).build();
    employeeList.add(employee1);

    Department department1 = Department.builder().name("Physics").desc("Dept of Physics").employees(employeeList).build();
    departmentRepository.save(department1);

    System.out.println("------department Saved -------" + department1.toString());

   List<Department> deptList =  departmentRepository.findDepartmentByName("Physics");

  deptList.forEach(dept->System.out.println(dept.toString()));

    /*repository.deleteAll();

    // save a couple of customers
    repository.save(new Customer("Alice", "Smith"));
    repository.save(new Customer("Bob", "Smith"));

    // fetch all customers
    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");
    for (Customer customer : repository.findAll()) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer
    System.out.println("Customer found with findByFirstName('Alice'):");
    System.out.println("--------------------------------");
    System.out.println(repository.findByFirstName("Alice"));

    System.out.println("Customers found with findByLastName('Smith'):");
    System.out.println("--------------------------------");
    for (Customer customer : repository.findByLastName("Smith")) {
      System.out.println(customer);
    }
*/
  }


}



