package com.base;

/**
 * Created by infear on 2017/3/10.
 * Java8 会对以下代码做优化 在生成字节码时使用StringBuilder来拼接字符串
 */
public class StringConcatInJava8 {
    public static void main(String[] args) {
        String result = "";
        result += "some more data";
        System.out.println(result);

        // 以下代码也会被编译器优化，但是优化发生在循环内
/*        result = "";
        for (int i = 0; i != 100; i++) {
            result += "some more data";
        }
        System.out.println(result);*/

        // 优化后的代码类似于：
/*        for (int i = 0; i < 1e6; i++) {
            StringBuilder tmp = new StringBuilder();
            tmp.append(result);
            tmp.append("some more data");
            result = tmp.toString();
        }
        System.out.println(result);*/
        // 这样比直接调用concat更慢，因为每次都需要创建新的StringBuilder对象

    }
}