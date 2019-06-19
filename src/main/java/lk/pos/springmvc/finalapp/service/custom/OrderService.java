package lk.pos.springmvc.finalapp.service.custom;

import lk.pos.springmvc.finalapp.dto.OrderDTO;
import lk.pos.springmvc.finalapp.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {

    public Integer placeOrder(OrderDTO order);

    public int generateOrderId();

    public List<OrderDTO> getAllOrders();

    public OrderDTO getOrderById(Integer orderID);

    boolean isOrderExists(Integer orderID);
}
