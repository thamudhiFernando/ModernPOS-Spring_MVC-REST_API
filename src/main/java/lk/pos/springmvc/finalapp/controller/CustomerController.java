package lk.pos.springmvc.finalapp.controller;

import lk.pos.springmvc.finalapp.dto.CustomerDTO;
import lk.pos.springmvc.finalapp.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("X-Count",customerService.cu);
//        httpHeaders.setAccessControlAllowHeaders(Arrays.asList("X-Count"));
//        httpHeaders.setAccessControlExposeHeaders(Arrays.asList("X-Count"));
//        return new ResponseEntity<List<CustomerDTO>>(customerService.getAllCustomers(),httpHeaders,HttpStatus.OK);
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{custID:C\\d{3}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("custID") String id) {
        CustomerDTO dto = null;
        if (customerService.isCustomerExists(id)){
            dto = customerService.getCustomerById(id);
        }
        System.out.println("dto : " + dto);
        return new ResponseEntity<CustomerDTO>(dto, (dto != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customer){
        System.out.println("customer :" +customer);
        if (customer.getId().isEmpty() || customer.getName().isEmpty() || customer.getAddress().isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }else{
            String customerId = customerService.saveCustomer(customer);
            return new ResponseEntity<String>("\""+customerId+"\"", (customerId != null) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id:C\\d{3}}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") String id, @RequestBody CustomerDTO customer){
        if (!customerService.isCustomerExists(id)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if (customer.getName().isEmpty() || customer.getAddress().isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else{
            customer.setId(id);
            customerService.updateCustomer(customer);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{id:C\\d{3}}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){
        if (!customerService.isCustomerExists(id)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        customerService.removeCustomer(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
