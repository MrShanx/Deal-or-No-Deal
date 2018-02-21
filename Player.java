/*
 * Decompiled with CFR 0_123.
 */
package myPackage;

import myPackage.Case;

public class Player {
    private Case[] cases = new Case[26];
    private Case myCase;
    private int myAmount;
    private int amount;
    private String myFace;

    public Player(Case[] cases) {
        this.cases = cases;
        this.myAmount = 0;
        this.myCase = null;
        this.myFace = null;
        this.amount = 0;
    }

    public void setMyCase(int choice) {
        this.myFace = Integer.toString(choice);
        for (int i = 0; i < this.cases.length; ++i) {
            if (!this.myFace.equalsIgnoreCase(this.cases[i].getFace())) continue;
            this.myCase = this.cases[i];
        }
        this.myAmount = this.myCase.getValue();
    }

    public int getMyAmount() {
        return this.myAmount;
    }

    public void setAmount(int amt) {
        this.amount = amt;
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean removeChoice(int numToRemove) {
        boolean result = false;
        for (int i = 0; i < this.cases.length; ++i) {
            if (numToRemove != Integer.parseInt(this.cases[i].getFace())) continue;
            if (!this.cases[i].isRemoved()) {
                this.cases[i].removeCase();
                this.setAmount(this.cases[i].getValue());
                result = true;
                continue;
            }
            result = false;
        }
        return result;
    }

    public Case getMyCase() {
        return this.myCase;
    }

    public Case[] getCases() {
        return this.cases;
    }
}