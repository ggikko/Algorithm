package me.ggikko.boyermoore;

import java.util.Random;

/**
 * Created by ggikko on 2016. 10. 2..
 */
public class RadixSort {

    /**
     * sort
     * @param a
     */
    public void sort(int[] a) {

        int i, m = a[0], exp = 1, n = a.length; // 초기 값

        int[] b = new int[n]; // 큐 생성

        for (i = 1; i < n; i++) if (a[i] > m) m = a[i]; // m을 첫번 째 값 가장 큰 값으로 설정, 이유는 while 문을 돌릴 때 기준이 되기 떄문

        //일의자리 -> 십의자리 -> 백의자리 -> ... 차례대로 담았다가 뺏다가 반복
        while (m / exp > 0) {
            int[] bucket = new int[10]; // bucket 생성
            for (i = 0; i < n; i++) bucket[(a[i] / exp) % 10]++; // 10으로 나눈 나머지에 따라 bucket 저장
            for (i = 1; i < 10; i++) bucket[i] += bucket[i - 1]; // bucket index 숫자에 따라 증가
            for (i = n - 1; i >= 0; i--) b[--bucket[(a[i] / exp) % 10]] = a[i]; // bucket index 숫자에 따라 감소 & 값 입력
            for (i = 0; i < n; i++) a[i] = b[i]; // 복사
            exp *= 10; // 자리수 올림
        }
    }

    public static void main(String[] args) {

        RadixSort rS = new RadixSort(); // instance 생성
        int[] array = new int[7]; // test array 생성
        Random random = new Random(); // random class instant 생성
        for(int i=0; i<array.length; i++) array[i] = random.nextInt(2000); // random 숫자 생성

        for (int i = 0; i < array.length; i++) System.out.print(array[i]+" "); // print array before sorting

        rS.sort(array); // sorting

        System.out.printf("\n\n"); // <br/>

        for (int i = 0; i < array.length; i++) System.out.print(array[i]+" "); // print array after sorting

    }
}
