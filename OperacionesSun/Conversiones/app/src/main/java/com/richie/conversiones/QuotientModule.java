package com.richie.conversiones;

/**
 * Created by richie on 9/7/17.
 */

public class QuotientModule {

    // (q,r) { q = a div d is the quotient, r = a mod d is the remainder }

    private int module;
    private int quotient;

    public QuotientModule(int quotient, int module) {
        this.module = module;
        this.quotient = quotient;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getQuotient() {
        return quotient;
    }

    public void setQuotient(int quotient) {
        this.quotient = quotient;
    }
}
