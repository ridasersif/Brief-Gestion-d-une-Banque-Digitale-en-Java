import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList  list1 = new ArrayList();
        ArrayList  list2 = new ArrayList();
        list1.add(1);
        list2.add(2);
        list1.add(3);
        list2.add(4);
        list1.add(5);
        list2.add(6);
        list1.addAll(0,  list2);

        System.out.println(list1);

    }


}