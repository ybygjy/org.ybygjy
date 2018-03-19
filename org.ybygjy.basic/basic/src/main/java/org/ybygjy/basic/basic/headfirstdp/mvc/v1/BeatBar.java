package org.ybygjy.basic.basic.headfirstdp.mvc.v1;

import javax.swing.*;
import java.awt.*;

/**
 * @author leye
 * @version 2018-02-07
 */
public class BeatBar extends JProgressBar {
    public BeatBar() {
        this.setMaximum(100);
        setIndeterminate(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
}
