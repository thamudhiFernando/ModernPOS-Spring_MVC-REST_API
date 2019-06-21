package lk.pos.springmvc.finalapp.service.custom.impl;

import lk.pos.springmvc.finalapp.dto.OrderDTO;
import lk.pos.springmvc.finalapp.dto.OrderDetailDTO;
import lk.pos.springmvc.finalapp.entity.Customer;
import lk.pos.springmvc.finalapp.entity.Item;
import lk.pos.springmvc.finalapp.entity.Order;
import lk.pos.springmvc.finalapp.entity.OrderDetail;
import lk.pos.springmvc.finalapp.repository.CustomerRepository;
import lk.pos.springmvc.finalapp.repository.ItemRepository;
import lk.pos.springmvc.finalapp.repository.OrderDetailRepository;
import lk.pos.springmvc.finalapp.repository.OrderRepository;
import lk.pos.springmvc.finalapp.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Integer placeOrder(OrderDTO order) {

        // Find the customer
        Customer customer = customerRepository.getOne(order.getCustomerId());
        // Save the order
        int id = orderRepository.save(new Order(order.getOrderId(), order.getOrderDate(), customer)).getId();
        //  Save OrderDetails and Update the Qty.
        for (OrderDetailDTO dto : order.getOrderDetails()) {
            orderDetailRepository.save(new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice()));
            // Find the item
            Item item = itemRepository.getOne(dto.getItemCode());
            // Calculate the qty. on hand
            int qty = item.getQtyOnHand() - dto.getQty();
            // Update the new qty.on hand
            item.setQtyOnHand(qty);
        }
        return id;
    }

    public int generateOrderId() {
        try {
            return orderRepository.getLastOrderId() + 1;
        } catch (NullPointerException e) {
            return 1;
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDetailDTO> detailDTOS = orderDetailRepository.findAll().stream().map(orderdetails -> new OrderDetailDTO(orderdetails.getOrder().getId(), orderdetails.getItem().getCode(), orderdetails.getQty(), orderdetails.getUnitPrice())).collect(Collectors.toList());
        List<OrderDTO> orderDTOS = orderRepository.findAll().stream().map(order -> new OrderDTO(order.getId(), order.getDate(), order.getCustomer().getCustID(), detailDTOS)).collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    public OrderDTO getOrderById(Integer orderID) {
        Order order = orderRepository.getOne(orderID);
        List<OrderDetailDTO> detailDTOS = orderDetailRepository.findAll().stream().map(orderdetails -> new OrderDetailDTO(orderdetails.getOrder().getId(), orderdetails.getItem().getCode(), orderdetails.getQty(), orderdetails.getUnitPrice())).collect(Collectors.toList());
        return new OrderDTO(order.getId(),order.getDate(),order.getCustomer().getCustID(),detailDTOS);
    }

    @Override
    public boolean isOrderExists(Integer orderID) {
        return orderRepository.existsById(orderID);
    }


    public String getCurrentId() {
        String id = String.valueOf(Integer.parseInt(orderDetailRepository.getTopOrderByOrderByOrderidDesc().getOrderid())+1);
        return "00"+id;
    }
}
