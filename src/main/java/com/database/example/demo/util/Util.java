package com.database.example.demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Util {
    private static final List<Character> randomChars = new ArrayList<>();

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            randomChars.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            randomChars.add(c);
        }
    }

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private Util() {
    }

    public static String randomString(int length) {
        Random random = new Random();
        String randStr = "";
        for (int i = 0; i < length; i++) {
            randStr += randomChars.get(random.nextInt(randomChars.size()));
        }
        return randStr;
    }
}
