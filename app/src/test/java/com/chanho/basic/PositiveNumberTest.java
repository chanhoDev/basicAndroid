package com.chanho.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class PositiveNumberTest {
    PositiveNumber SUT;

    @Before
    public void setup(){
        SUT = new PositiveNumber();
    }

    @Test
    public void test1(){
       boolean result =  SUT.isPositive(-1);
        assertThat(result, is(false));
    }

    @Test
    public void test2(){
        boolean result =  SUT.isPositive(0);
        assertThat(result, is(false));
    }

    @Test
    public void test3(){
        boolean result =  SUT.isPositive(1);
        assertThat(result, is(true));
    }
}