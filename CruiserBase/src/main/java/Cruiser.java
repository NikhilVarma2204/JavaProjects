import ch.qos.logback.classic.Logger;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClients.*;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cruiser {

        public static boolean adminAuthChecker(String username,String password){
            if(username.equals("foo") && password.equals("foo")){
                return true;
            }
            else
                return false;
        }

    public static void main(String[] args){

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        Logger rootLogger1 = loggerContext.getLogger("org.springframework.boot");

        rootLogger.setLevel(Level.OFF);
        rootLogger1.setLevel(Level.OFF);
        System.out.println("Welcome to the Cruiser Base\n");
        //Client connection
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        System.out.println("Mongo client connected on port 27017....\n");
        //Database
        MongoDatabase database = client.getDatabase("cruiser");

        //Collections
                 //collection-1
        MongoCollection<Document> CONNECTION_COLLECTION = database.getCollection("Acquaintances");
                //collection-2
        MongoCollection<Document> BIRTHDAY_COLLECTION = database.getCollection("birthday");
                //Collection-3
        MongoCollection<Document> SOCIAL_COLLECTION = database.getCollection("social");


        System.out.println("Now prove you are Nikhil Varma\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Username: \n");
        String username = sc.nextLine();

        System.out.println("Password: \n");
        String password = sc.nextLine();

        boolean isAuthenticated = adminAuthChecker(username,password);
       if(isAuthenticated == true){
            System.out.println("Hello Gray!!\n");
           System.out.println("Opening database...\n");
           System.out.println("Database currently in use: cruiser");

           System.out.println("********************************************************************************\n" +
                   "***************************************************************************************************");

           System.out.println("\n 1.Add a new Acquaintance\n 2. Add a birthday \n 3. Add a social document");
           int choice = sc.nextInt();

           switch (choice){
               /*
               *1. Connections
               *2. Birthdays
               *3. Social Numbers
               * */
               case 1: // Acquaintance Section
                   System.out.println("I see you made a new connection!!");
                   sc.nextLine();
                   System.out.println("Name: \n");
                   String name = sc.nextLine();
                   System.out.println("Mail: \n");
                   String mail = sc.nextLine();
                   System.out.println("Contact: \n");
                   String contact = sc.nextLine();

                   Document doc = new Document("name", name)
                           .append("mail", mail)
                           .append("contact", contact);
                   CONNECTION_COLLECTION.insertOne(doc);
                   break;
               case 2:
                   System.out.println("Enter a Birthday date here *********\n");
                   sc.nextLine();
                   System.out.println("name: \n");
                   String name_of_person = sc.nextLine();
                   System.out.println("Date: \n");
                   String date =sc.nextLine();
                   Document BIRTHDAY_DOC = new Document("name",name_of_person).append("Date",date);
                   BIRTHDAY_COLLECTION.insertOne(BIRTHDAY_DOC);
                   System.out.println("Birthday added successfully");
                   break;
               case 3:
                   System.out.println("Enter the Social Files here **********\n");
                   sc.nextLine();
                   System.out.println("Name of the file: \n");
                   String FILE_NAME = sc.nextLine();
                   System.out.println("Enter the CODE here: \n");
                   String CODE = sc.nextLine();
                   Document SOCIAL_FILE  = new Document("name",FILE_NAME)
                           .append("social_id",CODE);
                   SOCIAL_COLLECTION.insertOne(SOCIAL_FILE);
                   System.out.println("Social File added successfully");
                   break;
               case 4:
                   System.out.println("Test is successful");
           }




       }
        else{
            System.out.println("Oi!!! Who are you?...You arent the ADMIN...Now get out!!!");
        }



    }
}
