package com.example.umnozhka;

public class MyNumberAddSub extends MyNumber {

    public MyNumberAddSub(int minValue, int maxValue) {
        super(minValue, maxValue);
        // оставляем только генерацию

        setValue(getMinValue() + (int) (Math.random() * getMaxValue()));//0..9, 10..50 = 10-0, 50-10=40

    }


}
