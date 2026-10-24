class Solution {
    private final int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aStart = dateToDays(arriveAlice);
        int aEnd = dateToDays(leaveAlice);
        int bStart = dateToDays(arriveBob);
        int bEnd = dateToDays(leaveBob);
        int overlapStart = Math.max(aStart, bStart);
        int overlapEnd = Math.min(aEnd, bEnd);
        return Math.max(0, overlapEnd - overlapStart + 1);
    }

    private int dateToDays(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int totalDays = 0;
        for (int i = 0; i < month - 1; i++) {
            totalDays += months[i];
        }
        
        return totalDays + day;
    }
}