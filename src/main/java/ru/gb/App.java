package ru.gb;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        final int size = 10000000;
         final int half = size / 2;
        float[] arr = new float[size];

        method1(arr);
    }

    public static void method1(float[] arr) {
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            System.currentTimeMillis();
        }
        long result = System.currentTimeMillis() - a;
        System.out.printf("Однопоточное вычисление. Результат: %s", result);
    }
}
