import com.sun.jmx.snmp.Timestamp;

import java.util.*;

/**
 * Created by wangbin on 2016/8/2.
 */
public class TestList {


    public static void main(String[] args) {

        Timestamp ts = new Timestamp(new Date().getTime());


        String time = String.valueOf( ts.getDateTime());

        System.out.println(time);
        System.out.println(time.length());


    }

    public static void listIterator1() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for(int value:list) {
            if(3 == value){
                list.remove(value);
            }
            System.out.println(value);
        }

        System.out.println("list=" + list.toString());
    }

    public static void listIterator2(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()){
            Integer value = it.next();
            if (2 == value) {
                it.remove();
            }

            System.out.println(value);
        }

        System.out.println("list=" + list.toString());
    }

    public static void set1(){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(4);
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()){
            Integer value = it.next();
            if (2 == value) {
                it.remove();
            }

            System.out.println(value);
        }

        System.out.println("set=" + set.toString());
    }
}
