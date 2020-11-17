package com.example.umnozhka;

import com.firstSet.MultiplayIt.MyAct;
import com.firstSet.MultiplayIt.MyNumber;
import com.firstSet.MultiplayIt.MyNumberAddSub;
import com.firstSet.MultiplayIt.MyNumberOneTen;
import com.firstSet.MultiplayIt.MyTask;

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
    public void MyTask_false_false_false_false_All_True(){
        //все ошибочные
        boolean [] myBoolArray = { true,true,true,true,true,true,true,true,true,true};
        //ошибочные
        MyTask currentTask1 = new MyTask(0,0, new MyAct(false,false,false,false),myBoolArray);
        System.out.println("MyTask_false_false_false_false_All_True = "+currentTask1.getCurrentAct().getMyAct()+","+ " CurrentOneUnit = " + currentTask1.getCurrentOneUnit() + " CurrentTwoUnit = "+ currentTask1.getCurrentTwoUnit());

        MyTask currentTask2 = new MyTask(0,0, new MyAct(true,false,false,false),myBoolArray);
        System.out.println("test_MyTask_All_True CurrentAct = "+currentTask1.getCurrentAct().getMyAct()+","+ " CurrentOneUnit = " + currentTask1.getCurrentOneUnit() + " CurrentTwoUnit = "+ currentTask1.getCurrentTwoUnit());
        MyTask currentTask3 = new MyTask(1,100, new MyAct(true,false,false,false),myBoolArray);
        System.out.println("test_MyTask_All_True CurrentAct = "+currentTask1.getCurrentAct().getMyAct()+","+ " CurrentOneUnit = " + currentTask1.getCurrentOneUnit() + " CurrentTwoUnit = "+ currentTask1.getCurrentTwoUnit());
        MyTask currentTask4 = new MyTask(1,100, new MyAct(true,false,false,false),myBoolArray);
        System.out.println("test_MyTask_All_True CurrentAct = "+currentTask1.getCurrentAct().getMyAct()+","+ " CurrentOneUnit = " + currentTask1.getCurrentOneUnit() + " CurrentTwoUnit = "+ currentTask1.getCurrentTwoUnit());

        MyTask currentTask5 = new MyTask(1,100, new MyAct(true,false,false,false),myBoolArray);
        System.out.println("test_MyTask_All_True CurrentAct = "+currentTask1.getCurrentAct().getMyAct()+","+ " CurrentOneUnit = " + currentTask1.getCurrentOneUnit() + " CurrentTwoUnit = "+ currentTask1.getCurrentTwoUnit());

        MyTask currentTask6 = new MyTask(1,100, new MyAct(true,false,false,false),myBoolArray);

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
        if (minValue<1) minValue=1;
        if (maxValue<1) maxValue=1;

        MyNumber myNumber1 = new MyNumberAddSub(minValue,maxValue);
        System.out.println(myNumber1);
        assertThat(myNumber1.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber1.getValue()).isLessThan(maxValue+1) ;
        MyNumber myNumber2 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber2.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber2.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber2);
        MyNumber myNumber3 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber3.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber3.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber3);
        MyNumber myNumber4 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber4.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber4.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber4);
        MyNumber myNumber5 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber5.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber5.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber5);
        MyNumber myNumber6 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber6.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber6.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber6);
        MyNumber myNumber7 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber7.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber7.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber7);
        MyNumber myNumber8 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber8.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber8.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber8);
        MyNumber myNumber9 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber9.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber9.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber9);
        MyNumber myNumber10 = new MyNumberAddSub(minValue,maxValue);
        assertThat(myNumber10.getValue()).isGreaterThan(minValue-1) ;
        assertThat(myNumber10.getValue()).isLessThan(maxValue+1) ;
        System.out.println(myNumber10);

    }
    @Test
    public void MyNumberOneten_All_True_10tests (  ) {
        boolean [] myBoolArray = { true,true,true,true,true,true,true,true,true,true};
        System.out.println("(All_True) 1 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 2 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 3 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 4 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 5 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 6 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 7 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 8 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 9 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_True) 10 test:" + new MyNumberOneTen(1,10,myBoolArray));

    }
    @Test
    public void MyNumberOneten_All_False_10tests (  ) {
        boolean [] myBoolArray = { false,false,false,false,false,false,false,false,false,false};
        System.out.println("(All_False) 1 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 2 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 3 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 4 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 5 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 6 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 7 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 8 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 9 test:" + new MyNumberOneTen(1,10,myBoolArray));
        System.out.println("(All_False) 10 test:" + new MyNumberOneTen(1,10,myBoolArray));
    }
    @Test
    public void MyNumberOneten_7_True_10tests (  ) {
        boolean[] myBoolArray31 = {false, false, false, false, false, false, true, false, false, false};
        System.out.println("(7) 1 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 2 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 3 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 4 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 5 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 6 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 7 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 8 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 9 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
        System.out.println("(7) 10 test:" + new MyNumberOneTen(1, 10, myBoolArray31));
    }
    @Test
    public void MyNumberOneten_2_3_10_True_10tests (  ) {
        boolean [] myBoolArray3 = { false,true,true,false,false,false,false,false,false,true};
        System.out.println("(2,3,10) 1 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 2 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 3 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 4 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 5 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 6 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 7 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 8 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 9 test:"+new MyNumberOneTen(1,10,myBoolArray3));
        System.out.println("(2,3,10) 10 test:"+new MyNumberOneTen(1,10,myBoolArray3));

    }
    @Test
    public void MyNumberOneten_1_2_9_10_True_10tests (  ) {
        boolean [] myBoolArray4= { true,true,false,false,false,false,false,false,true,true};
        System.out.println("(1,2,9,,10) 1 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 2 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 3 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 4 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 5 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 6 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 7 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 8 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 9 test:"+new MyNumberOneTen(1,10,myBoolArray4));
        System.out.println("(1,2,9,,10) 10 test:"+new MyNumberOneTen(1,10,myBoolArray4));
    }
        @Test
    public void MyNumberOneten_10_test (  ) {
//        сгенерируем 10 объектов
        MyNumberOneten_All_True_10tests ();
        MyNumberOneten_All_False_10tests();
        MyNumberOneten_7_True_10tests();
        MyNumberOneten_2_3_10_True_10tests();
        MyNumberOneten_1_2_9_10_True_10tests();

    }
    @Test
    public void Act_10_test (  ) {
//        сгенерируем 10 объектов
        MyNumberOneten_All_True_10tests ();
        MyNumberOneten_All_False_10tests();
        MyNumberOneten_7_True_10tests();
        MyNumberOneten_2_3_10_True_10tests();
        MyNumberOneten_1_2_9_10_True_10tests();

    }




}