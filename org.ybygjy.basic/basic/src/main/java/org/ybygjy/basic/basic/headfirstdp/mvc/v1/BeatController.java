package org.ybygjy.basic.basic.headfirstdp.mvc.v1;

/**
 * @author leye
 * @version 2018-02-07
 */
public class BeatController implements ControllerInterface {
    private BeatModelInterface modelInteface;
    private DJView djView;
    public BeatController(BeatModelInterface modelInteface) {
        this.modelInteface = modelInteface;
        this.djView = new DJView(this, modelInteface);
        this.djView.createView();
        this.djView.createControls();
        this.djView.disableStopMenuItem();
        this.djView.enableStartMenuItem();
        this.modelInteface.initialize();
    }
    @Override
    public void start() {
        modelInteface.on();
        this.djView.disableStartMenuItem();
        this.djView.enableStopMenuItem();
    }

    @Override
    public void stop() {
        this.modelInteface.off();
        this.djView.disableStopMenuItem();
        this.djView.enableStartMenuItem();
    }

    @Override
    public void increaseBPM() {
        this.setBPM(modelInteface.getBPM() + 1);
    }

    @Override
    public void decreaseBPM() {
        this.setBPM(modelInteface.getBPM() - 1);
    }

    @Override
    public void setBPM(int bpm) {
        modelInteface.setBPM(bpm);
    }
}
