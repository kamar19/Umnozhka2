package com.example.umnozhka;

public class MyNumberAddSub extends MyNumber {

    public MyNumberAddSub(int minValue, int maxValue) {
        super(minValue, maxValue);
        // оставляем только генерацию
        if (maxValue>100) setMaxValue(100);

        setValue(getMinValue() + (int) (Math.random() * getMaxValue()));//0..9, 10..50 = 10-0, 50-10=40

    }
    public int getCountBool() {
        // Посчитаем количество разрешенных операций, если все закрыты, назначаем одну - умножение
//        int countBool = 0;
        return this.getMaxValue()-this.getMinValue();
    }


}
