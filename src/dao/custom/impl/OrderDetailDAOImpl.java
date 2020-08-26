package dao.custom.impl;

import java.util.List;

import dao.CrudDAOImpl;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;
import entity.OrderDetailPK;

public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {

  @Override
  public List<OrderDetail> findAll() throws Exception {
    return entityManager.createQuery("FROM OrderDetail", OrderDetail.class).getResultList();
  }
}
