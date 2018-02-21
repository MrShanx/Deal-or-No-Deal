/*
 * Decompiled with CFR 0_123.
 */
package myPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Values1Panel
extends JPanel {
    private JLabel[] labels;
    private String[] ints;
    private final int THICK = 5;

    public Values1Panel() {
        this.setLayout(new GridLayout(13, 1));
        this.ints = new String[13];
        this.ints[0] = "                $1 ";
        this.ints[1] = "                $5 ";
        this.ints[2] = "              $10 ";
        this.ints[3] = "              $25 ";
        this.ints[4] = "              $50 ";
        this.ints[5] = "              $75 ";
        this.ints[6] = "            $100 ";
        this.ints[7] = "            $200 ";
        this.ints[8] = "            $300 ";
        this.ints[9] = "            $400 ";
        this.ints[10] = "            $500 ";
        this.ints[11] = "            $750 ";
        this.ints[12] = "          $1000 ";
        this.labels = new JLabel[13];
        for (int i = 0; i < this.labels.length; ++i) {
            this.labels[i] = new JLabel(this.ints[i]);
            this.labels[i].setHorizontalTextPosition(4);
            this.labels[i].setOpaque(true);
            this.labels[i].setBackground(Color.YELLOW);
            this.labels[i].setForeground(Color.black);
            this.add(this.labels[i]);
            this.labels[i].setBorder(BorderFactory.createLineBorder(Color.black, 3));
        }
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
    }

    public JLabel[] getLabel() {
        return this.labels;
    }

    public JLabel getLabel(int value) {
        JLabel lab = null;
        for (int i = 0; i < this.ints.length; ++i) {
            String msg = this.ints[i];
            msg = msg.replace("$", "");
            int num = Integer.parseInt(msg = msg.replaceAll("\\s", ""));
            if (value != num) continue;
            lab = this.labels[i];
        }
        return lab;
    }
}