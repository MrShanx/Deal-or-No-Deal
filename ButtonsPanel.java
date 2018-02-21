/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  myPackage.Values1Panel
 *  myPackage.Values2Panel
 */
package myPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import myPackage.Values1Panel;
import myPackage.Values2Panel;

public class ButtonsPanel
extends JPanel {
    public Values1Panel vals1Obj;
    public Values2Panel vals2Obj;
    private JPanel first_panel;
    private JPanel second_panel;
    private JPanel third_panel;
    private JPanel fourth_panel;
    private JPanel main1_panel;
    private JPanel main2_panel;
    private JButton[] buttons;
    private final int WIDTH = 40;
    private final int HEIGHT = 35;
    private int caseNumber;
    private int caseIndex;
    private int casesToOpen;
    private int turn = 0;
    private int counter = 0;
    public boolean pressed = false;

    public ButtonsPanel() {
        this.setLayout(new BorderLayout());
        this.buttons = new JButton[26];
        this.first_panel = new JPanel(new FlowLayout());
        this.first_panel.setOpaque(true);
        this.first_panel.setBackground(Color.black);
        this.second_panel = new JPanel(new FlowLayout());
        this.second_panel.setOpaque(true);
        this.second_panel.setBackground(Color.black);
        this.third_panel = new JPanel(new FlowLayout());
        this.third_panel.setOpaque(true);
        this.third_panel.setBackground(Color.black);
        this.fourth_panel = new JPanel(new FlowLayout());
        this.fourth_panel.setOpaque(true);
        this.fourth_panel.setBackground(Color.black);
        this.main1_panel = new JPanel(new GridLayout(2, 1));
        this.main2_panel = new JPanel(new GridLayout(2, 1));
        for (int i = 0; i < this.buttons.length; ++i) {
            this.buttons[i] = new JButton(Integer.toString(i + 1));
            this.buttons[i].setOpaque(true);
            this.buttons[i].setForeground(Color.black);
            this.buttons[i].setPreferredSize(new Dimension(40, 35));
            this.buttons[i].setBorder(BorderFactory.createLineBorder(Color.black));
            if (i < 6) {
                this.first_panel.add(this.buttons[i]);
            }
            if (i > 5 && i < 13) {
                this.second_panel.add(this.buttons[i]);
            }
            if (i > 12 && i < 20) {
                this.third_panel.add(this.buttons[i]);
            }
            if (i <= 19 || i >= 26) continue;
            this.fourth_panel.add(this.buttons[i]);
        }
        JPanel topPane = new JPanel();
        topPane.setBackground(Color.black);
        JLabel imgLab = new JLabel(new ImageIcon(this.getClass().getResource("../images/dnd2.png")));
        topPane.add(imgLab);
        this.main2_panel.add(this.fourth_panel);
        this.main2_panel.add(this.third_panel);
        this.main1_panel.add(this.second_panel);
        this.main1_panel.add(this.first_panel);
        this.add((Component)topPane, "North");
        this.add((Component)this.main2_panel, "Center");
        this.add((Component)this.main1_panel, "South");
        for (JButton button : this.buttons) {
            button.addActionListener(new MyActionListener());
        }
    }

    public JButton[] getButtons() {
        return this.buttons;
    }

    public int getCaseIndex() {
        return this.caseIndex;
    }

    public int getCaseNumber() {
        return this.caseNumber;
    }

    public int getCasesToOpen() {
        return this.casesToOpen;
    }

    public int getCounter() {
        return this.counter;
    }

    private class MyActionListener
    implements ActionListener {
        private MyActionListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (ButtonsPanel.this.turn) {
                case 0: {
                    ButtonsPanel.this.caseNumber = Integer.parseInt(e.getActionCommand());
                    ButtonsPanel.this.caseIndex = ButtonsPanel.this.caseNumber - 1;
                    ButtonsPanel.this.buttons[Integer.parseInt(e.getActionCommand()) - 1].setVisible(false);
                    ButtonsPanel.this.buttons[Integer.parseInt(e.getActionCommand()) - 1].setText("");
                    ButtonsPanel.this.turn++;
                    ButtonsPanel.this.counter++;
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: 
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: {
                    ButtonsPanel.this.caseNumber = Integer.parseInt(e.getActionCommand());
                    ButtonsPanel.this.caseIndex = ButtonsPanel.this.caseNumber - 1;
                    ButtonsPanel.this.buttons[Integer.parseInt(e.getActionCommand()) - 1].setVisible(false);
                    ButtonsPanel.this.turn++;
                    ButtonsPanel.this.counter++;
                }
            }
        }
    }

}