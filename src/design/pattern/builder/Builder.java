package design.pattern.builder;

/**
 * Created by infear on 2017/2/12.
 */
public class Builder implements IBuilder{
    Entity entity;

    public Builder() {
        this.entity = new Entity();
    }

    @Override
    public IBuilder buildPart1() {
        entity.setPart1("builder_part1");
        return this;
    }

    @Override
    public IBuilder buildPart2() {
        entity.setPart2("builder_part2");
        return this;
    }

    @Override
    public Entity build() {
        return entity;
    }
}
