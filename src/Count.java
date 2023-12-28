import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Count {
    public static void main(String[] args) {
        String filePath = "random_numbers.txt";
        int[] arr = readNumbersFromFile(filePath);
        long startTime = System.currentTimeMillis();

        if (arr != null) {
            System.out.println("Original Array:");
            printArray(arr);
            countSort(arr);
            System.out.println("\nSorted Array:");
            printArray(arr);
        } else {
            System.out.println("Error reading numbers from file.");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds");
    }

    public static void countSort(int[] arr) {
        int max = findMax(arr);
        int[] count = new int[max + 1];

        // Count occurrences of each element
        for (int num : arr) {
            count[num]++;
        }

        // Update the array with sorted values
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                arr[index] = i;
                index++;
                count[i]--;
            }
        }
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
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
