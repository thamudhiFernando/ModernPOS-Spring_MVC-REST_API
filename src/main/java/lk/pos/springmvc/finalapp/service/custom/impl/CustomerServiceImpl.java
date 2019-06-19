package lk.pos.springmvc.finalapp.service.custom.impl;

import lk.pos.springmvc.finalapp.dto.CustomerDTO;
import lk.pos.springmvc.finalapp.entity.Customer;
import lk.pos.springmvc.finalapp.repository.CustomerRepository;
import lk.pos.springmvc.finalapp.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomerById(String id) {
        Customer customer = customerRepository.getOne(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getCustID(), customer.getCustName(), customer.getCustAddress());
        return customerDTO;
    }

    @Override
    public boolean isCustomerExists(String id) {
        return customerRepository.existsById(id);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = customerRepository.findAll().stream().map(customer -> new CustomerDTO(customer.getCustID(), customer.getCustName(), customer.getCustAddress())).collect(Collectors.toList());
        return customers;
    }

    public String saveCustomer(CustomerDTO dto) {
        boolean customerExists = isCustomerExists(dto.getId());
        if (customerExists){
            return null;
        }
        return customerRepository.save(new Customer(dto.getId(), dto.getName(), dto.getAddress())).getCustID();
    }

    public void updateCustomer(CustomerDTO dto) {
        customerRepository.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
    }

    public void removeCustomer(String id) {
        customerRepository.deleteById(id);
    }

}
