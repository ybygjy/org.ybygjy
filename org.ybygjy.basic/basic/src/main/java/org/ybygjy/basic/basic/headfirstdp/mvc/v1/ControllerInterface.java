package org.ybygjy.basic.basic.headfirstdp.mvc.v1;

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
