import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchingAlgorithms {

    public static void main(String[] args) {
        int[] array = {3, 7, 1, 9, 5, 2, 8, 4, 6};

        System.out.println("Original array: " + Arrays.toString(array));

        int linearSearchTarget = 5;
        int linearSearchIndex = linearSearch(array, linearSearchTarget);
        System.out.println("Linear Search: " + linearSearchTarget + " found at index " + linearSearchIndex);

        Arrays.sort(array); // Binary Search requires a sorted array
        int binarySearchTarget = 7;
        int binarySearchIndex = binarySearch(array, binarySearchTarget);
        System.out.println("Binary Search: " + binarySearchTarget + " found at index " + binarySearchIndex);

        int hashSearchTarget = 4;
        Map<Integer, Integer> hashMap = createHashMap(array);
        int hashSearchIndex = hashSearch(hashMap, hashSearchTarget);
        System.out.println("Hash-based Search: " + hashSearchTarget + " found at index " + hashSearchIndex);
    }

    // Linear Search
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search
    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Hash-based Search
    public static int hashSearch(Map<Integer, Integer> hashMap, int target) {
        return hashMap.getOrDefault(target, -1);
    }

    // Helper method to create a HashMap for Hash-based Search
    public static Map<Integer, Integer> createHashMap(int[] array) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            hashMap.put(array[i], i);
        }
        return hashMap;
    }
}
