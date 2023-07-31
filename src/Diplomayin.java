import java.util.ArrayList;
import java.util.List;

public class Diplomayin {

    public static List<Integer> divider(int num) { // divider is array of all dividers of num
        List<Integer> divider = new ArrayList<>();
        for (int n = 1; n <= num; n++) {
            if (num % n == 0) {
                divider.add(n);
            }
        }
        return divider;
    }

    public static List<Integer[]> diplomayin(int apples, int men, int k) { // divide apples by dividers of k
        List<Integer> arr = divider(k);
        System.out.println("dividers: " + arr);
        List<Integer[]> result = new ArrayList<>();
        for (int i = 1; i <= apples; i++) {
            for (int j : arr) {
                if (i * j % men == 0) {
                    result.add(new Integer[]{i, j});
                }
            }
        }
        return result;
    }

    public static List<List<Integer[]>> solution = new ArrayList<>();

    public static void subset_sum(List<Integer[]> numbers, int target, List<Integer[]> partial) { // check if there are numbers which sum equal apples, target is number of apples
        int s = sumOfFirstIndexes(partial);
        // check if the partial sum is equals to target
        if (s == target) {
            solution.add(new ArrayList<>(partial));
        }
        if (s >= target) {
            return; // if we reach the number why bother to continue
        }
        for (int i = 0; i < numbers.size(); i++) {
            int n = numbers.get(i)[0];
            int div = numbers.get(i)[1];
            List<Integer[]> remaining = numbers.subList(i + 1, numbers.size());
            List<Integer[]> newPartial = new ArrayList<>(partial);
            newPartial.add(new Integer[]{n, div});
            subset_sum(remaining, target, newPartial);
        }
    }

    public static int sumOfFirstIndexes(List<Integer[]> partial) {
        int sum = 0;
        for (Integer[] a : partial) {
            sum += a[0];
        }
        return sum;
    }

    public static void main(String[] args) {
        int men = 20;
        int apples = 6;
        int k = 20;
        List<Integer[]> res = diplomayin(apples, men, k);
        for (Integer[] x : res) {
            System.out.printf("(%d, %d) ", x[0], x[1]);
        }
        System.out.println();
        //System.out.println(res); // first number apple second divider

        List<Integer> resApples = new ArrayList<>();
        for (Integer[] r : res) {
            resApples.add(r[0]);
        }
        subset_sum(res, apples, new ArrayList<>());
        System.out.println("----------------------------------------------");
        for (List<Integer[]> x : solution) {
            System.out.print("[");
            for (Integer[] i : x) {
                System.out.print("{" + i[0] + "," + i[1] + "}");
            }
            System.out.print("]");
            System.out.println();

        }
    }
}


