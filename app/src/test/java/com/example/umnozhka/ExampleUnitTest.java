package com.example.umnozhka;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }





    @Test
    public void MyNumber_constructor_isCorrect(int minValue,int maxValue){
//        int minValue,maxValue;
        MyNumber myNumber = new MyNumber(minValue,maxValue);
        myNumber.getRandomValue();
        assertThat(myNumber.getValue()). ;
    }

    @Test
    public void MyNumber_2(){
        assertTrue(myNumber.);
    }

    @Test
    public void MyNumber_All_constructor_isCorrect(){
//        assertEquals(4, 2 + 2);

//                ("N 1, min = 0, max = 100"+MyNumber_constructor_isCorrect(0,100));
//        System.out.println("N 2, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 3, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 4, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 5, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 6, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 7, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 8, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 9, min = 1, max = 100"+MyNumber_constructor_isCorrect(1,100));
//        System.out.println("N 10, min = 1, max = 0"+MyNumber_constructor_isCorrect(1,0));
//        System.out.println("N 11, min = 2, max = 2"+MyNumber_constructor_isCorrect(2,2));
//        System.out.println("N 12, min = 1, max = 1"+MyNumber_constructor_isCorrect(1,1));
//        System.out.println("N 13, min = 50, max = 1"+MyNumber_constructor_isCorrect(50,1));
//        System.out.println("N 14, min = 1, max = 100000"+MyNumber_constructor_isCorrect(1,100000));
//        System.out.println("N 15, min = 100000, max = 1000000"+MyNumber_constructor_isCorrect(100000,1000000));
//        System.out.println("N 16, min = 100, max = 100"+MyNumber_constructor_isCorrect(100,100));
//        System.out.println("N 17, min = 1, max = 10"+MyNumber_constructor_isCorrect(1,10));
//        System.out.println("N 18, min = 1, max = 20"+MyNumber_constructor_isCorrect(1,20));
//        System.out.println("N 19, min = 1, max = 30"+MyNumber_constructor_isCorrect(1,30));

    }
}