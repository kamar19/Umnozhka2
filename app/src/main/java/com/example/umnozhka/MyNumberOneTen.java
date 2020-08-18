package com.example.umnozhka;

public class MyNumberOneTen extends MyNumber {
//    private int value;
//    private int minValue,maxValue;
    private boolean [] SETTINGS_MULTIPLYS = { false,false, false, false, false, false, false, false, false, false};
//    private boolean SETTINGS_MULTIPLY_1=false; // Умножение на 1
//    private boolean SETTINGS_MULTIPLY_2=false; // Умножение на 2
//    private boolean SETTINGS_MULTIPLY_3=false; //
//    private boolean SETTINGS_MULTIPLY_4=false; //
//    private boolean SETTINGS_MULTIPLY_5=false; //
//    private boolean SETTINGS_MULTIPLY_6=false; //
//    private boolean SETTINGS_MULTIPLY_7=false; //
//    private boolean SETTINGS_MULTIPLY_8=false; //
//    private boolean SETTINGS_MULTIPLY_9=false; //
//    private boolean SETTINGS_MULTIPLY_10=false;//

//



//    @Override
//    public String toString() {
//        return String.valueOf(this.getValue());
////        return super.toString() ;
//    }
    @Override
    public String toString() {
        return Integer.toString(this.getValue()) ;

    }


    public MyNumberOneTen(int minValue, int maxValue, boolean SETTINGS_MULTIPLYS []) {
//            SETTINGS_MULTIPLY_1, boolean SETTINGS_MULTIPLY_2,
//                          boolean SETTINGS_MULTIPLY_3,boolean SETTINGS_MULTIPLY_4,boolean SETTINGS_MULTIPLY_5,
//                          boolean SETTINGS_MULTIPLY_6,boolean SETTINGS_MULTIPLY_7,boolean SETTINGS_MULTIPLY_8,
//                          boolean SETTINGS_MULTIPLY_9,boolean SETTINGS_MULTIPLY_10) {
         super(minValue, maxValue);

        this.value = this.minValue + (int) (Math.random() * this.maxValue);//0..9, 10..50 = 10-0, 50-10=40
//        this.setValue(new MyNumber(minValue, maxValue).getValue());

        this.SETTINGS_MULTIPLYS= SETTINGS_MULTIPLYS;
        int countBool=0;
        for (boolean b: SETTINGS_MULTIPLYS)
            if(b) countBool++;
        if (countBool==0)
            SETTINGS_MULTIPLYS[1] = true;

        if (!SETTINGS_MULTIPLYS[this.getValue()-1]) {
            MyNumberOneTen myNumberOneTen2 = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
            this.setValue(myNumberOneTen2.getValue());
        }
//            switch (this.getValue()) {
//                // Если сгенерированно число, но она не разрешено, то занова генерируем число
//
//
//                // Можно оптимизировать - убрать конструкцию sweetch ...case
//                // Вместо этого использовать массив с индексом this.getValue()
//                case 1: {
//                    if (!SETTINGS_MULTIPLYS[0]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 2: {
//                    if (!SETTINGS_MULTIPLYS[1]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 3: {
//                    if (!SETTINGS_MULTIPLYS[2]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 4: {
//                    if (!SETTINGS_MULTIPLYS[3]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 5: {
//                    if (!SETTINGS_MULTIPLYS[4]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 6: {
//                    if (!SETTINGS_MULTIPLYS[5]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 7: {
//                    if (!SETTINGS_MULTIPLYS[6]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 8: {
//                    if (!SETTINGS_MULTIPLYS[7]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 9: {
//                    if (!SETTINGS_MULTIPLYS[8]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }
//                case 10: {
//                    if (!SETTINGS_MULTIPLYS[9]) {
//                        MyNumberOneTen myNumberOneTen = new MyNumberOneTen(minValue, maxValue, SETTINGS_MULTIPLYS);
//                        this.setValue (myNumberOneTen.getValue());
//                    }
//                    break;
//                }


        }


}
