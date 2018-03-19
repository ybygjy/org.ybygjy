package org.ybygjy.basic.basic.headfirstdp.mvc.v2;

/**
 * @author leye
 * @version 2018-03-18
 */
public interface BeatModelObserver {
    public void onChangeBPM(BPMModel2 bpmModelOld, BPMModel2 bpmModelNew);
    public void onChangeStatus(BeatModel2 beatModel);
}
