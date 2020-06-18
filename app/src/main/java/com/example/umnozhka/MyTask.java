package com.example.umnozhka;

public class MyTask {
    private MyNumber currentOneUnit, currentTwoUnit;
    private MyAct currentAct;
//    private int answer;

    public void setCurrentAct(MyAct currentAct) {
        this.currentAct = currentAct;
    }

    public MyNumber getCurrentOneUnit() {
         return currentOneUnit;
    }
    //        if (MyNumber.class.isInstance(currentOneUnit))
    //        { return currentOneUnit;}
    //        else if  (MyNumberOneTen.class.isInstance(currentOneUnit))
    //            return currentOneUnit;}
    //    }


    public MyNumber getCurrentTwoUnit() {
        return currentTwoUnit;
    }



    public MyAct getCurrentAct() {
        return currentAct;
    }

    public MyTask(int currentOneUnitMin,int currentOneUnitMax,MyAct currentAct,boolean SETTINGS_MULTIPLYS []) {
        if (currentAct.getMyAct() == Act.MULTIPLY | currentAct.getMyAct() == Act.DIVIDE) {
            // Если операция умножения или деления, то нужно сгенерировать сисла от 1 до 10,
            // с учетом включенных значений чисел
            this.currentOneUnit = new MyNumberOneTen(currentOneUnitMin, currentOneUnitMax, SETTINGS_MULTIPLYS );//Значения чисел 0..9, 10..50 = 10-0, 50-10=40
            this.currentTwoUnit = new MyNumberOneTen(currentOneUnitMin, currentOneUnitMax, SETTINGS_MULTIPLYS );


            //// Если Действие - деление, то Первое число произведения двух чисел.
            if (currentAct.getMyAct() == Act.DIVIDE) {
                currentOneUnit.setValue(currentOneUnit.getValue() * currentTwoUnit.getValue());
            }
        } else {
            //
            this.currentOneUnit = new MyNumber(currentOneUnitMin, currentOneUnitMax);//Значения чисел 0..9, 10..50 = 10-0, 50-10=40
            this.currentTwoUnit = new MyNumber(currentOneUnitMin, currentOneUnitMax);

            if (currentAct.getMyAct() == Act.SUBTRAC) { // Если Действие - вычитание, то чье значение больше тот будет первым значением
                if (currentOneUnit.getValue() < currentTwoUnit.getValue()) {
                    int tempInt = currentOneUnit.getValue();
                    currentOneUnit.setValue(currentTwoUnit.getValue());
                    currentTwoUnit.setValue(tempInt);
                }
            }
            ;

        } ;
        this.setCurrentAct(currentAct);
    }
}
