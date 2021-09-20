package com.codegym.repository.customer.impl;


import com.codegym.model.Customer;
import com.codegym.repository.customer.ICustomerRepository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where  c.id=:id", Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Customer> findAll() {
        String qr= "SELECT c FROM Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(qr,Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId()!=null){
            entityManager.merge(customer);
        }else {
            entityManager.persist(customer);
        }
    }
    @Override
    public void remote(Long id) {
        Customer customer = findById(id);
        if(customer!=null){
            entityManager.remove(customer);
        }
    }
}
