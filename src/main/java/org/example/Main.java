package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String text = "Suma (liczba produktów: 2):";
        Matcher matcher = Pattern.compile("\\d+").matcher(text);
        //     matcher.find();
        //       BigDecimal totalAmount = new BigDecimal(matcher.group().replace(",", "."));
        System.out.println(matcher.group());
    }

    private static void sayHello(String name, String surname) {
        System.out.println(String.format("Hello %s %s", name, surname));
    }
}

//Wyrażenia regularne