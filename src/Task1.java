import java.util.Arrays;
import java.util.stream.IntStream;

public class Task1 {

    public static class MaxBenefit {
        int i, j;

        MaxBenefit(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MaxBenefit that = (MaxBenefit) o;

            return i == that.i && j == that.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    public static MaxBenefit maxBenefit(int[] price) {
        price = makeDeltaArray(price);
        MaxBenefit maxBenefit = maxSubarrayIndexes(price, 0, price.length);
        return maxBenefit;
    }

    private static int[] makeDeltaArray(int[] price) {
        int[] delta = new int[price.length - 1];
        for (int i = 0; i < price.length - 1; i++) {
            delta[i] = price[i + 1] - price[i];
        }
        return delta;
    }

    private static int[] maxArray(int[] arr1, int[] arr2) {
        int sum1 = IntStream.of(arr1).sum();
        int sum2 = IntStream.of(arr2).sum();
        return (sum1 > sum2) ? arr1 : arr2;
    }

    private static int compareArrays(int[] arr1, int[] arr2) {
        int sum1 = IntStream.of(arr1).sum();
        int sum2 = IntStream.of(arr2).sum();
        return (sum1 > sum2) ? 1 : 2;
    }

    public static int[] maxSubarray(int[] delta) {
        int[] leftMaxArr, rightMaxArr, maxArr;
        maxArr = delta;
        if (delta.length > 1) {
            leftMaxArr = maxSubarray(Arrays.copyOfRange(delta, 0, delta.length / 2));
            rightMaxArr = maxSubarray(Arrays.copyOfRange(delta, delta.length / 2, delta.length));
            maxArr = maxArray(leftMaxArr, rightMaxArr);
            for (int l = 1; l < delta.length / 2; l++) {
                for (int r = 1; r < delta.length / 2; r++) {
                    maxArr = maxArray(maxArr, Arrays.copyOfRange(delta, delta.length / 2 - l, delta.length / 2 + r));
                }
            }
        }
        return maxArr;
    }

    public static MaxBenefit maxSubarrayIndexes(int[] delta, int start, int end) {

        MaxBenefit left, right;
        int maxStart = start;
        int maxEnd = end;

        if ((end - start) > 1) {
            int[] leftArr, rightArr, maxArr;
            int middle = (end - ((end - start) / 2));
            left = maxSubarrayIndexes(delta, start, middle);
            right = maxSubarrayIndexes(delta, middle, end);

            leftArr = Arrays.copyOfRange(delta, left.i, left.j);
            rightArr = Arrays.copyOfRange(delta, right.i, right.j);

            if (compareArrays(leftArr, rightArr) == 1) {
                maxArr = leftArr;
                maxStart = left.i;
                maxEnd = left.j;
            } else {
                maxArr = rightArr;
                maxStart = right.i;
                maxEnd = right.j;
            }

            for (int l = middle - 1; l >= start; --l) {
                for (int r = middle + 1; r <= end; ++r) {
                    int[] crossingArr = Arrays.copyOfRange(delta, l, r);
                    if (compareArrays(maxArr, crossingArr) == 2) {
                        maxArr = crossingArr;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return new MaxBenefit(maxStart, maxEnd);
    }

    public static MaxBenefit maxSubarrayKadanes(int[] delta) {
        int indexRight = 0;
        int indexLeft = 0;
        int tempLeft = -1;
        int partSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < delta.length; ++i) {
            partSum += delta[i];
            if (partSum >= maxSum) {
                maxSum = partSum;
                indexRight = i + 1;
                indexLeft = tempLeft + 1;
            }

            if (partSum < 0) {
                partSum = 0;
                tempLeft = i;
            }
        }
        return new MaxBenefit(indexLeft, indexRight);
    }
}
