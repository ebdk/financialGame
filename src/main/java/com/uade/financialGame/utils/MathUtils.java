package com.uade.financialGame.utils;

import java.util.Random;

public class MathUtils {

    public static Integer generateRandomNumber(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

    public static Integer getPercentage(Integer value, Integer percentage) {
        return (int)(value*(percentage/100.0f)); //10% of the salary;
    }

}
