package lk.ijse.dep.pos.dao.custom.impl;

import javax.persistence.NoResultException;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.entity.Customer;

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
