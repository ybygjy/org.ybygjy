package org.ybygjy.basic.basic.headfirstdp.mvc.v2;

/**
 * @author leye
 * @version 2018-03-18
 */
public class DJPlayer {
    public static void main(String[] args) {
        BeatModel2 beatModel2 = new BeatModel2();
        beatModel2.init();
        BeatController beatController = new BeatController(beatModel2);
        BeatDJView beatDJView = new BeatDJView(beatController, beatModel2);
        beatDJView.show();
    }
}
