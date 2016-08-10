import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangbin on 2016/8/10.
 */
public class TestArrays {


    public static void main(String[] args) {
        String[] letters =  new String[]{"A","B","C","D","E","F","G","H","I","J",
                "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String letter = "A~E";

        String[] lesArr  = letter.split("~");



        Integer  index1 = Arrays.binarySearch(letters,lesArr[0]);
        Integer  index2 = Arrays.binarySearch(letters,lesArr[1]);

        StringBuffer sb = new StringBuffer();
        List<String> tempList = new ArrayList<>();
        for(int i = index1;i<=index2;i++){
            tempList.add(letters[i]);
        }
        StringUtils.join(tempList, ",");

        System.out.println(  StringUtils.join(tempList, ","));

    }
}
