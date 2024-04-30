import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    private static final String INPUT_FOLDER = "input/";
    private static final String OUTPUT_FOLDER = "output/";

    public static void main(String[] args) {
        try {
            createOutputFolder(); // Create output folder if not exists

            // Load products and salesmen information
            List<Product> products = readProductsInfoFromFile(INPUT_FOLDER + "products.txt");
            List<Salesman> salesmen = readSalesmanInfoFromFile(INPUT_FOLDER + "salesmen.txt");

            // Generate random sales
            generateRandomSales(products, salesmen);

            System.out.println("Random sales generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating random sales: " + e.getMessage());
        }
    }

    // Create output folder if it doesn't exist
    private static void createOutputFolder() throws IOException {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    // Generate random sales and write to CSV file
    private static void generateRandomSales(List<Product> products, List<Salesman> salesmen) throws IOException {
        try (PrintWriter writer = new PrintWriter(OUTPUT_FOLDER + "random_sales.csv")) {
            // Write headers to CSV file
            writer.println("Salesman ID;Product ID;Quantity Sold;Total Price");

            // Generate random sales
            Random random = new Random();
            for (Salesman salesman : salesmen) {
                double totalSalesPrice = 0;
                for (Product product : products) {
                    int quantitySold = random.nextInt(10) + 1; // Random sale between 1 and 10 units
                    double totalPrice = quantitySold * product.getPrice(); // Calculate total price
                    totalSalesPrice += totalPrice; // Add to total sales for the salesman
                    writer.println(salesman.getName() + ";" + product.getName() + ";" + quantitySold + ";" + String.format("%.2f", totalPrice));
                }
                writer.println("Total for " + salesman.getName() + ";;;" + String.format("%.2f", totalSalesPrice));
                writer.println(); // Add a blank line between each salesman
            }
        }
    }

    // Read products information from file
    private static List<Product> readProductsInfoFromFile(String filename) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0]; // Get product name
                double price = Double.parseDouble(parts[2]); // Get product price
                products.add(new Product(name, price));
            }
        }
        return products;
    }

    // Read salesmen information from file
    private static List<Salesman> readSalesmanInfoFromFile(String filename) throws IOException {
        List<Salesman> salesmen = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0];
                salesmen.add(new Salesman(name));
            }
        }
        return salesmen;
    }
}

class Salesman {

    private String name;

    public Salesman(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
