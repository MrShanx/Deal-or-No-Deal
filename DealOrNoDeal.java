/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  myPackage.LabelPanel
 *  myPackage.Player
 *  myPackage.Values1Panel
 *  myPackage.Values2Panel
 */
package myPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import myPackage.Banker;
import myPackage.ButtonsPanel;
import myPackage.Case;
import myPackage.LabelPanel;
import myPackage.Player;
import myPackage.Values1Panel;
import myPackage.Values2Panel;

public class DealOrNoDeal
extends JFrame {
    private static final long serialVersionUID = 1;
    private int counter = 0;
    private Banker banker = new Banker();
    private Player player;
    private Case[] cases = new Case[26];
    private Values1Panel vals1Obj;
    private Values2Panel vals2Obj;
    private ButtonsPanel buttonsObj;
    private LabelPanel labelObj;
    private int turn = 0;
    private int casesToOpen = 6;
    private int briefCases = 26;
    private int offer;
    private List<Integer> values;
    private final int WIDTH = 600;
    private final int HEIGHT = 450;
    private StringBuffer sb = new StringBuffer();

    public DealOrNoDeal() {
        super("Deal or No Deal!");
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setSize(600, 450);
        this.setLayout(new BorderLayout());
        this.buttonsObj = new ButtonsPanel();
        this.add((Component)this.buttonsObj, "Center");
        this.vals1Obj = new Values1Panel();
        this.vals2Obj = new Values2Panel();
        this.add((Component)this.vals1Obj, "West");
        this.add((Component)this.vals2Obj, "East");
        this.labelObj = new LabelPanel();
        this.add((Component)this.labelObj, "South");
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem exitAction = new JMenuItem("Exit");
        fileMenu.add(exitAction);
        exitAction.addActionListener(new MyActionListener());
        UIManager UI = new UIManager();
        UIManager.put("OptionPane.background", Color.black);
        UIManager.put("Panel.background", Color.black);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void game() throws InterruptedException {
        this.setUpCases();
        this.player = new Player(this.cases);
        this.pickFirstCase();
        do {
            if (this.turn < 6) {
                this.pickCases(this.casesToOpen);
                this.setEnabled(false);
                this.setVisible(true);
            } else {
                this.pickCases(1);
                this.setEnabled(false);
                this.setVisible(true);
            }
            Thread.sleep(1500);
            this.bank();
            this.setVisible(true);
            this.setEnabled(true);
        } while (this.briefCases != 1);
        this.endGameCase(this.player.getMyAmount());
        this.dispose();
        System.exit(0);
    }

    private void setUpCases() {
        this.values = new LinkedList<Integer>(Arrays.asList(1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000, 2000000));
        Collections.shuffle(this.values);
        for (int i = 0; i < this.values.size(); ++i) {
            this.cases[i] = new Case(this.values.get(i), i + 1);
        }
    }

    private void pickFirstCase() {
        do {
            System.out.print("");
        } while (this.buttonsObj.getCounter() == 0);
        if (this.buttonsObj.getCounter() > 0) {
            System.out.println("START");
        }
        int choice = this.buttonsObj.getCaseNumber();
        this.player.setMyCase(choice);
        this.player.getMyCase().removeCase();
        JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>You chose case #" + choice + "</font></h2></html>", "Welcome", -1);
        String face = this.player.getMyCase().getFace();
        this.labelObj.getLeftLabel().setText(face);
        this.labelObj.getLeftLabel().setOpaque(true);
        this.labelObj.getLeftLabel().setBackground(Color.gray);
        ++this.turn;
        --this.briefCases;
        ++this.counter;
    }

    private void showDialog(String message) {
        JPanel pan = new JPanel();
        final JDialog dlg = new JDialog();
        dlg.add(pan);
        pan.add(new JLabel(message));
        dlg.setDefaultCloseOperation(0);
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dlg.setVisible(false);
            }
        }).start();
        dlg.pack();
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true);
    }

    private void pickCases(int casesToRemove) {
        int choice = 0;
        int caseToOp = casesToRemove;
        String text = null;
        for (int j = 0; j < casesToRemove; ++j) {
            text = caseToOp > 1 ? "Please select " + caseToOp + " cases to be removed." : "Please select " + caseToOp + " case to be removed.";
            this.labelObj.getRightLabel().setText(text);
            while (this.buttonsObj.getCounter() == this.counter) {
                System.out.print("");
            }
            choice = this.buttonsObj.getCaseNumber();
            if (this.player.removeChoice(choice)) {
                String msg;
                this.sb.delete(0, this.sb.length());
                --this.briefCases;
                --caseToOp;
                int money = this.player.getAmount();
                if (money <= 1000) {
                    this.vals1Obj.getLabel(money).setOpaque(true);
                    this.vals1Obj.getLabel(money).setBackground(Color.darkGray);
                    msg = this.sb.append("<html><title>Opened<h2><font color='white'><center>$" + Integer.toString(money) + "</center></font></h2></html>").toString();
                    this.showDialog(msg);
                } else {
                    this.vals2Obj.getLabel(money).setOpaque(true);
                    this.vals2Obj.getLabel(money).setBackground(Color.darkGray);
                    if (money <= 75000) {
                        msg = this.sb.append("<html><title>Opened<h2><font color='white'><center>$" + Integer.toString(money) + "</center></font></h2></html>").toString();
                        this.showDialog(msg);
                    } else {
                        String msg2 = this.sb.append("<html><title>Opened<h2><font color='red'><center>$" + Integer.toString(money) + "</center></font></h2></html>").toString();
                        this.showDialog(msg2);
                    }
                }
            } else {
                --j;
            }
            ++this.counter;
        }
        ++this.turn;
        --this.casesToOpen;
    }

    private void bank() {
        this.sb.delete(0, this.sb.length());
        this.labelObj.getRightLabel().setText("Banker's calling...");
        this.offer = 0;
        int ans = 0;
        this.offer = this.banker.getOffer(this.cases, this.briefCases, this.player.getMyAmount(), this.turn);
        this.showDialog("<html><h2><font color='blue'>Ring...</font></h2></html>");
        try {
            Thread.sleep(1750);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg = this.sb.append("<html><title>Banker's Offer<h2><font color='white'><center>$" + Integer.toString(this.offer) + "</center></font></h2></html>").toString();
        int counter = 0;
        do {
            if (this.briefCases == 1) {
                JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>This is the Banker's FINAL offer</font></h2></html>", "Final", -1);
                ans = JOptionPane.showConfirmDialog(null, "<html><h2><font color ='white'>The Banker's final offer is.. $" + this.offer + "</font></h2></html>", "Final", 0);
            } else {
                ans = JOptionPane.showConfirmDialog(null, msg, "Banker's Offer", 0);
            }
            if (ans == 0) {
                ++counter;
                this.endGameOffer(this.offer);
            }
            if (ans != 1) continue;
            ++counter;
        } while (counter == 0);
    }

    private void endGameOffer(int amt) {
        JOptionPane.showMessageDialog(null, "<html><h2><font color='green'>DEAL</font></h2></html>", "Accept", -1);
        JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>You accepted the banker's offer of $" + amt + "</font></h2></html>", "Accept", -1);
        if (this.player.getMyAmount() < amt) {
            JOptionPane.showMessageDialog(null, "<html><h2><font color='green'>Your case had... $" + this.player.getMyAmount() + "</font></h2></html>", "Great Decision", -1);
            JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>Till next time on DEAL OR NO DEAL!</font></h2></html>", "End", -1);
        } else {
            JOptionPane.showMessageDialog(null, "<html><h2><font color='red'>You missed out on... $" + this.player.getMyAmount() + "</font></h2></html>", "It's Okay", 2);
            JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>Till next time on DEAL OR NO DEAL!</font></h2></html>", "End", -1);
        }
        this.dispose();
        System.exit(0);
    }

    private void endGameCase(int amt) {
        JOptionPane.showMessageDialog(null, "<html><h2><font color='red'>NO DEAL</font></h2></html>", "Decline", -1);
        JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>The banker's offer was $" + this.offer + "</font></h2></html>", "Decline", -1);
        JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>Opening... case#" + this.player.getMyCase().getFace() + "</font></h2></html>", "Decline", -1);
        if (amt > this.offer) {
            JOptionPane.showMessageDialog(null, "<html><h2><font color='green'>You just won $" + amt + " dollars</font></h2></html>", "Congratulations", -1);
            JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>Till next time on DEAL OR NO DEAL!</font></h2></html>", "End", -1);
        } else {
            JOptionPane.showMessageDialog(null, "<html><h2><font color='red'>That's okay! You're still going home with $" + amt + "</font></h1></html>", "Better luck next time", 2);
            JOptionPane.showMessageDialog(null, "<html><h2><font color='white'>Till next time on DEAL OR NO DEAL!</font></h2></html>", "End", -1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DealOrNoDeal dnd = new DealOrNoDeal();
        dnd.game();
    }

    private class MyActionListener
    implements ActionListener {
        private MyActionListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Exit")) {
                DealOrNoDeal.this.dispose();
                System.exit(0);
            }
        }
    }

}