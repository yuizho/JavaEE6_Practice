/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.customermng.sessionbean;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import com.mycompany.customermng.entities.Customer;
import com.mycompany.customermng.entities.maneger.CustomerManegerLocal;
import javax.inject.Inject;
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
    @Inject CustomerManegerLocal cm;
    
    @GET
    @Path("/getEntityName")
    @Produces("text/plain")
    public String getEntityName() {
        return "Customer";
    }
    
    @GET
    @Path("/unnko/{id}")
    @Produces("application/json")
    public Customer changeUnnko(@PathParam("id") Integer id) {
        Customer target = cm.getCustomersById(id).get(0);
        target.setName("うんこ");
        cm.updateCustomers(target);
        return target;
    }
    
    @GET
    @Path("/getCustomerXml/{id}")
    @Produces("application/xml")
    public Customer getCustomerXml(@PathParam("id") Integer id) {
        return (Customer) cm.getCustomersById(id).get(0);
    }
    
    @GET
    @Path("/getCustomerJson/{id}")
    @Produces("application/json")
    public Customer getCustomerJson(@PathParam("id") Integer id) {
        return (Customer) cm.getCustomersById(id).get(0);
    }
    
    @AroundInvoke
    public Object print(InvocationContext ic) throws Exception {
        System.out.println(ic.getMethod().toString() + "を実行するよ！！");
        return ic.proceed();
    }
}
