package utils;

import java.util.Random;

public class RandomValueGenerator {

    public static char getRandomChar() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'A');
    }
}