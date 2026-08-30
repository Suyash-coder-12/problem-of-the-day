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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node acts as the starting point
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Traverse both lists until one is exhausted
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            // Move the current pointer forward
            current = current.next;
        }
        
        // At least one of the lists is now null. 
        // Attach the remaining nodes of the other list.
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        // Return the merged list, skipping the dummy node
        return dummy.next;
    }
}