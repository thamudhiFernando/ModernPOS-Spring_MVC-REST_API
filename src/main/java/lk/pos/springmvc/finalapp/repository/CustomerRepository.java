package lk.pos.springmvc.finalapp.repository;

import lk.pos.springmvc.finalapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
/*
    Customer getTop1CustomersByOrderByAddressDesc();

    List<Customer> getCustomersByNameLike(String s);

    @Query(value = "select c FROM Customer c WHERE c.name like ?#{[0]}")
    List<Customer> getCustomerNameStartWithT(String letter);

    List<Customer> getCustomersByNameLikeAndAddressLikeOrderByIdDesc(String name, String address);

    @Query(value = "SELECT c FROM Customer c WHERE c.name like ?#{#name + '%'} and c.address like ?#{#address + '%'}")
    List<Customer> getCustomerIdUsingNameAndAddress(@Param("name") String name, @Param("address") String address);
*/
}
