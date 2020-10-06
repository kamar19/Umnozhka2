package com.example.umnozhka;

public abstract class MyNumber {
    private int value;
    private int minValue,maxValue;

    public MyNumber(int minValue, int maxValue) {
        // здесь была генерация
        // при создании объекта генерируется его значение.
        // правильно ли это?
        // Наверное не правильно, нужно отделить генерацию значения и консуруктор.
        if (minValue<1) minValue=0;
        if (maxValue<1) maxValue=10;
        this.minValue = minValue;
        this.maxValue = maxValue;
        // а генерацию оставим в подклассах
//        this.value = this.minValue + (int) (Math.random() * this.maxValue);//0..9, 10..50 = 10-0, 50-10=40

    }

//    private int getRandomValue(){
//        // Что бы не только при создании объекта можно было генерировать новое значение
//        // Может этого и не нужно
//        return this.minValue + (int) (Math.random() * this.maxValue);//0..9, 10..50 = 10-0, 50-10=40
//    }

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

    public int getMaxValue() {
        return maxValue;
    }

/*    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    public void setMaxValue(int maxValue) {
        maxValue = maxValue;
    }

*/



}
