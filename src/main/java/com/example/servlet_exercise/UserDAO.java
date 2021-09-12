package com.example.servlet_exercise;

import java.util.*;

import org.hibernate.*;

public class UserDAO {
    public List<User> listUser() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnection.getInstance().getSession();
            Query query = session.createQuery("from User");
            tx = session.beginTransaction();
            List queryList = query.list();
            tx.commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<User>) queryList;
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    public void updateUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnection.getInstance().getSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);

            tx.commit();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public User addUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnection.getInstance().getSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    public void deleteUser(Long id) {
        Session session = null;
        try {
            session = HibernateConnection.getInstance().getSession();
            Transaction tx = session.beginTransaction();
            Query createQuery = session.createQuery("delete from User u where u.id =:id");
            createQuery.setParameter("id", id);
            createQuery.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
