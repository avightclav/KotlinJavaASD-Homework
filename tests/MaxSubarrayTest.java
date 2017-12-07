import Task1.MaxSubarray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxSubarrayTest {
    @Test
    void maxBenefit() {
        int[] arr1 = new int[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94};
        int[] arr2 = new int[]{10, 11, 7, 10, 6};
        assertEquals(new MaxSubarray.MaxBenefit(7, 11), MaxSubarray.maxBenefit(arr1));
        assertEquals(new MaxSubarray.MaxBenefit(2, 3), MaxSubarray.maxBenefit(arr2));
    }

    @Test
    void maxSubarrayIndexes() {
        int arr1[] = new int[]{13, -9, -47, 18, 20, -5, -7, 12, -5};
        int arr2[] = new int[]{-3, -12, -40, -5, -7};
        int arr3[] = new int[]{-3, -4, -5, 0, -17, -34, -56};
        int arr5[] = new int[]{};
        int arr6[] = new int[]{-10, 18, 7, -3, 2, -24};
        assertEquals(new MaxSubarray.MaxBenefit(3, 8), MaxSubarray.maxSubarrayIndexes(arr1));
        assertEquals(new MaxSubarray.MaxBenefit(0, 1), MaxSubarray.maxSubarrayIndexes(arr2));
        assertEquals(new MaxSubarray.MaxBenefit(3, 4), MaxSubarray.maxSubarrayIndexes(arr3));
        assertEquals(new  MaxSubarray.MaxBenefit(0, 0), MaxSubarray.maxSubarrayIndexes(arr5));
        assertEquals(new MaxSubarray.MaxBenefit(1, 3), MaxSubarray.maxSubarrayIndexes(arr6));
    }

    @Test
    void maxSubarray() {
        int arr1[] = new int[]{13, -9, -47, 18, 20, -7, 12, -5};
        int arr2[] = new int[]{-3, -12, -40, -5, -7};
        int arr3[] = new int[]{-3, -4, -5, 0, -17, -34, -56};
        int arr4[] = new int[]{-5};
        int arr5[] = new int[]{};

        assertArrayEquals(new int[]{18, 20, -7, 12}, MaxSubarray.maxSubarray(arr1));
        assertArrayEquals(new int[]{-3}, MaxSubarray.maxSubarray(arr2));
        assertArrayEquals(new int[]{0}, MaxSubarray.maxSubarray(arr3));
        assertArrayEquals(new int[]{-5}, MaxSubarray.maxSubarray(arr4));
        assertArrayEquals(new int[]{}, MaxSubarray.maxSubarray(arr5));
    }

    @Test
    void maxSubarrayKadanes() {
        int arr1[] = new int[]{13, -9, -47, 18, 20, -5, -7, 12, -5};
        int arr2[] = new int[]{13, -9, -47};
        int arr3[] = new int[]{-3, -12, -40, -5, -7};
        int arr4[] = new int[]{-3, -4, -5, 0, -17, -34, -56};
        int arr5[] = new int[]{-5};
        int arr6[] = new int[]{};
        assertEquals(new MaxSubarray.MaxBenefit(3, 8), MaxSubarray.maxSubarrayKadanes(arr1));
        assertEquals(new MaxSubarray.MaxBenefit(0, 1), MaxSubarray.maxSubarrayKadanes(arr2));
        assertEquals(new MaxSubarray.MaxBenefit(0, 1), MaxSubarray.maxSubarrayKadanes(arr3));
        assertEquals(new MaxSubarray.MaxBenefit(3, 4), MaxSubarray.maxSubarrayKadanes(arr4));
        assertEquals(new MaxSubarray.MaxBenefit(0, 1), MaxSubarray.maxSubarrayKadanes(arr5));
        assertEquals(new MaxSubarray.MaxBenefit(0, 0), MaxSubarray.maxSubarrayKadanes(arr6));

    }

}