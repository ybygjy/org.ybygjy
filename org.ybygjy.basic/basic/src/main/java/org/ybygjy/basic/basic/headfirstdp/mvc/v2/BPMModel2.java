package org.ybygjy.basic.basic.headfirstdp.mvc.v2;

/**
 * @author leye
 * @version 2018-03-18
 */
public class BPMModel2 {
    private volatile int bpmVal;
    public BPMModel2(int bpmVal) {
        this.bpmVal = bpmVal;
    }

    public int getBpmVal() {
        return bpmVal;
    }

    public void setBpmVal(int bpmVal) {
        this.bpmVal = bpmVal;
    }
}
