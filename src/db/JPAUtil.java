package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

  public static EntityManagerFactory emf = buildEntityManagerFactory();

  private static EntityManagerFactory buildEntityManagerFactory() {
    File fileProp = new File("resources/application.properties");
    Properties jpaProp = new Properties();
    try {
      jpaProp.load(new FileInputStream(fileProp));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Persistence.createEntityManagerFactory("DEP", jpaProp);
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return emf;
  }

}
