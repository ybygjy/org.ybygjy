package org.ybygjy.basic.basic.headfirstdp.mvc;

/**
 * 模型接口
 * @author leye
 * @version 2018-02-07
 */
public interface BeatModelInterface {
    void initialize();
    void on();
    void off();
    void setBPM(int bpm);
    int getBPM();
    void registerObserver(BeatObserver o);
    void removeObserver(BeatObserver o);
    void registerObserver(BPMObserver o);
    void removeObserver(BPMObserver o);
}
