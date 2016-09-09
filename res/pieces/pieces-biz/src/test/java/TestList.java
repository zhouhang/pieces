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

}
