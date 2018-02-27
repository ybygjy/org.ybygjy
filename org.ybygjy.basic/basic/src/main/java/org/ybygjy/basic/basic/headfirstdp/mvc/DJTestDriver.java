package org.ybygjy.basic.basic.headfirstdp.mvc;

/**
 * @author leye
 * @version 2018-02-07
 */
public class DJTestDriver {
    public static void main(String[] args) {
        BeatModelInterface beatModelInterface = new BeatModel();
        ControllerInterface controllerInterface = new BeatController(beatModelInterface);
    }
}
