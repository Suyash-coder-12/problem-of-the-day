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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        // Step 1: Count total nodes
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        
        // Step 2: Dummy node to track the new head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        
        // Step 3: Reverse exactly k nodes at a time
        while (count >= k) {
            ListNode groupStart = prevGroupEnd.next;
            ListNode current = groupStart;
            ListNode prev = null;
            
            // Standard reverse list logic for k nodes
            for (int i = 0; i < k; i++) {
                ListNode nextNode = current.next;
                current.next = prev;
                prev = current;
                current = nextNode;
            }
            
            // Reconnect the reversed section
            groupStart.next = current; // connect to the remaining unreversed list
            prevGroupEnd.next = prev;  // connect previous part to the new head of this group
            
            // Move the pointer for the next iteration
            prevGroupEnd = groupStart;
            count -= k;
        }
        
        return dummy.next;
    }
}