import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dhruv on 17/06/17.
 */
public class StringUtils {

    public static String reverse(String str, String delim) {
        List<String> stringList = new ArrayList<String>(Arrays.asList(str.split(delim)));
        Collections.reverse(stringList);
        return joinString(stringList,delim);
    }

    public static String joinString(List<String> stringList, String delim) {
        String res = stringList.get(0).trim();
        for(int i = 1; i<stringList.size(); i++) {
            res += delim + stringList.get(i).trim();
        }
        return res;
    }
}
