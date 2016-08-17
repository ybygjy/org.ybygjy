package org.ybygjy.pay.paynow.dto;

/**
 * 交易响应状态
 * @author WangYanCheng
 * @version 2016年8月3日
 */
public enum TradeStatusEnum {
    /** 成功 */
    SUCCESS(10),
    /** 失败 */
    FAIL(20),
    /** 处理中 */
    PROCESS(30),
    /** 未知状态_需要使用业务状态确认 */
    UNKNOW(99);
    /** 枚举值 */
    private int value;

    private TradeStatusEnum(int value) {
        this.value = value;
    }

    /**
     * 给定值映射Enum类型
     * @param value value
     * @return rtnEnum/UNKNOW
     */
    public static TradeStatusEnum parseEnum(int value) {
        for (TradeStatusEnum tseInst : TradeStatusEnum.values()) {
            if (tseInst.value == value) {
                return tseInst;
            }
        }
        return UNKNOW;
    }

    public int value() {
        return this.value;
    }
}
