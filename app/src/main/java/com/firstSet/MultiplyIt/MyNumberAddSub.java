package com.firstSet.MultiplyIt;

public class MyNumberAddSub extends MyNumber {

    public MyNumberAddSub(int minValue, int maxValue) {
        super(minValue, maxValue);
        // оставляем только генерацию
        if (maxValue<minValue) {
            int tempInt=minValue;
            minValue=maxValue;
            maxValue=tempInt;
        }

        if (minValue>99)  setMinValue(99);
        if (maxValue>100) setMaxValue(100);
        if (minValue<1) setMinValue(1);
        if (maxValue<2) setMaxValue(2);

        setValue(getMinValue() + (int) (Math.random() * (getMaxValue()-getMinValue())));//0..9, 10..50 = 10-0, 50-10=40

    }
    public MyNumberAddSub(int minValue, int maxValue, int value) {
        super(minValue, maxValue);
        // этот конструктор нужен для готовых значений, из сохраненного Siring вопроса типа 3*5
        setValue(value);
    }


    public int getCountBool() {
        // Посчитаем количество разрешенных операций, если все закрыты, назначаем одну - умножение
//        int countBool = 0;
        return this.getMaxValue()-this.getMinValue();
    }


}
