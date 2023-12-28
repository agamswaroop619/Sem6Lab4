import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Radix {
    public static void main(String[] args) {
        String filePath = "random_numbers.txt";
        int[] arr = readNumbersFromFile(filePath);
        long startTime = System.currentTimeMillis();

        if (arr != null) {
            System.out.println("Original Array:");
            printArray(arr);
            radixSort(arr);
            System.out.println("\nSorted Array:");
            printArray(arr);
        } else {
            System.out.println("Error reading numbers from file.");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds");
    }

    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Initialize count array
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Count occurrences
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Update count array to store the position of each element
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int[] readNumbersFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            int count = 0;

            while (line != null) {
                count++;
                line = reader.readLine();
            }

            int[] arr = new int[count];

            reader.close();
            reader = new BufferedReader(new FileReader(filePath));

            for (int i = 0; i < count; i++) {
                line = reader.readLine();
                arr[i] = Integer.parseInt(line);
            }

            reader.close();
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
