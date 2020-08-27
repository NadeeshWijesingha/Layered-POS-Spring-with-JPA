package lk.ijse.dep.pos.dao.custom.impl;

import java.util.List;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.entity.OrderDetail;
import lk.ijse.dep.pos.entity.OrderDetailPK;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {

  @Override
  public List<OrderDetail> findAll() throws Exception {
    return entityManager.createQuery("FROM OrderDetail", OrderDetail.class).getResultList();
  }
}
