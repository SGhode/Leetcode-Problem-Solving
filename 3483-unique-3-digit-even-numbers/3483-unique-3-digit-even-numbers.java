class Solution {
    public int totalNumbers(int[] digits) {
        int[] digitCounts = new int[10];
        for (int digit : digits) {
            digitCounts[digit]++;
        }
        
        int validNumberCount = 0;
        
        for (int i = 100; i <= 999; i += 2) {
            int hundreds = i / 100;
            int tens = (i / 10) % 10;
            int ones = i % 10;
            
            digitCounts[hundreds]--;
            digitCounts[tens]--;
            digitCounts[ones]--;
            
            if (digitCounts[hundreds] >= 0 && digitCounts[tens] >= 0 && digitCounts[ones] >= 0) {
                validNumberCount++;
            }
            digitCounts[hundreds]++;
            digitCounts[tens]++;
            digitCounts[ones]++;
        }
        
        return validNumberCount;
    }
}