/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generateinfofiles;

/**
 *
 * @author diego reina, rosa ospino, andrea agudelo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class provides methods for generating seller, product, and sales
 * information files.
 */
public class GenerateInfoFiles {

    /**
     * Main method to run the information file generator.
     *
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
     *
     * Output directory to store the generated files.
     */
    private static final String OUTPUT_FOLDER = "outputFiles/";//A directory is created as a relative path

    /**
     *
     * Create a seller information file with pseudo-random data.
     *
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
        try (FileWriter writer = new FileWriter(OUTPUT_FOLDER + "salesman_info.txt")) {
            for (int i = 0; i < salesmanCount; i++) {
                String seller = sellersInfo.get(random.nextInt(sellersInfo.size()));
                writer.write(seller + "\n");
            }
        }
    }

    /**
     * Create a product information file with pseudo-random data.
     *
     * @param productsCount Number of products to generate.
     * @param productsFile Name of the file containing product information
     * products.
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
        try (FileWriter writer = new FileWriter(OUTPUT_FOLDER + "products_info.txt")) {
            for (int i = 0; i < productsCount; i++) {
                String product = productsInfo.get(random.nextInt(productsInfo.size()));
                writer.write(product + "\n");
            }
        }
    }

    /**
     * Create a sales information file with pseudo-random data.
     *
     * @param randomSalesCount Number of random sales to generate.
     * @param salesFile Name of the file containing sales information.
     * @param sellersFile Name of the file that contains sales information
     * sellers.
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
        try (BufferedReader reader = new BufferedReader(new FileReader(salesFile)); FileWriter writer = new FileWriter(OUTPUT_FOLDER + "sales_info.txt")) {

            String line;
            for (int i = 0; i < randomSalesCount; i++) {
                String seller = sellersInfo.get(random.nextInt(sellersInfo.size()));
                writer.write(seller + "\n");

                // Read sales from sales file
                List<String> sales = readLinesFromFile(salesFile);
                for (String sale : sales) {
                    writer.write(sale + "\n");
                }
            }
        }
    }

    /**
     * Reads all lines from a file and returns them as a list of chains.
     *
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
     *
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
