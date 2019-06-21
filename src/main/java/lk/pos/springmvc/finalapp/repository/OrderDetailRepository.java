package lk.pos.springmvc.finalapp.repository;

import lk.pos.springmvc.finalapp.entity.OrderDetail;
import lk.pos.springmvc.finalapp.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
    public OrderDetail getTopOrderByOrderByOrderidDesc();
}
