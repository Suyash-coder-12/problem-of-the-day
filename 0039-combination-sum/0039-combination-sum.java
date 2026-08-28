import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        // Base case: if remaining target is less than 0, stop exploring this path
        if (remain < 0) {
            return;
        } 
        // Base case: if remaining target is exactly 0, we found a valid combination
        else if (remain == 0) {
            result.add(new ArrayList<>(tempList));
        } 
        // Recursive step: explore further
        else {
            for (int i = start; i < candidates.length; i++) {
                // Choose the current candidate
                tempList.add(candidates[i]);
                
                // Explore this choice (pass 'i' as start since we can reuse the same element)
                backtrack(result, tempList, candidates, remain - candidates[i], i);
                
                // Undo the choice (backtrack) to try the next candidate
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}