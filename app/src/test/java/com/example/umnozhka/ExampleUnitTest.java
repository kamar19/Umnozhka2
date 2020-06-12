package com.example.umnozhka;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    int minValue, maxValue;
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }


    @Test
    public void MyTask_tttt(){
        //все ошибочные
        MyTask currentTask1 = new MyTask(0,0, new MyAct(false,false,false,false));
        //ошибочные
        System.out.println("test_MyTask_tttt (0,0, new MyAct(f,f,f,f)): CurrentAct = "+currentTask1.getCurrentAct().getMyAct()+","+ " CurrentOneUnit = " + currentTask1.getCurrentOneUnit() + " CurrentTwoUnit = "+ currentTask1.getCurrentTwoUnit());
        MyTask currentTask2 = new MyTask(0,0, new MyAct(true,false,false,false));



        MyTask currentTask3 = new MyTask(1,100, new MyAct(true,false,false,false));
        MyTask currentTask4 = new MyTask(1,100, new MyAct(true,false,false,false));
        MyTask currentTask5 = new MyTask(1,100, new MyAct(true,false,false,false));
        MyTask currentTask6 = new MyTask(1,100, new MyAct(true,false,false,false));

    }

    @Test
    public void MyNumber_constructor () {
//        сгенерируем 10 объектов
        minValue=1;
        maxValue=10;
        MyNumber_constructor_10_test();
        MyNumber_constructor_10_test();
        minValue=1;
        maxValue=100;
        MyNumber_constructor_10_test();
        MyNumber_constructor_10_test();

    }


    @Test
    public void MyNumber_constructor_10_test (  ){
//        сгенерируем 10 объектов
        MyNumber myNumber1 = new MyNumber(minValue,maxValue);
        System.out.println(myNumber1);
        assertThat(myNumber1.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber1.getValue()).isLessThan(maxValue+1) ;
        MyNumber myNumber2 = new MyNumber(minValue,maxValue);
        assertThat(myNumber2.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber2.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber2);
        MyNumber myNumber3 = new MyNumber(minValue,maxValue);
        assertThat(myNumber3.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber3.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber3);
        MyNumber myNumber4 = new MyNumber(minValue,maxValue);
        assertThat(myNumber4.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber4.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber4);
        MyNumber myNumber5 = new MyNumber(minValue,maxValue);
        assertThat(myNumber5.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber5.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber5);
        MyNumber myNumber6 = new MyNumber(minValue,maxValue);
        assertThat(myNumber6.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber6.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber6);
        MyNumber myNumber7 = new MyNumber(minValue,maxValue);
        assertThat(myNumber7.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber7.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber7);
        MyNumber myNumber8 = new MyNumber(minValue,maxValue);
        assertThat(myNumber8.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber8.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber8);
        MyNumber myNumber9 = new MyNumber(minValue,maxValue);
        assertThat(myNumber9.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber9.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber9);
        MyNumber myNumber10 = new MyNumber(minValue,maxValue);
        assertThat(myNumber10.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber10.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber10);

    }



}