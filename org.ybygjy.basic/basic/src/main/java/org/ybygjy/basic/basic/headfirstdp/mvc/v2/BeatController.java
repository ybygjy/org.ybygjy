package org.ybygjy.basic.basic.headfirstdp.mvc.v2;

/**
 * @author leye
 * @version 2018-03-18
 */
public class BeatController {
    private BeatModel2 beatModel2;
    public BeatController(BeatModel2 beatModel2) {
        this.beatModel2 = beatModel2;
    }
    public void start() {
        this.beatModel2.play();
    }
    public void stop() {
        this.beatModel2.stop();
    }
    public void increaseBPM() {
        BPMModel2 bpmModel2 = this.beatModel2.getBPMModel2();
        bpmModel2.setBpmVal(bpmModel2.getBpmVal() + 1);
        this.setBPMModel2(bpmModel2);
    }
    public void decreaseBPM() {
        BPMModel2 bpmModel2 = this.beatModel2.getBPMModel2();
        bpmModel2.setBpmVal(bpmModel2.getBpmVal() - 1);
        this.setBPMModel2(bpmModel2);
    }
    public void setBPMModel2(BPMModel2 bpmModel2) {
        this.beatModel2.setBPM(bpmModel2);
    }
    public void setBPM(int bpm) {
        this.setBPMModel2(new BPMModel2(bpm));
    }
}
