package com.uade.financialGame.utils;

import java.util.Random;

public class MathUtils {

    public static Integer generateRandomNumber(Integer min, Integer max){

        Random r = new Random();

        return r.nextInt(max-min) + min;
    }

}
