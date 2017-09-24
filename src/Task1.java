import java.util.Arrays;
import java.util.stream.IntStream;

public class Task1 {

    public static class MaxBenefit {
        int i, j, sum;

        MaxBenefit(int i, int j) {
            this.i = i;
            this.j = j;
            this.sum = 0;
        }

        MaxBenefit(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
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
        return maxSubarrayIndexes(price);
    }

    private static int[] makeDeltaArray(int[] price) {
        int[] delta = new int[price.length - 1];
        for (int i = 0; i < price.length - 1; i++) {
            delta[i] = price[i + 1] - price[i];
        }
        return delta;
    }

    private static int sum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        return sum;
    }

    private static MaxBenefit halfMaxSubarray(int[] arr, int start, int end) {
        if ((end - start) > 1) {
            int middle = end -  (end - start) / 2;
            MaxBenefit leftArrIndexes, rightArrIndexes, crossingArrIndexes;

            leftArrIndexes = halfMaxSubarray(arr, start, middle);
            rightArrIndexes = halfMaxSubarray(arr, middle, end);

            crossingArrIndexes = new MaxBenefit(leftArrIndexes.i, rightArrIndexes.j, sum(arr, leftArrIndexes.i, rightArrIndexes.j));
            leftArrIndexes.sum = sum(arr, leftArrIndexes.i, leftArrIndexes.j);
            rightArrIndexes.sum = sum(arr, rightArrIndexes.i, rightArrIndexes.j);

            if ((crossingArrIndexes.sum > rightArrIndexes.sum) && (crossingArrIndexes.sum > leftArrIndexes.sum)) {
                return crossingArrIndexes;
            } else if (leftArrIndexes.sum > rightArrIndexes.sum) {
                return leftArrIndexes;
            } else {
                return rightArrIndexes;
            }

        }
        return new MaxBenefit(start, end, sum(arr, start, end));
    }

    private static MaxBenefit crossingMaxSubarray(int[] arr) {
        int middle = arr.length / 2, maxLeftIndex = 0, maxRightIndex = arr.length - 1, maxSum = Integer.MIN_VALUE, leftSum = 0, sum;
        for (int l = middle - 1; l >= 0; l--) {
            leftSum += arr[l];
            sum = leftSum;
            for (int r = middle; r < arr.length; r++) {
                sum += arr[r];

                if (sum >= maxSum) {
                    maxLeftIndex = l;
                    maxRightIndex = r + 1;
                    maxSum = sum;
                }

            }
        }
        return new MaxBenefit(maxLeftIndex, maxRightIndex, maxSum);
    }

    public static int[] maxSubarray(int[] delta) {
        MaxBenefit leftArrIndexes, rightArrIndexes, crossingArrIndexes;

        if (delta.length > 1) {

            leftArrIndexes = halfMaxSubarray(delta, 0, delta.length / 2);
            rightArrIndexes = halfMaxSubarray(delta, delta.length / 2, delta.length);
            crossingArrIndexes = crossingMaxSubarray(delta);

            if ((crossingArrIndexes.sum > rightArrIndexes.sum) && (crossingArrIndexes.sum > leftArrIndexes.sum)) {
                return Arrays.copyOfRange(delta, crossingArrIndexes.i, crossingArrIndexes.j);
            } else if (leftArrIndexes.sum > rightArrIndexes.sum) {
                return Arrays.copyOfRange(delta, leftArrIndexes.i, leftArrIndexes.j);
            } else {
                return Arrays.copyOfRange(delta, rightArrIndexes.i, rightArrIndexes.j);
            }
        }
        return delta;
    }

    public static MaxBenefit maxSubarrayIndexes(int[] delta) {
        MaxBenefit leftArrIndexes, rightArrIndexes, crossingArrIndexes;

        if (delta.length > 1) {

            leftArrIndexes = halfMaxSubarray(delta, 0, delta.length / 2);
            rightArrIndexes = halfMaxSubarray(delta, delta.length / 2, delta.length);
            crossingArrIndexes = crossingMaxSubarray(delta);

            if ((crossingArrIndexes.sum > rightArrIndexes.sum) && (crossingArrIndexes.sum > leftArrIndexes.sum)) {
                return crossingArrIndexes;
            } else if (leftArrIndexes.sum > rightArrIndexes.sum) {
                return leftArrIndexes;
            } else {
                return rightArrIndexes;
            }
        }
        return new MaxBenefit(0, delta.length, sum(delta,0, delta.length));
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
