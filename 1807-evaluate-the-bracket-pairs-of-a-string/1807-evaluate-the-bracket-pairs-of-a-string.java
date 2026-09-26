import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Build the knowledge map for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Find the end of the bracket
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                
                // Extract the key and look it up
                String key = s.substring(i + 1, j);
                result.append(map.getOrDefault(key, "?"));
                
                // Advance 'i' to 'j' so the loop skips over the parsed key and ')'
                i = j;
            } else {
                // Not inside brackets, just append the character
                result.append(c);
            }
        }
        
        return result.toString();
    }
}