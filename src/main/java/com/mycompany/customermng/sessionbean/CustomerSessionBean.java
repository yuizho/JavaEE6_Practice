/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.customermng.sessionbean;

import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.mycompany.customermng.entities.Customer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author yuizho
 */
@Stateless
@Path("/customers")
public class CustomerSessionBean {
    @GET
    @Path("/getEntityName")
    @Produces("text/plain")
    public String getEntityName() {
        return "Customer";
    }
    
    @GET
    @Path("/getCustomerXml/{id}")
    @Produces("application/xml")
    public Customer getCustomerXml(@PathParam("id") Integer id) {
        return (Customer)
                em.createNamedQuery("Customer.findByCustomerId")
                        .setParameter("customerId", id)
                        .getSingleResult();
    }
    
    @GET
    @Path("/getCustomerJson/{id}")
    @Produces("application/json")
    public Customer getCustomerJson(@PathParam("id") Integer id) {
        return (Customer)
                em.createNamedQuery("Customer.findByCustomerId")
                        .setParameter("customerId", id)
                        .getSingleResult();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "sample")
    EntityManager em;
    
    //@Resource
    //UserTransaction utx;
    
    public List<Customer> getCustomers() {
        return (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
    }
    
    public List<Customer> getCustomersById(int id) {
        Query q = em.createNamedQuery("Customer.findByCustomerId");
        q.setParameter("customerId", id);
        return (List<Customer>)q.getResultList();
    }
    
    public List<Customer> getCustomersByIdAndName(int id, String name){
        Query q = em.createNamedQuery("Customer.findByIdAndName")
                .setParameter("customerId", id)
                .setParameter("name", name);
        return (List<Customer>)q.getResultList();
    }
    
    public void updateCustomers(Customer customer) {
        em.merge(customer);
    }
    
    @AroundInvoke
    public Object print(InvocationContext ic) throws Exception {
        System.out.println(ic.getMethod().toString() + "を実行するよ！！");
        return ic.proceed();
    }
}
