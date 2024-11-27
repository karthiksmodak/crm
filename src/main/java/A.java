import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class A {
    public static void main(String[] args) {
        List<Integer> arr= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        List<Integer> newList=arr.stream()
                .filter(t->t%2==0)
                .collect(Collectors.toList());
        System.out.println(newList);
    }
}