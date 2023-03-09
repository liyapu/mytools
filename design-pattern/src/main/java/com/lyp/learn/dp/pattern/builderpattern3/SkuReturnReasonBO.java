package com.lyp.learn.dp.pattern.builderpattern3;

/**
 * @author liyapu
 * @date 2023-03-09 09:11
 * @description lombok.Builder 自动生成的建造者模式
 */

public class SkuReturnReasonBO {

    private Integer firstValue;
    private Integer secondValue;
    private String reasonText;

    /**
     * 外层对象的 builder() 方法，产生的是内部对象 SkuReturnReasonBOBuilder
     *
     * @return 内部的 Builder 对象
     */
    public static SkuReturnReasonBO.SkuReturnReasonBOBuilder builder() {
        return new SkuReturnReasonBO.SkuReturnReasonBOBuilder();
    }

    public Integer getFirstValue() {
        return this.firstValue;
    }

    public Integer getSecondValue() {
        return this.secondValue;
    }

    public String getReasonText() {
        return this.reasonText;
    }

    public void setFirstValue(final Integer firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(final Integer secondValue) {
        this.secondValue = secondValue;
    }

    public void setReasonText(final String reasonText) {
        this.reasonText = reasonText;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SkuReturnReasonBO)) {
            return false;
        } else {
            SkuReturnReasonBO other = (SkuReturnReasonBO) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$firstValue = this.getFirstValue();
                    Object other$firstValue = other.getFirstValue();
                    if (this$firstValue == null) {
                        if (other$firstValue == null) {
                            break label47;
                        }
                    } else if (this$firstValue.equals(other$firstValue)) {
                        break label47;
                    }

                    return false;
                }

                Object this$secondValue = this.getSecondValue();
                Object other$secondValue = other.getSecondValue();
                if (this$secondValue == null) {
                    if (other$secondValue != null) {
                        return false;
                    }
                } else if (!this$secondValue.equals(other$secondValue)) {
                    return false;
                }

                Object this$reasonText = this.getReasonText();
                Object other$reasonText = other.getReasonText();
                if (this$reasonText == null) {
                    if (other$reasonText != null) {
                        return false;
                    }
                } else if (!this$reasonText.equals(other$reasonText)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SkuReturnReasonBO;
    }

    @Override
    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $firstValue = this.getFirstValue();
        result = result * 59 + ($firstValue == null ? 43 : $firstValue.hashCode());
        Object $secondValue = this.getSecondValue();
        result = result * 59 + ($secondValue == null ? 43 : $secondValue.hashCode());
        Object $reasonText = this.getReasonText();
        result = result * 59 + ($reasonText == null ? 43 : $reasonText.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SkuReturnReasonBO(firstValue=" + this.getFirstValue() + ", secondValue=" + this.getSecondValue()
            + ", reasonText=" + this.getReasonText() + ")";
    }

    public SkuReturnReasonBO(final Integer firstValue, final Integer secondValue, final String reasonText) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.reasonText = reasonText;
    }

    public SkuReturnReasonBO() {
    }

    public static class SkuReturnReasonBOBuilder {

        /**
         * 外层对象的 字段，在这里还需要再次定义一次
         */
        private Integer firstValue;
        private Integer secondValue;
        private String reasonText;

        SkuReturnReasonBOBuilder() {
        }

        /**
         * set 方法，返回自身
         *
         * @param firstValue
         * @return
         */
        public SkuReturnReasonBO.SkuReturnReasonBOBuilder firstValue(final Integer firstValue) {
            this.firstValue = firstValue;
            return this;
        }

        public SkuReturnReasonBO.SkuReturnReasonBOBuilder secondValue(final Integer secondValue) {
            this.secondValue = secondValue;
            return this;
        }

        public SkuReturnReasonBO.SkuReturnReasonBOBuilder reasonText(final String reasonText) {
            this.reasonText = reasonText;
            return this;
        }

        /**
         * 最后的 build(),使用内部对象的属性，生成外部的对象
         *
         * @return 外部对象
         */
        public SkuReturnReasonBO build() {
            return new SkuReturnReasonBO(this.firstValue, this.secondValue, this.reasonText);
        }

        @Override
        public String toString() {
            return "SkuReturnReasonBO.SkuReturnReasonBOBuilder(firstValue=" + this.firstValue + ", secondValue="
                + this.secondValue + ", reasonText=" + this.reasonText + ")";
        }
    }
}
