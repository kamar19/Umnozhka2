package com.example.umnozhka;

public class MyAct {
    private Act myAct;

    public void setMyAct(Act myAct) {
        this.myAct = myAct;
    }

    @Override
    public String toString() {
        return myAct.toString();
    }

    public Act getMyAct() {
        return myAct;
    }


    public MyAct(boolean SETTINGS_MULTIPLY, boolean SETTINGS_DIVIDE, boolean SETTINGS_ADD, boolean SETTINGS_SUBTRAC) {
        this.myAct=generateAct(SETTINGS_MULTIPLY, SETTINGS_DIVIDE, SETTINGS_ADD, SETTINGS_SUBTRAC);
    }


    public Act generateAct(boolean SETTINGS_MULTIPLY, boolean SETTINGS_DIVIDE, boolean SETTINGS_ADD, boolean SETTINGS_SUBTRAC) {
//int a = 0; // Начальное значение диапазона - "от"
//      int b = 10; // Конечное значение диапазона - "до"
//
//      int random_number1 = a + (int) (Math.random() * b); // Генерация 1-го числа
        int Variants = 0;
        Act resultAct = Act.MULTIPLY;
        int tempResultAct=0;

        if (SETTINGS_MULTIPLY) Variants++;
        if (SETTINGS_DIVIDE) Variants++;
        if (SETTINGS_ADD) Variants++;
        if (SETTINGS_SUBTRAC) Variants++;
        switch (Variants) {
            case 0:
                Variants++;
            case 1:
                if (SETTINGS_MULTIPLY)  resultAct = Act.MULTIPLY;
                if (SETTINGS_DIVIDE)  resultAct = Act.DIVIDE;
                if (SETTINGS_ADD)  resultAct = Act.ADD;
                if (SETTINGS_SUBTRAC)  resultAct = Act.SUBTRAC;
                break;
            case 2:
                tempResultAct = (int) (Math.random() * 2);
                if (tempResultAct == 0) {
                    if (SETTINGS_MULTIPLY) {
                        resultAct = Act.MULTIPLY;
                    } else if (SETTINGS_DIVIDE) {
                        resultAct = Act.DIVIDE;
                    } else
                        resultAct = Act.ADD;
                }
                if (tempResultAct == 1) {
                    if (SETTINGS_SUBTRAC) {
                        resultAct = Act.SUBTRAC;
                    } else if (SETTINGS_ADD) {
                        resultAct = Act.ADD;
                    } else
                        resultAct = Act.DIVIDE;
                }
                break;
            case 3:
                tempResultAct = (int) (Math.random() * 3);
                // из каких трей действий?
                switch (tempResultAct){
                    case 0:  if (!SETTINGS_MULTIPLY) resultAct = Act.SUBTRAC;
                        // Пропускаем 1-е действие, т.е если не используется умножение, то
                        // включены 2,3,4 действия, и 1-е действие деление
                    else resultAct = Act.MULTIPLY;
                        break;
                    case 1:  if (SETTINGS_MULTIPLY) {
                        if (SETTINGS_DIVIDE) resultAct = Act.DIVIDE;
                        else resultAct = Act.ADD;
                    } else resultAct = Act.ADD;
                        break;
                    case 2: if (SETTINGS_ADD)  resultAct = Act.ADD ;
                    else resultAct = Act.SUBTRAC ;
                        break;

                }
                break;
            case 4:
                tempResultAct = (int) (Math.random() * 4);
                // все Действия
                switch (tempResultAct) {
                    case 0: resultAct = Act.MULTIPLY;
                        break;
                    case 1: resultAct = Act.DIVIDE;
                        break;
                    case 2: resultAct = Act.ADD;
                        break;
                    case 3: resultAct = Act.SUBTRAC;
                        break;
                }
                break;
            default:

                resultAct = Act.MULTIPLY;
        }

        return resultAct;
    }

}
