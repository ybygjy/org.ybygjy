package org.ybygjy.basic.basic.headfirstdp.mvc;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leye
 * @version 2018-02-07
 */
public class BeatModel implements BeatModelInterface, MetaEventListener {
    private Sequencer sequencer;
    private Sequence sequence;
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
        setUpMidi();
        buildTrackAndStart();
    }

    @Override
    public void on() {
        sequencer.start();
        setBPM(bpm);
    }

    @Override
    public void off() {
        setBPM(0);
        sequencer.stop();
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        sequencer.setTempoInBPM(getBPM());
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
    private void buildTrackAndStart() {
        int[] trackLists = {35, 0, 46, 0};
        sequence.deleteTrack(null);
        track = sequence.createTrack();
        makeTracks(trackLists);
        track.add(makeEvent(192, 9, 1, 0, 4));
        try {
            sequencer.setSequence(sequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeTracks(int[] trackLists) {
        for (int i = 0; i < trackLists.length; i++) {
            int key = trackLists[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent midiEvent = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            midiEvent = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return midiEvent;
    }

    private void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.setLoopCount(100000);
            sequencer.open();
            sequencer.addMetaEventListener(this);
            sequence = new Sequence(Sequence.PPQ, 4);
            //track = sequence.createTrack();
            sequencer.setTempoInBPM(getBPM());
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    private void beatEvent() {
        notifyBeatObservers();
    }
}
