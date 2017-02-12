package jvm.advanced.features.optimization.compile;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhanggang02 on 2016/7/29.
 */
public class GenericTypes {
    /*public void testGeneric(List<String> list){

    }*/

    public String testGeneric(List<Integer> list) {
        return "";
    }

    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(1, 2);
    }
}
