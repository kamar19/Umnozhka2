package com.example.umnozhka;

public class MyTask {
    private MyNumber currentOneUnit, currentTwoUnit;
    private MyAct currentAct;
    private int answer;

    public void setCurrentAct(MyAct currentAct) {
        this.currentAct = currentAct;
    }

    public MyNumber getCurrentOneUnit() {
        return currentOneUnit;
    }

    public MyNumber getCurrentTwoUnit() {
        return currentTwoUnit;
    }



    public MyAct getCurrentAct() {
        return currentAct;
    }

    public MyTask(int currentOneUnitMin,int currentOneUnitMax,MyAct currentAct) {
        // создается начальное = 1 значение чисел
        // потому, что мы пока не знаем предельных значений чисел,
        this.currentOneUnit = new MyNumber(currentOneUnitMin,currentOneUnitMax);//Значения чисел 0..9, 10..50 = 10-0, 50-10=40
        this.currentTwoUnit = new MyNumber(currentOneUnitMin,currentOneUnitMax);
        if (currentAct.getMyAct() ==Act.DIVIDE) {  // Если Действие - деление, то Первое число произведения двух чисел.
            currentOneUnit.setValue(currentOneUnit.getValue() * currentTwoUnit.getValue());
        } else
        if (currentAct.getMyAct()==Act.SUBTRAC) { // Если Действие - вычитание, то чье значение больше тот будет первым значением
            if (currentOneUnit.getValue() < currentTwoUnit.getValue()) {
                int tempInt = currentOneUnit.getValue();
                currentOneUnit.setValue(currentTwoUnit.getValue());
                currentTwoUnit.setValue(tempInt);
            }
        } ;
        this.setCurrentAct(currentAct);
    };



}
