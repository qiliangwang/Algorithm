package util;

import java.util.ArrayList;

public class ForEachDemo {
    private class NumberStepDTO{
        int number;
        int step;

        public NumberStepDTO(int number, int step) {
            this.number = number;
            this.step = step;
        }
    }

    public void testForeach() {
        ArrayList<NumberStepDTO> numberStepDTOArrayList = new ArrayList<>();
        new NumberStepDTO(10, 10);
        for (int i = 0; i < 10; i ++){
            numberStepDTOArrayList.add(new NumberStepDTO(10, 10));
            numberStepDTOArrayList.add(null);
        }
        numberStepDTOArrayList.forEach(numberStepDTO -> System.out.println(numberStepDTO));
    }

    public static void main(String[] args) {
        new ForEachDemo().testForeach();
    }
}
