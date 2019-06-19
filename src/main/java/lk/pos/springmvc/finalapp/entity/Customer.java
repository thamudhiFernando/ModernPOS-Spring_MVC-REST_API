package lk.pos.springmvc.finalapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//cannot write SpEL in here
//@NamedQuery(custName = "Customer.getCustomerNameStartWithA",query = "SELECT c.custName FROM Customer c Where c.custName Like ?#{[0]}")
@Entity
public class Customer extends SuperEntity {

    @Id
    private String custID;
    private String custName;
    private String CustAddress;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String custID, String custName, String CustAddress) {
        this.custID = custID;
        this.custName = custName;
        this.CustAddress = CustAddress;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        this.CustAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custID='" + custID + '\'' +
                ", custName='" + custName + '\'' +
                ", CustAddress='" + CustAddress + '\'' +
                '}';
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        this.getOrders().add(order);
        order.setCustomer(this);
    }

}
