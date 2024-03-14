package Ciro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    private static Logger logger = LoggerFactory.getLogger(Order.class);

    private long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(long id, String status, LocalDate orderDate, List<Product> products, Customer customer) {
        this.id = id;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = orderDate.plusDays(7);
        this.products = products;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(status, order.status) && Objects.equals(orderDate, order.orderDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(products, order.products) && Objects.equals(customer, order.customer);
    }



    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }
}
