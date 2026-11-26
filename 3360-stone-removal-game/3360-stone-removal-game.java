class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean aliceTurn = true;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            aliceTurn = !aliceTurn;
        }

        // The current player (aliceTurn) cannot move and loses, so Alice wins if it's Bob's turn
        return !aliceTurn;
    }
}