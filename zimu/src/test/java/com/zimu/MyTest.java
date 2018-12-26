package com.zimu;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {

    private static final String COLUMNS_REGEX = "^columns\\[\\d+\\]\\[data\\]$";

    public static void main(String[] args) {
        Pattern cp = Pattern.compile(COLUMNS_REGEX);
        Matcher cm = cp.matcher("columns[002][data]");
        System.out.println(cm.matches());

    }
}
