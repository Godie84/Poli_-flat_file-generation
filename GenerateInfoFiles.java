package generateinfofiles;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * This class provides methods for generating seller, product, and sales information files.
 */
public class GenerateInfoFiles {

    /** Output directory to store the generated files. */
    private static final String OUTPUT_FOLDER = "outputFiles/";

    /**
     * Main method to run the information file generator.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GenerateInfoFiles generator = new GenerateInfoFiles();
        try {
            // Generate information files for sellers, products and sales
            generator.createSalesManInfoFile(5, "sellers.txt");
            generator.createProductsFile(10, "products.txt");
            generator.createSalesMenFile(10, "sales.txt", "sellers.txt");
        } catch (IOException e) {
            System.err.println("Error generating files: " + e.getMessage());
        }
    }

    /**
     * Create a seller information file with pseudo-random data.
     * @param salesmanCount Number of sellers to generate.
     * @param sellersFile Name of the file that contains information sellers.
     * @throws IOException If an error occurs while writing the file.
     */
    public void createSalesManInfoFile(int salesmanCount, String sellersFile) throws IOException {
        // Read seller information from the file
        List<String> sellersInfo = readLinesFromFile(sellersFile);
        if (sellersInfo.isEmpty()) {
            throw new IOException("Seller file is empty");
        }

        // Generate salespeople pseudo-randomly and write to the output file
        Random random = new Random();
        createFolderIfNotExists(OUTPUT_FOLDER);
        try (FileWriter writer = new FileWriter(OUTPUT_FOLDER + "salesman_info.csv")) {
            for (int i = 0; i < salesmanCount; i++) {
                String seller = sellersInfo.get(random.nextInt(sellersInfo.size()));
                // Simulate sales amount (random)
                double salesAmount = random.nextDouble() * 10000;
                writer.write(seller + ";" + String.format("%.2f", salesAmount) + "\n");
            }
        }
    }

    /**
     * Create a product information file with pseudo-random data.
     * @param productsCount Number of products to generate.
     * @param productsFile Name of the file containing product information products.
     * @throws IOException If an error occurs while writing the file.
     */
    public void createProductsFile(int productsCount, String productsFile) throws IOException {
        // Read product information from file
        List<String> productsInfo = readLinesFromFile(productsFile);
        if (productsInfo.isEmpty()) {
            throw new IOException("Product file is empty");
        }

        // Generate products pseudorandomly and write to the output file
        Random random = new Random();
        createFolderIfNotExists(OUTPUT_FOLDER);
        try (FileWriter writer = new FileWriter(OUTPUT_FOLDER + "products_info.csv")) {
            for (int i = 0; i < productsCount; i++) {
                String product = productsInfo.get(random.nextInt(productsInfo.size()));
                // Simulate product price (random)
                double price = random.nextDouble() * 100;
                writer.write(product + ";" + String.format("%.2f", price) + "\n");
            }
        }
    }

    /**
     * Create a sales information file with pseudo-random data.
     * @param randomSalesCount Number of random sales to generate.
     * @param salesFile Name of the file containing sales information.
     * @param sellersFile Name of the file that contains sales information sellers.
     * @throws IOException If an error occurs while writing the file.
     */
    public void createSalesMenFile(int randomSalesCount, String salesFile, String sellersFile) throws IOException {
        // Read seller information from the file
        List<String> sellersInfo = readLinesFromFile(sellersFile);
        if (sellersInfo.isEmpty()) {
            throw new IOException("Seller file is empty");
        }

        // Generate random sales and write to output file
        Random random = new Random();
        createFolderIfNotExists(OUTPUT_FOLDER);
        try (FileWriter writer = new FileWriter(OUTPUT_FOLDER + "sales_info.csv")) {

            for (int i = 0; i < randomSalesCount; i++) {
                String seller = sellersInfo.get(random.nextInt(sellersInfo.size()));
                // Simulate sales data (random)
                String saleData = generateRandomSalesData();
                writer.write(seller + ";" + saleData + "\n");
            }
        }
    }

    /**
     * Generates random sales data in the format: productName;quantity;price.
     * @return Randomly generated sales data.
     */
    private String generateRandomSalesData() {
        Random random = new Random();
        // Let's assume we have a list of product names
        List<String> productNames = Arrays.asList("keyboard_usb", "mouse", "notebook", "memory", "ipad");
        String productName = productNames.get(random.nextInt(productNames.size()));
        int quantity = random.nextInt(10) + 1; // Random quantity between 1 and 10
        double price = random.nextDouble() * 100; // Random price between 0 and 100
        return productName + ";" + quantity + ";" + String.format("%.2f", price);
    }

    /**
     * Reads all lines from a file and returns them as a list of strings.
     * @param filename Name of the file to read.
     * @return List of lines read from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    private List<String> readLinesFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Creates a folder if it does not exist in the specified path.
     * @param folderPath Path of the folder to create.
     * @throws IOException If an error occurs while creating the folder.
     */
    private void createFolderIfNotExists(String folderPath) throws IOException {
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}
