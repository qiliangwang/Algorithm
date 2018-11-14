package explore;

import java.util.LinkedList;

class Solution279 {
    private class NumberStepDTO{
        int number;
        int step;

        public NumberStepDTO(int number, int step) {
            this.number = number;
            this.step = step;
        }
    }

    public int numSquares(int n) {
        LinkedList<NumberStepDTO> queue = new LinkedList<>();
        queue.addLast(new NumberStepDTO(n, 0));
        boolean[] visited = new boolean[n+1];
        visited[n] = true;
        while (!queue.isEmpty()) {
            NumberStepDTO numberStepDTO = queue.removeFirst();
            int number = numberStepDTO.number;
            int step = numberStepDTO.step;
            if (number == 0) {
                return step;
            }
            for (int i = 1; number - i*i >= 0; i ++) {
                int new_number = number - i*i;
                if (!visited[new_number]) {
                    queue.addLast(new NumberStepDTO(new_number, step + 1));
                    visited[new_number] = true;
                }
            }
        }
        return n;
    }


    public static void main(String[] args) {

        int result = new Solution279().numSquares(12);
        System.out.println(result);
    }
}
