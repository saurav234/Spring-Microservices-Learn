import vo.Person;

public class LearnLambdaMain {

    public static void main(String args[]) {

        Store addition = (arr) -> {
            int sum = 0;
            for(int num : arr) {
                sum = sum + num;
            }
            return sum;
        };
        int[] myArray = {3,5,7,9,11,10,10};
        System.out.println(addition.add(myArray));



    }

    interface Store {
        int add(int[] arr);
    }


}
