package design.pattern.builder;

/**
 * Created by infear on 2017/2/12.
 */
public class Entity {
    Object part1;
    Object part2;

    public Object getPart1() {
        return part1;
    }

    public void setPart1(Object part1) {
        this.part1 = part1;
    }

    public Object getPart2() {
        return part2;
    }

    public void setPart2(Object part2) {
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "part1=" + part1 +
                ", part2=" + part2 +
                '}';
    }
}
