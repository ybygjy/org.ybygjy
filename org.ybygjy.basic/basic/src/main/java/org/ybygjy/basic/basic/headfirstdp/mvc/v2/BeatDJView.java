package org.ybygjy.basic.basic.headfirstdp.mvc.v2;

import org.ybygjy.basic.basic.headfirstdp.mvc.v1.BeatBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author leye
 * @version 2018-03-18
 */
public class BeatDJView implements BeatModelObserver {
    private BeatController beatController;
    private int defBPMVal;
    private InnerDJView innerDJView;
    private InnerDJControlView innerDJControlView;
    public BeatDJView(BeatController beatController, BeatModel2 beatModel2) {
        this.beatController = beatController;
        this.defBPMVal = beatModel2.getBPMModel2().getBpmVal();
        beatModel2.registerObserver(this);
        innerDJView = new InnerDJView();
        innerDJControlView = new InnerDJControlView();
    }

    @Override
    public void onChangeBPM(BPMModel2 bpmModelOld, BPMModel2 bpmModelNew) {
        innerDJView.setBPMVal(bpmModelNew.getBpmVal());
        innerDJControlView.setBPMVal(bpmModelNew.getBpmVal());
    }

    @Override
    public void onChangeStatus(BeatModel2 beatModel) {
        System.out.println("BeatModel Status Changed!");
    }

    public void show() {
        //渲染界面
        innerDJView.show();
        innerDJControlView.show();
    }
    class InnerDJView {
        private JFrame viewFrame = null;
        private BeatBar beatBar;
        public InnerDJView() {
            JPanel viewPanel = new JPanel(new GridLayout(1, 2));
            viewFrame = new JFrame("View");
            viewFrame.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
            viewFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            viewFrame.setSize(new Dimension(100, 80));
            JLabel bpmOutputLabel = new JLabel("Offline", SwingConstants.CENTER);
            bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            beatBar = new BeatBar();
            beatBar.setMaximum(100);
            beatBar.setValue(defBPMVal);
            beatBar.setForeground(Color.ORANGE);
            JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
            bpmPanel.add(beatBar);
            bpmPanel.add(bpmOutputLabel);
            viewPanel.add(bpmPanel);
            viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
            viewFrame.pack();
        }
        public void show() {
            if (null == viewFrame) {
                return;
            }
            viewFrame.setVisible(true);
        }
        public void setBPMVal(int bpmVal) {
            beatBar.setValue(bpmVal);
        }
    }
    class InnerDJControlView {
        private JFrame controlFrame;
        private JTextField bpmTextField;
        public InnerDJControlView() {
            JFrame.setDefaultLookAndFeelDecorated(true);
            controlFrame = new JFrame("Control");
            controlFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            controlFrame.setSize(new Dimension(100, 80));
            JPanel controlPanel = new JPanel(new GridLayout(1, 2));
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("DJ Control");
            JMenuItem startMenuItem = new JMenuItem("Start");
            menu.add(startMenuItem);
            startMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    beatController.start();
                }
            });
            JMenuItem stopMenuItem = new JMenuItem("Stop");
            menu.add(stopMenuItem);
            stopMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    beatController.stop();
                }
            });
            JMenuItem exit = new JMenuItem("Quit");
            exit.addActionListener((s)-> System.exit(0));
            menu.add(exit);
            menuBar.add(menu);
            controlFrame.setJMenuBar(menuBar);
            bpmTextField = new JTextField(String.valueOf(defBPMVal), 2);
            JLabel bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
            JButton setBPMButton = new JButton("Set");
            setBPMButton.setSize(new Dimension(10, 40));
            JButton increaseBPMButton = new JButton(">>");
            JButton decreaseBPMButton = new JButton("<<");
            setBPMButton.addActionListener((e)->{
                String tmpVal = bpmTextField.getText();
                if (tmpVal != null && tmpVal.matches("\\d+\\.?\\d*")) {
                    beatController.setBPM(Integer.parseInt(tmpVal));
                }
            });
            increaseBPMButton.addActionListener((s)-> beatController.increaseBPM());
            decreaseBPMButton.addActionListener((s)-> beatController.decreaseBPM());

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
            bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            controlFrame.getRootPane().setDefaultButton(setBPMButton);
            controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);
            controlFrame.pack();
        }
        public void setBPMVal(int bpmVal) {
            this.bpmTextField.setText(String.valueOf(bpmVal));
        }
        public void show() {
            if (null == controlFrame) {
                return;
            }
            controlFrame.setVisible(true);
        }
    }
}
