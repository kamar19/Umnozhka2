package com.example.umnozhka;

public enum Act {
        MULTIPLY("*"), DIVIDE("/"), SUBTRAC("-"),ADD("+");
        private String act;

    @Override
    public String toString() {
        return this.act;
    }

    Act(String act){
            this.act = act;
        }

    public String getAct(){ return act;}


    }
//    private String getStringCurrentAct(){
//        switch (currentAct){
//            case 1: return "*";
//            case 2: return "/";
//            case 3: return "+";
//            case 4: return "-";
//            default:return "*";
//        }
//    }

