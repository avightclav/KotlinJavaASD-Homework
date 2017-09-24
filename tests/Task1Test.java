import javafx.concurrent.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    @Test
    void maxBenefit() {
        int[] arr1 = new int[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94};
        int[] arr2 = new int[]{10, 11, 7, 10, 6};
        assertEquals(new Task1.MaxBenefit(7, 11), Task1.maxBenefit(arr1));
        assertEquals(new Task1.MaxBenefit(2, 3), Task1.maxBenefit(arr2));
    }

    @Test
    void maxSubarrayIndexes() {
        int arr1[] = new int[]{13, -9, -47, 18, 20, -5, -7, 12, -5};
        int arr2[] = new int[]{-3, -12, -40, -5, -7};
        int arr3[] = new int[]{-3, -4, -5, 0, -17, -34, -56};
        int arr5[] = new int[]{};
        int arr6[] = new int[]{-10, 18, 7, -3, 2, -24};
        assertEquals(new Task1.MaxBenefit(3, 8), Task1.maxSubarrayIndexes(arr1));
        assertEquals(new Task1.MaxBenefit(0, 1), Task1.maxSubarrayIndexes(arr2));
        assertEquals(new Task1.MaxBenefit(3, 4), Task1.maxSubarrayIndexes(arr3));
        assertEquals(new  Task1.MaxBenefit(0, 0), Task1.maxSubarrayIndexes(arr5));
        assertEquals(new Task1.MaxBenefit(1, 3), Task1.maxSubarrayIndexes(arr6));
    }

    @Test
    void maxSubarray() {
        int arr1[] = new int[]{13, -9, -47, 18, 20, -7, 12, -5};
        int arr2[] = new int[]{-3, -12, -40, -5, -7};
        int arr3[] = new int[]{-3, -4, -5, 0, -17, -34, -56};
        int arr4[] = new int[]{-5};
        int arr5[] = new int[]{};

        assertArrayEquals(new int[]{18, 20, -7, 12}, Task1.maxSubarray(arr1));
        assertArrayEquals(new int[]{-3}, Task1.maxSubarray(arr2));
        assertArrayEquals(new int[]{0}, Task1.maxSubarray(arr3));
        assertArrayEquals(new int[]{-5}, Task1.maxSubarray(arr4));
        assertArrayEquals(new int[]{}, Task1.maxSubarray(arr5));
    }

    @Test
    void maxSubarrayKadanes() {
        int arr1[] = new int[]{13, -9, -47, 18, 20, -5, -7, 12, -5};
        int arr2[] = new int[]{13, -9, -47};
        int arr3[] = new int[]{-3, -12, -40, -5, -7};
        int arr4[] = new int[]{-3, -4, -5, 0, -17, -34, -56};
        int arr5[] = new int[]{-5};
        int arr6[] = new int[]{};
        assertEquals(new Task1.MaxBenefit(3, 8), Task1.maxSubarrayKadanes(arr1));
        assertEquals(new Task1.MaxBenefit(0, 1), Task1.maxSubarrayKadanes(arr2));
        assertEquals(new Task1.MaxBenefit(0, 1), Task1.maxSubarrayKadanes(arr3));
        assertEquals(new Task1.MaxBenefit(3, 4), Task1.maxSubarrayKadanes(arr4));
        assertEquals(new Task1.MaxBenefit(0, 1), Task1.maxSubarrayKadanes(arr5));
        assertEquals(new Task1.MaxBenefit(0, 0), Task1.maxSubarrayKadanes(arr6));

    }

}