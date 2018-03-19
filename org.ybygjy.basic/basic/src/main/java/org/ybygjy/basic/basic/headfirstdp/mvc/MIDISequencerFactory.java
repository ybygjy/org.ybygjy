package org.ybygjy.basic.basic.headfirstdp.mvc;

import javax.sound.midi.*;

/**
 * MIDI Sequencer 工厂类，负责封装构造Sequencer实例
 * @author leye
 * @version 2018-03-18
 */
public class MIDISequencerFactory {
    private static MIDISequencerFactory midiSequencerFactory = new MIDISequencerFactory();
    private MIDISequencerFactory() {
    }
    public Sequencer createSequencer(MetaEventListener metaEventListener, int bpmVal) {
        Sequencer sequencer = null;
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.setLoopCount(100000);
            sequencer.open();
            sequencer.addMetaEventListener(metaEventListener);
            sequencer.setTempoInBPM(bpmVal);
            sequencer.setSequence(this.buildSequence());
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return sequencer;
    }

    private Sequence buildSequence() throws InvalidMidiDataException {
        Sequence sequence = new Sequence(Sequence.PPQ, 4);
        int[] trackLists = {35, 0, 46, 0};
        sequence.deleteTrack(null);
        this.buildTracks(sequence, trackLists);
        return sequence;
    }

    private Track buildTracks(Sequence sequence, int[] trackLists) {
        Track track = sequence.createTrack();
        for (int i = 0; i < trackLists.length; i++) {
            int key = trackLists[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
        track.add(makeEvent(192, 9, 1, 0, 4));
        return track;
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
    public static MIDISequencerFactory getInstance() {
        return midiSequencerFactory;
    }
}
