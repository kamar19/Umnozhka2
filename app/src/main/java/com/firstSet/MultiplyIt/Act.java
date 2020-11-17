package com.firstSet.MultiplyIt;

public enum Act {
        MULTIPLY("*"), DIVIDE("/"), SUBTRAC("-"),ADD("+");
        private final String act;

    @Override
    public String toString() {
        return this.act;
    }

    Act(String act){
            this.act = act;
        }

    public String getAct(){ return act;}


    }

