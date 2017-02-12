package jvm.advanced.features.memory;

/**
 * Created by infear on 2016/5/8.
 * 指令重排测试
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int times = 0;
        while (true) {
            times += 1;
            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            one.join();
            other.join();
            System.out.println("(" + x + "," + y + ")");
            if (x == 0 || y == 0) {
                System.err.println(times);
                break;
            }
        }
    }
}
