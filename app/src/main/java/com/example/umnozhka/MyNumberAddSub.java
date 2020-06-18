package com.example.umnozhka;

public class MyNumberAddSub extends MyNumber{

    public MyNumberAddSub(int minValue, int maxValue) {
        super(minValue, maxValue);
        // при создании объекта генерируется его значение.
        // правильно ли это?
        // Наверное не правильно, нужно отделить генерацию значени и консуруктор.
        if (minValue<1) minValue=0;
        if (maxValue<1) maxValue=10;

//        this.minValue = minValue;
//        this.maxValue = maxValue;
//        this.value = this.minValue + (int) (Math.random() * this.maxValue);//0..9, 10..50 = 10-0, 50-10=40

    }


}
