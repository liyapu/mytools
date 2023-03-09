package com.lyp.learn.dp.pattern.builderpattern4;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liyapu
 * @date 2023-03-09 09:21
 * @description 我们可以把校验逻辑放置到Builder类中，先创建建造者，并且通过set()方法设置建造者的变量值，
 * 然后在使用build()方法真正创建对象之前，做集中的校验，校验通过之后才会创建对象。
 * 除此之外，我们把ResourcePoolConfig的构造函数改为private私有权限。这样我们就只能通过建造者来创建ResourcePoolConfig类对象。
 * 并且，ResourcePoolConfig没有提供任何set()方法，这样我们创建出来的对象就是不可变对象了。
 * <p>
 * 我们用建造者模式重新实现了上面的需求，具体的代码如下所示：
 */
public class ResourcePoolConfig3 {

    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    private ResourcePoolConfig3(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    //...省略getter方法...
    public String getName() {
        return name;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public String toString() {
        return "ResourcePoolConfig{" +
            "name='" + name + '\'' +
            ", maxTotal=" + maxTotal +
            ", maxIdle=" + maxIdle +
            ", minIdle=" + minIdle +
            '}';
    }

    //我们将Builder类设计成了ResourcePoolConfig的内部类。
    //我们也可以将Builder类设计成独立的非内部类ResourcePoolConfigBuilder。
    public static class Builder {

        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public ResourcePoolConfig3 build() {
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("名称不能为空");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("最大空闲数不能大于最大数");
            }
            if (minIdle > maxTotal || minIdle > maxIdle) {
                throw new IllegalArgumentException("最小空闲数不能大于最大数，细小空闲数不能大于最大空闲数");
            }

            return new ResourcePoolConfig3(this);
        }

        public Builder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.minIdle = minIdle;
            return this;
        }
    }
}
