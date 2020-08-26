package dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public interface SuperDAO {
    public void setEntityManager(EntityManager entityManager);
}
