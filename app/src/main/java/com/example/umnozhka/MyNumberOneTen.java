package com.example.umnozhka;

public class MyNumberOneTen extends MyNumber {

    private boolean[] SETTINGS_MULTIPLYS = {false, false, false, false, false, false, false, false, false, false};
    // доступ только из конструктора

//    @Override
//    public String toString() {
//        return String.valueOf(this.getValue());
////        return super.toString() ;
//    }

    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }

    public int getCountBool() {
        // Посчитаем количество разрешенных операций, если все закрыты, назначаем одну - умножение
        int countBool = 0;
        for (boolean b : SETTINGS_MULTIPLYS)
            if (b) countBool++;
        return countBool;
    }

    public MyNumberOneTen(int minValue, int maxValue, boolean SETTINGS_MULTIPLYS[]) {
        super(minValue, maxValue);
        // Можно и не писать конструктор супер, но для наглядности напишу
        if (maxValue > 10) setMaxValue(10);

        setValue(getMinValue() + (int) (Math.random() * getMaxValue()));//0..9, 10..50 = 10-0, 50-10=40
//        this.setValue(new MyNumber(minValue, maxValue).getValue());

        this.SETTINGS_MULTIPLYS = SETTINGS_MULTIPLYS;
        // Принимаем параметры настроек

        if (getCountBool() == 0) this.SETTINGS_MULTIPLYS[1] = true;
        // Если все операции закрыты, назначаем одну - умножение
        if (this.getValue() > 10) setValue(10);
        // переодичсеки вылетает, не могу вычислить где ошибка  java.lang.ArrayIndexOutOfBoundsException: показывает на эту строку.
        if (!SETTINGS_MULTIPLYS[this.getValue() - 1]) {
            // Если сгенерированно число, но оно не разрешено, то занова генерируем число
            MyNumberOneTen myNumberOneTen2 = new MyNumberOneTen(getMinValue(), getMaxValue(), this.SETTINGS_MULTIPLYS);
            setValue(myNumberOneTen2.getValue());
        }
    }
}
