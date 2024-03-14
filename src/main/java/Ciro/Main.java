package Ciro;


import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Faker faker = new Faker();

        //       SUPPLIER

        Supplier<Product> productSupplier = () -> {

            Random random = new Random();
            long randomId = random.nextLong(10000, 100000);
            double randomPrice = random.nextDouble(1, 500.00);
            int randomCategory = random.nextInt(0, 4);
            String name =faker.leagueOfLegends().rank();
            List<String> categories = new ArrayList<>();
            categories.add("Baby");
            categories.add("Boys");
            categories.add("Books");
            categories.add("Girls");

            return new Product(randomId,name, categories.get(randomCategory), randomPrice);

        };


        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            productList.add(productSupplier.get());

        }


        Supplier<Customer> customerSupplier = () -> {
            Random random = new Random();
            long randomId = random.nextLong(10000, 100000);
            int randomTier = random.nextInt(1, 3);
            String name = faker.leagueOfLegends().champion();
            return new Customer(randomId,name, randomTier);
        };

        Supplier<Order> orderSupplier = () -> {
            Random random = new Random();
            long randomId = random.nextLong(10000, 100000);
            int randomProducts = random.nextInt(1, 3);
            int randomStatus = random.nextInt(0, 2);
            int randomDate = random.nextInt(0, 4);
            List<String> statusList = new ArrayList<>();
            statusList.add("pending");
            statusList.add("not in charge yet");
            statusList.add("charged");
            List<LocalDate> orderDate = new ArrayList<>();
            orderDate.add(LocalDate.now());
            orderDate.add(LocalDate.now().plusDays(1));
            orderDate.add(LocalDate.now().minusDays(1));
            orderDate.add(LocalDate.parse("2024-02-11"));

            Customer randomCustomer = customerSupplier.get();
            List<Product> randomProduct = new ArrayList<>();
            for (int i = 0; i < randomProducts; i++) {
                randomProduct.add(productSupplier.get());

            }
            return new Order(randomId, statusList.get(randomStatus), orderDate.get(randomDate), randomProduct, randomCustomer);


        };
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            orderList.add(orderSupplier.get());
        }


//        productList.forEach(product -> logger.info(String.valueOf(product)));

        //        EXERCISE 1

        Map<Customer, List<Order>> ordersByCustomers = orderList
                .stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer()));

        ordersByCustomers.forEach((customer, orders) -> logger.info("customer " + customer.getName() + ", " + "order " + orders));


        //       EXERCISE 2

        Map<Customer, Double> totalSalesbyCustomer = orderList
                .stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer(), Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));

        totalSalesbyCustomer.forEach(((customer, aDouble) -> logger.info("customer " + customer.getName() + " ,total sales " + aDouble)));

        //       EXERCISE 3

        List<Product> mostExpensiveProducts = productList.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(10)
                .toList();
        mostExpensiveProducts.forEach(product -> logger.info(String.valueOf(product)));


        //       EXERCISE 4

        double averageOrders = orderList.stream()
                .mapToDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()).average().getAsDouble();
        logger.info("average price: " + averageOrders);


        //      EXERCISE 5

        Map<String, Double> totalSumByproducts = productList.stream()
                .collect(Collectors.groupingBy(order -> order.getCategory(), Collectors.summingDouble(product -> product.getPrice())));
        totalSumByproducts.forEach((s, aDouble) -> logger.info("category: " + s + "total: " + aDouble ));


        //      EXERCISE 6



        //      EXERCISE 7



    }


}