/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.customermng.entities.maneger;

import com.mycompany.customermng.entities.Customer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author yuizho
 */
@Remote
public interface CustomerManegerRemote {
    List<Customer> getCustomers();
    List<Customer> getCustomersById(int id);
    void updateCustomers(Customer customer);
}
