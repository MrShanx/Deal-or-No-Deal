/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  myPackage.Case
 */
package myPackage;

import myPackage.Case;

public class Banker {
    private int numOfSamples = 0;
    private int average = 0;

    public int getOffer(Case[] cases, int casesRemaining, int myAmount, int turn) {
        int total = 0;
        for (int i = 0; i < cases.length; ++i) {
            if (cases[i].isRemoved()) continue;
            total += cases[i].getValue();
            ++this.numOfSamples;
        }
        this.average = (myAmount + total) / this.numOfSamples;
        return this.average * turn / 10;
    }
}