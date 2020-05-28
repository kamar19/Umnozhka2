package com.example.umnozhka;

public class MyNumber {
    private int value;
    private int minValue,MaxValue;

    public MyNumber(int minValue, int maxValue) {
        this.minValue = minValue;
        MaxValue = maxValue;
        value =minValue + (int) (Math.random() * maxValue);//0..9, 10..50 = 10-0, 50-10=40
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value) ;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return MaxValue;
    }

    public void setMaxValue(int maxValue) {
        MaxValue = maxValue;
    }


}
