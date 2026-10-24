class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert all dates to their integer day of the year (1 to 365)
        int aStart = dateToDays(arriveAlice);
        int aEnd = dateToDays(leaveAlice);
        int bStart = dateToDays(arriveBob);
        int bEnd = dateToDays(leaveBob);
        
        // Find the overlapping interval
        int overlapStart = Math.max(aStart, bStart);
        int overlapEnd = Math.min(aEnd, bEnd);
        
        // Calculate the number of overlapping days, minimum is 0 if no overlap
        return Math.max(0, overlapEnd - overlapStart + 1);
    }
    
    private int dateToDays(String date) {
        // Days per month in a non-leap year (index 0 is a placeholder for 1-based indexing)
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        
        // Add the days from all preceding months
        for (int i = 1; i < month; i++) {
            totalDays += daysInMonth[i];
        }
        
        return totalDays;
    }
}