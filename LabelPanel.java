/*
 * Decompiled with CFR 0_123.
 */
package myPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class LabelPanel
extends JPanel {
    private JPanel leftPane;
    private JPanel rightPane;
    private JLabel leftLab;
    private JLabel rightLab;
    private JLabel leftLab2;
    private final int THICK = 5;

    public LabelPanel() {
        this.setLayout(new BorderLayout());
        this.leftPane = new JPanel(new FlowLayout());
        this.leftPane.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
        this.leftPane.setBackground(Color.black);
        this.leftLab = new JLabel("?");
        this.leftLab.setOpaque(true);
        this.leftLab.setForeground(Color.white);
        this.leftLab.setBackground(Color.black);
        this.leftLab2 = new JLabel("Your Briefcase");
        this.leftLab2.setOpaque(true);
        this.leftLab2.setForeground(Color.white);
        this.leftLab2.setBackground(Color.black);
        this.leftPane.add(this.leftLab);
        this.leftPane.add(this.leftLab2);
        this.rightPane = new JPanel(new FlowLayout());
        this.rightPane.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
        this.rightPane.setBackground(Color.black);
        this.rightLab = new JLabel("Pick a case.");
        this.rightLab.setOpaque(true);
        this.rightLab.setForeground(Color.white);
        this.rightLab.setBackground(Color.black);
        this.rightPane.add(this.rightLab);
        this.add((Component)this.leftPane, "West");
        this.add(this.rightPane);
    }

    public JLabel getLeftLabel() {
        return this.leftLab;
    }

    public JLabel getRightLabel() {
        return this.rightLab;
    }
}