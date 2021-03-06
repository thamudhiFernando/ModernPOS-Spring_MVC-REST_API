package lk.pos.springmvc.finalapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="`Order`")
public class Order extends SuperEntity {

    @Id
    private int id;
    private String date;
    @ManyToOne
    @JoinColumn(name="customerId",referencedColumnName = "custID")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {
    }

    public Order(int id, String date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", customer='" + customer + '\'' +
                '}';
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

}
