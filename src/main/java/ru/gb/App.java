package ru.gb;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];

        method1(arr);
        System.out.println(" \n");
        method2(arr, h, size);
    }

    public static void method1(float[] arr) {
        Arrays.fill(arr, 1);
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long result = System.currentTimeMillis() - a1;
        System.out.printf("Однопоточное вычисление. Результат: %s миллисекунд.", result);
    }

    public static class MyThread extends Thread {

        private float[] a;
        private int iLen;

        MyThread(float[] a, int iLen) {
            this.a = a;
            this.iLen = iLen;
        }

        @Override
        public void run() {
            for (int i = 0; i < iLen; i++) {
                a[i] = (float)(a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    public static void method2(float[] arr, int h, int size) {

        Arrays.fill(arr,1);

        long a2 = System.currentTimeMillis();

        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        MyThread myThread1 = new MyThread(arr1, h);
        MyThread myThread2 = new MyThread(arr2, h);

        myThread1.setDaemon(true);
        myThread2.setDaemon(true);

        myThread1.start();
        myThread2.start();

        try {
            myThread1.join();
            myThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.currentTimeMillis();
        long result = System.currentTimeMillis() - a2;
        System.out.printf("Многопоточное вычисление. Результат: %s миллисекунд.\n",result);

    }

}
