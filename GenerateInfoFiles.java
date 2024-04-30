import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class GenerateInfoFiles {

    private static final String INPUT_FOLDER_PATH = "input/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            createInputFolder();

            // Prompt and write salesmen information
            System.out.println("Enter the number of salesmen:");
            int numSalesmen = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String salesMenFilePath = INPUT_FOLDER_PATH + "salesmen.txt";
            writeSalesMenInfo(numSalesmen, salesMenFilePath, scanner);
            System.out.println("Salesmen information saved successfully.");

            // Prompt and write products information
            System.out.println("Enter the number of products:");
            int numProducts = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String productsFilePath = INPUT_FOLDER_PATH + "products.txt";
            writeProductsInfo(numProducts, productsFilePath, scanner);
            System.out.println("Products information saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving information: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Create input folder if it doesn't exist
    private static void createInputFolder() throws IOException {
        File inputFolder = new File(INPUT_FOLDER_PATH);
        if (!inputFolder.exists()) {
            inputFolder.mkdir();
        }
    }

    // Write salesmen information to file
    private static void writeSalesMenInfo(int numSalesmen, String filePath, Scanner scanner) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 1; i <= numSalesmen; i++) {
                System.out.println("Enter details for Salesman " + i + ":");
                System.out.print("Document Type: ");
                String documentType = scanner.nextLine();
                System.out.print("Document Number: ");
                String documentNumber = scanner.nextLine();
                System.out.print("First Name: ");
                String firstName = scanner.nextLine();
                System.out.print("Last Name: ");
                String lastName = scanner.nextLine();
                writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName + "\n");
            }
        }
    }

    // Write products information to file
    private static void writeProductsInfo(int numProducts, String filePath, Scanner scanner) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 1; i <= numProducts; i++) {
                System.out.println("Enter details for Product " + i + ":");
                System.out.print("Product ID: ");
                String productId = scanner.nextLine();
                System.out.print("Product Name: ");
                String productName = scanner.nextLine();
                System.out.print("Price per Unit: ");
                String pricePerUnit = scanner.nextLine();
                writer.write(productId + ";" + productName + ";" + pricePerUnit + "\n");
            }
        }
    }
}
