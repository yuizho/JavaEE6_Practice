/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.customermng.entities.maneger;

import com.mycompany.customermng.entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author yuizho
 */
@Stateless
public class CustomerManeger implements CustomerManegerLocal, CustomerManegerRemote {
    @PersistenceContext(unitName = "sample")
    EntityManager em;
    
    @Override
    public List<Customer> getCustomers() {
        return (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
    }
    
    @Override
    public List<Customer> getCustomersById(int id) {
        Query q = em.createNamedQuery("Customer.findByCustomerId");
        q.setParameter("customerId", id);
        return (List<Customer>)q.getResultList();
    }
    
    @Override
    public void updateCustomers(Customer customer) {
        em.merge(customer);
    }
}
