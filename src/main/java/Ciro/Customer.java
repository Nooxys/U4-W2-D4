package Ciro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Customer {
    private static Logger logger = LoggerFactory.getLogger(Customer.class);

    private long id;
    private String name;
    private int tier;

    public Customer(long id, String name, int tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && tier == customer.tier && Objects.equals(name, customer.name);
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }
}
