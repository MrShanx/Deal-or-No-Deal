/*
 * Decompiled with CFR 0_123.
 */
package myPackage;

public class Case {
    private int amount = 0;
    private String face = "";
    public boolean removed = false;

    public Case(int amt, int face) {
        this.amount = amt;
        this.face = Integer.toString(face);
    }

    public int getValue() {
        return this.amount;
    }

    public String getFace() {
        return this.face;
    }

    public boolean isRemoved() {
        return this.removed;
    }

    public void removeCase() {
        this.removed = true;
    }
}