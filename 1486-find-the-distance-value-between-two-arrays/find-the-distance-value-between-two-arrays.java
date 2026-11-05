class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        java.util.Arrays.sort(arr2);
        int distance = 0;
        for (int val : arr1) {
            if (isValid(arr2, val, d)) {
                distance++;
            }
        }
        return distance;
    }

    private boolean isValid(int[] arr, int val, int d) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (Math.abs(arr[mid] - val) <= d) {
                return false;
            }
            if (arr[mid] < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return true;
    }
}
