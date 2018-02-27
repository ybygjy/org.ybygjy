package org.ybygjy.basic.basic.headfirstdp.mvc;

/**
 * @author leye
 * @version 2018-02-07
 */
public interface ControllerInterface {
    void start();
    void stop();
    void increaseBPM();
    void decreaseBPM();
    void setBPM(int bpm);
}
