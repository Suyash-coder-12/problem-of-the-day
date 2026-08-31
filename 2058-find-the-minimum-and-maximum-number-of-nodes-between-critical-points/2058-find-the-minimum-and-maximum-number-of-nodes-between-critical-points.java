/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] {-1, -1};
        }
        
        ListNode prev = head;
        ListNode curr = head.next;
        
        int firstCritical = -1;
        int lastCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        
        int index = 1;
        
        while (curr.next != null) {
            ListNode next = curr.next;
            
            // Check if current node is a local maxima or minima
            if ((curr.val > prev.val && curr.val > next.val) || 
                (curr.val < prev.val && curr.val < next.val)) {
                
                if (firstCritical == -1) {
                    // This is the first critical point found
                    firstCritical = index;
                } else {
                    // Update min distance using the previous critical point
                    minDistance = Math.min(minDistance, index - lastCritical);
                }
                // Update the last critical point to the current index
                lastCritical = index;
            }
            
            // Move pointers forward
            prev = curr;
            curr = next;
            index++;
        }
        
        // If we found fewer than 2 critical points, return [-1, -1]
        if (minDistance == Integer.MAX_VALUE) {
            return new int[] {-1, -1};
        }
        
        int maxDistance = lastCritical - firstCritical;
        
        return new int[] {minDistance, maxDistance};
    }
}