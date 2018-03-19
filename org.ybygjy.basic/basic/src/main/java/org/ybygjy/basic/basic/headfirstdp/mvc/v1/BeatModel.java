package org.ybygjy.basic.basic.headfirstdp.mvc.v1;

import org.ybygjy.basic.basic.headfirstdp.mvc.MIDISequencerFactory;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leye
 * @version 2018-02-07
 */
public class BeatModel implements BeatModelInterface, MetaEventListener {
    private Sequencer sequencer;
    private Track track;
    private List<BeatObserver> beatObservers = new ArrayList();
    private List<BPMObserver> bpmObservers = new ArrayList();
    private int bpm = 90;
    @Override
    public void meta(MetaMessage meta) {
        if (meta.getType() == 47) {
            beatEvent();
            sequencer.start();
            setBPM(getBPM());
        }
    }

    @Override
    public void initialize() {
        this.sequencer = MIDISequencerFactory.getInstance().createSequencer(this, bpm);
    }

    @Override
    public void on() {
        sequencer.start();
    }

    @Override
    public void off() {
        setBPM(0);
        sequencer.stop();
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        if (sequencer != null) {
            sequencer.setTempoInBPM(getBPM());
        }
        notifyBPMObservers();
    }

    @Override
    public int getBPM() {
        return bpm;
    }

    @Override
    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
    }

    @Override
    public void removeObserver(BeatObserver o) {
        beatObservers.remove(o);
    }

    @Override
    public void registerObserver(BPMObserver o) {
        bpmObservers.add(o);
    }

    @Override
    public void removeObserver(BPMObserver o) {
        bpmObservers.remove(o);
    }

    private void notifyBPMObservers() {
        bpmObservers.forEach(BPMObserver::updateBPM);
    }
    private void notifyBeatObservers() {
        beatObservers.forEach(BeatObserver::updateBeat);
    }

    private void beatEvent() {
        notifyBeatObservers();
    }
}
