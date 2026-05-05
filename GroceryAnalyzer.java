// GroceryAnalyzer.java
import java.io.*;
import java.util.Scanner;

public class GroceryAnalyzer {

    public static void main(String[] args) {
        String[] names = new String[50];
        double[] prices = new double[50];
        int count = 0;

        String filename = "groceries.txt";

        try {
            // Load data
            count = loadGroceryData(filename, names, prices);

            // Calculate average
            double average = calculateAveragePrice(prices, count);

            // Write report
            writeReport(names, prices, count, average);

            System.out.println("Report generated: grocery_report.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Method 1: Load grocery data
    public static int loadGroceryData(String filename, String[] names, double[] prices)
            throws FileNotFoundException {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        int count = 0;

        while (scanner.hasNextLine() && count < 50) {
            String line = scanner.nextLine();

            // Split by comma
            String[] parts = line.split(",");

            if (parts.length == 2) {
                names[count] = parts[0].trim();
                prices[count] = Double.parseDouble(parts[1].trim());
                count++;
            }
        }

        scanner.close();
        return count;
    }

    // Method 2: Calculate average price
    public static double calculateAveragePrice(double[] prices, int count) {
        if (count == 0) return 0;

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += prices[i];
        }

        return sum / count;
    }

    // Method 3: Write report
    public static void writeReport(String[] names, double[] prices, int count, double average)
            throws IOException {

        PrintWriter writer = new PrintWriter("grocery_report.txt");

        writer.println("=== Grocery Report ===\n");

        for (int i = 0; i < count; i++) {
            writer.printf("%-15s $%.2f%n", names[i], prices[i]);
        }

        writer.println("\n----------------------");
        writer.printf("Average Price: $%.2f%n", average);

        writer.close();
    }
}
