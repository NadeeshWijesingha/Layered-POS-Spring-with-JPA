package business.custom.impl;

import business.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import db.JPAUtil;
import entity.Item;
import util.ItemTM;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {


    private final ItemDAO itemDAO;

    public ItemBOImpl() {
        this.itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
    }

    public String getNewItemCode() throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(em);
        String lastItemCode = null;

        try {
            em.getTransaction().begin();
            lastItemCode = itemDAO.getLastItemCode();
            em.getTransaction().commit();

            if (lastItemCode == null) {
                return "I001";
            } else {
                int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "I00" + maxId;
                } else if (maxId < 100) {
                    id = "I0" + maxId;
                } else {
                    id = "I" + maxId;
                }
                return id;
            }
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        } finally {
            em.close();
        }


    }

    public List<ItemTM> getAllItems() throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(em);
        List<Item> allItems = null;

        try {
            em.getTransaction().begin();
            allItems = itemDAO.findAll();
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        } finally {
            em.close();
        }

        List<ItemTM> items = new ArrayList<>();
        for (Item item : allItems) {
            items.add(new ItemTM(item.getCode(), item.getDescription(), item.getQtyOnHand(),
                    item.getUnitPrice().doubleValue()));
        }
        return items;
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(em);

        try {
            em.getTransaction().begin();
            itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        } finally {
            em.close();
        }
    }

    public void deleteItem(String itemCode) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(em);

        try {
            em.getTransaction().begin();
            itemDAO.delete(itemCode);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        } finally {
            em.close();
        }
    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(em);

        try {
            em.getTransaction().begin();
            itemDAO.update(new Item(itemCode, description,
                    BigDecimal.valueOf(unitPrice), qtyOnHand));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        } finally {
            em.close();
        }
    }
}
