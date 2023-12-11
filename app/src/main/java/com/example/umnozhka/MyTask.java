package com.example.umnozhka;

public class MyTask {
    private MyNumber currentOneUnit, currentTwoUnit;
    private MyAct currentAct;

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

    public MyTask(int currentOneUnitMin, int currentOneUnitMax, MyAct currentAct, boolean SETTINGS_MULTIPLYS[]) {
        // В конструктор получем currentAct
        setCurrentAct(currentAct);
        // Присваеваем его значение для this.currentAc

        if (this.currentAct.getMyAct() == Act.MULTIPLY | this.currentAct.getMyAct() == Act.DIVIDE) {
            // Если операция умножения или деления, то нужно сгенерировать сисла от 1 до 10,
            // с учетом включенных значений чисел
            currentOneUnit = new MyNumberOneTen(currentOneUnitMin, currentOneUnitMax, SETTINGS_MULTIPLYS);//Значения чисел 0..9, 10..50 = 10-0, 50-10=40
            currentTwoUnit = new MyNumberOneTen(currentOneUnitMin, currentOneUnitMax, SETTINGS_MULTIPLYS);

            //// Если Действие - деление, то Первое число произведения двух чисел.
            if (this.currentAct.getMyAct() == Act.DIVIDE) {
                currentOneUnit.setValue(currentOneUnit.getValue() * currentTwoUnit.getValue());
            }
        } else {
            //
            currentOneUnit = new MyNumberAddSub(currentOneUnitMin, currentOneUnitMax);//Значения чисел 0..9, 10..50 = 10-0, 50-10=40
            currentTwoUnit = new MyNumberAddSub(currentOneUnitMin, currentOneUnitMax);

            if (this.currentAct.getMyAct() == Act.SUBTRAC) {
                // Если Действие - вычитание, то чье значение больше тот будет первым значением
                if (currentOneUnit.getValue() < currentTwoUnit.getValue()) {
                    int tempInt = currentOneUnit.getValue();
                    currentOneUnit.setValue(currentTwoUnit.getValue());
                    currentTwoUnit.setValue(tempInt);
                }
            }
            ;
        }
        ;
    }
}
