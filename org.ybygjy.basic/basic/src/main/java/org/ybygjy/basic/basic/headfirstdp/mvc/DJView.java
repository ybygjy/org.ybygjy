package org.ybygjy.basic.basic.headfirstdp.mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author leye
 * @version 2018-02-07
 */
public class DJView implements ActionListener, BeatObserver, BPMObserver {
    private BeatModelInterface beatModelInterface;
    private ControllerInterface controllerInterface;
    private JFrame viewFrame;
    private JPanel viewPanel;
    private BeatBar beatBar;
    private JLabel bpmOutputLabel;
    private JFrame controlFrame;
    private JPanel controlPanel;
    private JLabel bpmLabel;
    private JTextField bpmTextField;
    private JButton setBPMButton;
    private JButton increaseBPMButton;
    private JButton decreaseBPMButton;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem startMenuItem;
    private JMenuItem stopMenuItem;

    public DJView(ControllerInterface controllerInterface, BeatModelInterface beatModelInterface) {
        this.controllerInterface = controllerInterface;
        this.beatModelInterface = beatModelInterface;
        this.beatModelInterface.registerObserver((BPMObserver) this);
        this.beatModelInterface.registerObserver((BeatObserver) this);
    }
    void createView() {
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(new Dimension(100, 80));
        bpmOutputLabel = new JLabel("Offline", SwingConstants.CENTER);
        beatBar = new BeatBar();
        JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
        bpmPanel.add(beatBar);
        bpmPanel.add(bpmOutputLabel);
        viewPanel.add(bpmPanel);
        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        viewFrame.setVisible(true);
    }
    public void createControls() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));
        controlPanel = new JPanel(new GridLayout(1, 2));
        menuBar = new JMenuBar();
        menu = new JMenu("DJ Control");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerInterface.start();
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem);
        stopMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerInterface.stop();
            }
        });
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exit);
        menuBar.add(menu);
        controlFrame.setJMenuBar(menuBar);
        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10, 40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(decreaseBPMButton);
        buttonPanel.add(increaseBPMButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(bpmLabel);
        enterPanel.add(bpmTextField);

        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setBPMButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);

        Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        bpmLabel.setBorder(border);
        bpmOutputLabel.setBorder(border);

        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setBPMButton) {
            int bpm = Integer.parseInt(bpmTextField.getText());
            controllerInterface.setBPM(bpm);
        } else if (e.getSource() == increaseBPMButton) {
            controllerInterface.increaseBPM();
        } else if (e.getSource() == decreaseBPMButton) {
            controllerInterface.decreaseBPM();
        }
    }

    @Override
    public void updateBPM() {
        int bpm = beatModelInterface.getBPM();
        if (bpm == 0) {
            bpmOutputLabel.setText("Offline");
        } else {
            bpmOutputLabel.setText("Current BPM:" + beatModelInterface.getBPM());
            bpmTextField.setText(String.valueOf(beatModelInterface.getBPM()));
        }
    }

    @Override
    public void updateBeat() {
        beatBar.setValue(100);
    }

    public void disableStopMenuItem() {
        this.stopMenuItem.setEnabled(false);
    }

    public void enableStartMenuItem() {
        this.startMenuItem.setEnabled(true);
    }

    public void disableStartMenuItem() {
        this.startMenuItem.setEnabled(false);
    }

    public void enableStopMenuItem() {
        this.stopMenuItem.setEnabled(true);
    }
}
