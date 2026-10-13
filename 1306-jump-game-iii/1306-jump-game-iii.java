class Solution {
    public boolean canReach(int[] arr, int start) {
        // Base cases: 
        // 1. Index is out of bounds
        // 2. We have already visited this index (marked as negative)
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        
        // Target found
        if (arr[start] == 0) {
            return true;
        }
        
        // Save the jump distance
        int jump = arr[start];
        
        // Mark the current index as visited by negating its value
        arr[start] = -jump;
        
        // Recursively check the right jump and the left jump
        return canReach(arr, start + jump) || canReach(arr, start - jump);
    }
}