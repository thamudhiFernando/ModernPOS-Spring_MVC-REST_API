package lk.pos.springmvc.finalapp.service.custom;

import lk.pos.springmvc.finalapp.dto.CustomerDTO;
import lk.pos.springmvc.finalapp.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {

    public List<CustomerDTO> getAllCustomers() ;

    public String saveCustomer(CustomerDTO dto) ;

    public void updateCustomer(CustomerDTO dto) ;

    public void removeCustomer(String id) ;

    public CustomerDTO getCustomerById(String id);

    boolean isCustomerExists(String id);

}
