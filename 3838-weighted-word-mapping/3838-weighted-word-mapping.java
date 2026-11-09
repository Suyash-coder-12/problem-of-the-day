class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        int n = words.length;
        char[] result = new char[n];

        for (int i = 0; i < n; i++) {
            String word = words[i];
            int totalWeight = 0;

            for (int j = 0; j < word.length(); j++) {
                totalWeight += weights[word.charAt(j) - 'a'];
            }

            int rem = totalWeight % 26;
            result[i] = (char) ('z' - rem);
        }

        return new String(result);
    }
}