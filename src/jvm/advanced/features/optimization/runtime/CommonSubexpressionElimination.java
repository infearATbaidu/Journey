package jvm.advanced.features.optimization.runtime;

/**
 * Created by zhanggang02 on 2016/7/26.
 */
public class CommonSubexpressionElimination {
    public static void main(String args[]) {
        int a, b, c;
        a = b = c = 0;
        int d = 0;
        long cost = 0, times = Integer.MAX_VALUE;
        //JIT 的编译优化结果：int d = E*13 + a*2
        /*-Xint：cost=115s
          -Xcomp：cost=35s
           mix:cost=35s
        */
        for (int i = 0; i != times; i++) {
            long start = System.currentTimeMillis();
            d = (c * b) * 12 + a + (a + b * c);
            cost += (System.currentTimeMillis() - start);
        }
        System.out.println(cost);
    }
}
