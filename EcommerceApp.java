// E-commerce Product Management Application
import java.io.*;
import java.util.*;

// Product class
class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: " + price;
    }
}

// Customer class
class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}

// Order class
class Order {
    private String orderId;
    private Customer customer;
    private List<Product> products;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotalCost() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void saveOrderToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_history.txt", true))) {
            writer.write("Order ID: " + orderId + "\n");
            writer.write("Customer: " + customer.getName() + "\n");
            writer.write("Products:\n");
            for (Product product : products) {
                writer.write(" - " + product.getName() + " ($" + product.getPrice() + ")\n");
            }
            writer.write("Total Cost: $" + calculateTotalCost() + "\n\n");
            System.out.println("Order saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " + customer.getName() + ", Total Cost: $" + calculateTotalCost();
    }
}

// Main class
public class ECommerceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample products and customers
        Product product1 = new Product("P001", "Laptop", 800.0);
        Product product2 = new Product("P002", "Smartphone", 500.0);
        Product product3 = new Product("P003", "Headphones", 50.0);

        Customer customer1 = new Customer("C001", "Alice");
        Customer customer2 = new Customer("C002", "Bob");

        // Order creation
        Order order1 = new Order("O001", customer1);
        order1.addProduct(product1);
        order1.addProduct(product3);

        Order order2 = new Order("O002", customer2);
        order2.addProduct(product2);

        // Display orders
        System.out.println(order1);
        System.out.println(order2);

        // Save orders to file
        order1.saveOrderToFile();
        order2.saveOrderToFile();

        scanner.close();
    }
}