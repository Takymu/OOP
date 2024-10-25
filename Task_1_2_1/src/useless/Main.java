
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(" ");
        f(list);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public static void f(ArrayList<Integer> list) {
        list.add(1);
        list.add(2);
    }
}