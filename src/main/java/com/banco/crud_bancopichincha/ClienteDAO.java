package com.banco.crud_bancopichincha;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
public class ClienteDAO {
    public void guardar(Cliente cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaccion = session.beginTransaction();
            session.persist(cliente);
            transaccion.commit();
        }
    }
    public List<Cliente> listar() {
        List<Cliente> clientes;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            clientes = session
                    .createQuery("FROM Cliente", Cliente.class)
                    .list();
        }
        return clientes;
    }
    public Cliente buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Cliente.class, id);
        }
    }
    public void actualizar(Cliente cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaccion = session.beginTransaction();
            session.merge(cliente);
            transaccion.commit();
        }
    }
    public void eliminar(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaccion = session.beginTransaction();
            Cliente cliente = session.find(Cliente.class, id);
            if (cliente != null) {
                session.remove(cliente);
            }
            transaccion.commit();
        }
    }
}
