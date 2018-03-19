package org.ybygjy.basic.basic.headfirstdp.mvc.v2;

import org.ybygjy.basic.basic.headfirstdp.mvc.MIDISequencerFactory;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequencer;
import java.util.ArrayList;
import java.util.List;

/**
 * 节拍模型
 * @author leye
 * @version 2018-03-18
 */
public class BeatModel2 implements MetaEventListener {
    private volatile BPMModel2 bpmModel = new BPMModel2(90);
    private Sequencer sequencer = null;
    private List<BeatModelObserver> observerList = new ArrayList<BeatModelObserver>();
    public void init() {
        //基础环境构建
        this.sequencer = MIDISequencerFactory.getInstance().createSequencer(this, this.bpmModel.getBpmVal());
    }
    public void play() {
        //播放
        sequencer.start();
    }
    public void stop() {
        //停止
        sequencer.stop();
    }
    public void setBPM(BPMModel2 bpmModel2) {
        final BPMModel2 oldBPM = bpmModel;
        this.bpmModel = bpmModel2;
        if (sequencer != null) {
            sequencer.setTempoInBPM(this.bpmModel.getBpmVal());
        }
        observerList.forEach(s->{
            try {
                s.onChangeBPM(oldBPM, bpmModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public BPMModel2 getBPMModel2() {
        return bpmModel;
    }
    public void registerObserver(BeatModelObserver beatModelObserver) {
        if (observerList.contains(beatModelObserver)) {
            return;
        }
        observerList.add(beatModelObserver);
    }
    public void removeObserver(BeatModelObserver beatModelObserver) {
        if (observerList.contains(beatModelObserver)) {
            observerList.remove(beatModelObserver);
        }
    }

    @Override
    public void meta(MetaMessage meta) {
        if (meta.getType() == 47) {
            observerList.forEach(s->{
                s.onChangeStatus(this);
            });
            sequencer.start();
            this.setBPM(getBPMModel2());
        }
    }
}
