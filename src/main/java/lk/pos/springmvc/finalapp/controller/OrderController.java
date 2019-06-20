package lk.pos.springmvc.finalapp.controller;


import lk.pos.springmvc.finalapp.dto.ItemDTO;
import lk.pos.springmvc.finalapp.dto.OrderDTO;
import lk.pos.springmvc.finalapp.service.custom.ItemService;
import lk.pos.springmvc.finalapp.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders(){
//        System.out.println(orderService.getAllOrders());
        return orderService.getAllOrders();
    }


    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderId") Integer orderId) {
        OrderDTO dto = null;
        if (orderService.isOrderExists(orderId)) {
            dto = orderService.getOrderById(orderId);
        }
        return new ResponseEntity<OrderDTO>(dto, (dto != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> saveOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println("order id : " + orderDTO.getOrderId());
        System.out.println("order date : " + orderDTO.getOrderDate());
        System.out.println("order customer id : " + orderDTO.getCustomerId());
        System.out.println("order detail : " + orderDTO.getOrderDetails());
        if (orderDTO.getOrderId() == 0 || orderDTO.getOrderDate() == null || orderDTO.getCustomerId().isEmpty() || orderDTO.getOrderDetails().isEmpty()) {
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        } else {
            Integer savedOrderID = orderService.placeOrder(orderDTO);
            System.out.println(savedOrderID);
//            return new ResponseEntity<Integer>(Integer.valueOf("\"+savedOrderID+\""), (savedOrderID != null) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Integer>(HttpStatus.CREATED);
        }
    }

}
