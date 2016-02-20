package com.example.app.models;

public enum NormalizedStep {

    C, C_SHARP, D, E_FLAT, E, F, F_SHARP, G, G_SHARP, A, B_FLAT, B;

    public static NormalizedStep getNormalizedStepFrom(Step step, Alter alter) {
        if (step == Step.C) {
            return C;
        }
        if (step == Step.C && alter == Alter.SHARP ||
                step == Step.D && alter == Alter.FLAT) {
            return C_SHARP;
        }
        if (step == Step.D) {
            return D;
        }
        if (step == Step.D && alter == Alter.SHARP ||
                step == Step.E && alter == Alter.FLAT) {
            return E_FLAT;
        }
        if (step == Step.E) {
            return E;
        }
        if (step == Step.F) {
            return F;
        }
        if (step == Step.F && alter == Alter.SHARP ||
                step == Step.G && alter == Alter.FLAT) {
            return F_SHARP;
        }
        if (step == Step.G) {
            return G;
        }
        if (step == Step.G && alter == Alter.SHARP ||
                step == Step.A && alter == Alter.FLAT) {
            return G_SHARP;
        }
        if (step == Step.A) {
            return A;
        }
        if (step == Step.A && alter == Alter.SHARP ||
                step == Step.B && alter == Alter.FLAT) {
            return B_FLAT;
        }
        if (step == Step.B) {
            return B;
        }
        return null;
    }

}
