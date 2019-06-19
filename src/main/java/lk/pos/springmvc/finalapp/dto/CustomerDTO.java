package lk.pos.springmvc.finalapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {

    @JsonProperty("custID")
    private String id;
    @JsonProperty("custName")
    private String name;
    @JsonProperty("CustAddress")
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
