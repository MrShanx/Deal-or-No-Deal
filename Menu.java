/*
 * Decompiled with CFR 0_123.
 */
//Currently not included when application is run
package myPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import myPackage.DealOrNoDeal;

public class Menu
extends JFrame {
    private static final long serialVersionUID = 1;
    private JButton play;
    private JButton exit;
    private JPanel panel;
    private DealOrNoDeal dnd;

    public Menu() {
        super("Deal or No Deal!");
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.dnd = new DealOrNoDeal();
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("../images/dnd.jpg"))));
        this.setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.play = new JButton("play");
        this.panel.add(this.play);
        this.exit = new JButton("exit");
        this.panel.add(this.exit);
        this.panel.setBackground(Color.WHITE);
        this.add((Component)this.panel, "South");
        this.play.addActionListener(new MenuListener());
        this.exit.addActionListener(new MenuListener());
        this.pack();
        this.setVisible(true);
    }

    private class MenuListener
    implements ActionListener {
        private MenuListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "play") {
                Menu.this.setVisible(false);
                Menu.this.dnd.setVisible(true);
                try {
                    Menu.this.dnd.game();
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Menu.this.dispose();
            }
        }
    }

}
