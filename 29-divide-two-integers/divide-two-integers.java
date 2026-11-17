class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        int a = dividend > 0 ? -dividend : dividend;
        int b = divisor > 0 ? -divisor : divisor;

        int quotient = 0;

        while (a <= b) {
            int value = b;
            int multiple = 1;

            while (value >= -1073741824 && a <= value + value) {
                value += value;
                multiple += multiple;
            }

            a -= value;
            quotient += multiple;
        }

        return isNegative ? -quotient : quotient;
    }
}
