import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        // TreeSet keeps the final strings sorted lexicographically
        Set<String> result = new TreeSet<>();
        
        queue.add(expression);
        seen.add(expression);
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            
            // If there are no braces left, the string is fully expanded
            if (!curr.contains("{")) {
                result.add(curr);
                continue;
            }
            
            // Find the first '}' and the closest '{' right before it
            int right = curr.indexOf("}");
            int left = curr.lastIndexOf("{", right);
            
            // Extract the parts to form new strings
            String prefix = curr.substring(0, left);
            String suffix = curr.substring(right + 1);
            String[] parts = curr.substring(left + 1, right).split(",");
            
            // Expand the innermost brace and add new combinations to the queue
            for (String part : parts) {
                String next = prefix + part + suffix;
                if (seen.add(next)) {
                    queue.add(next);
                }
            }
        }
        
        return new ArrayList<>(result);
    }
}