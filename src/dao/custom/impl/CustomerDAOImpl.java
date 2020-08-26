package dao.custom.impl;

import javax.persistence.NoResultException;

import dao.CrudDAOImpl;
import dao.custom.CustomerDAO;
import entity.Customer;

public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {

  @Override
  public String getLastCustomerId() throws Exception {
    try {
      return (String) entityManager.createNativeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1").getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
