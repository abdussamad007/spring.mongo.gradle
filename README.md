# This example is to show the Springboot , MongoDB with Gradle integration. 

### Requirements
  * Ensure you have MongoDB up and running. To install and configure MongoDB , follow the instructions in https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/ 
  * Ensure you have the user id and db created . I used the following scripts from the Mongo Client 
    Robo3t    
    
    ```
    use admin

    db.createUser( { user: "root",
                     pwd: "root",
                     roles: [ { role: "readWrite", db: "db_test" }],
                       } );
    
    ```
    * For SpringBoot used https://start.spring.io/
    

### AES Passwprd Encryption reference : https://howtodoinjava.com/security/aes-256-encryption-decryption/
