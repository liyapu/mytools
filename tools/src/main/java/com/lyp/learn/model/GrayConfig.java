package com.lyp.learn.model;

import com.google.common.base.Preconditions;

/**
 * 灰度配置
 */
public class GrayConfig {

    /**
     * @see GrayReaderPhaseEnum
     */
    private Integer phase;
    /**
     * 1 => 1%
     * 99 => 99%
     * 0.1 => 0.1%
     */
    private Double percentage;

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public void check() {
        Preconditions.checkNotNull(this.percentage, "GrayConfig#percentage cant' be null!");
        Preconditions.checkNotNull(this.phase, "GrayConfig#phase cant' be null!");
        GrayReaderPhaseEnum phaseEnum = GrayReaderPhaseEnum.of(this.phase);
        if (phaseEnum == null) {
            String msg = String.format("invalid GrayConfig#phase = %d", this.phase);
            throw new IllegalArgumentException(msg);
        }
    }

    public GrayReaderPhaseEnum retrieveReaderPhaseEnum() {
        GrayReaderPhaseEnum phaseEnum = GrayReaderPhaseEnum.of(this.phase);
        if (phaseEnum == null) {
            String msg = String.format("invalid GrayConfig#phase = %d", this.phase);
            throw new IllegalArgumentException(msg);
        }
        return phaseEnum;
    }
}
