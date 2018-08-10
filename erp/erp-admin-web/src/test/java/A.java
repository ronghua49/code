import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class A {

    @Test
    public void  s(){
        String a = "123123";
       String b =  DigestUtils.md5Hex(a);
        System.out.println(b);
    }
@Test
    public void ListTest(){
        List<String> aList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("22");

        List<String> stringList2 = new ArrayList<>();
        stringList2.add("33");
        stringList2.add("44");
      /*  List<Boolean> booleanList = new ArrayList<>();
        booleanList.add(false);
        booleanList.add(true);*/

        aList.addAll(stringList);
        aList.addAll(stringList2);
        for (String s:aList){
            System.out.println(s);
        }
    }
}
