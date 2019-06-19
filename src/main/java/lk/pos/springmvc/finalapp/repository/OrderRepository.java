package lk.pos.springmvc.finalapp.repository;

import lk.pos.springmvc.finalapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o.id FROM `Order` o ORDER BY o.id DESC LIMIT 1", nativeQuery = true)
    int getLastOrderId();
}
