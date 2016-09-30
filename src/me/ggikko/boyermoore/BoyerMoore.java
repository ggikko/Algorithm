package me.ggikko.boyermoore;

/**
 * Created by ggikko on 2016. 9. 30..
 */

/**
 * 2가지 방법이 있음
 * Bad character & Suffix
 */
public class BoyerMoore {

    private final int R;
    private int[] right;
    private char[] pattern;

    /**
     * 초기화 및 생성
     * @param pattern
     */
    public BoyerMoore(char[] pattern) {
        this.R = 256;
        this.pattern = new char[pattern.length];

        for (int j = 0; j < pattern.length; j++) this.pattern[j] = pattern[j];

        right = new int[R];

        for (int c = 0; c < R; c++) right[c] = -1;
        for (int j = 0; j < pattern.length; j++) right[pattern[j]] = j;
    }

    /**
     * offset 구하기
     * @param text
     * @return
     */
    public int getOffSet(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return n;
    }

    /**
     * search
     * offset을 이용하여 구별
     * @param text
     * @return
     */
    public boolean search(char[] text){
        int offSet = getOffSet(text);
        if(offSet == text.length) return false;
        return true;
    }

    /**
     * main함수
     * TODO : test case
     * @param args
     */
    public static void main(String[] args) {
        String pat = "ggikko";
        String txt = "hello, ggikko! What is ur name?";

        char[] pattern = pat.toCharArray();
        char[] text    = txt.toCharArray();

        BoyerMoore boyermoore = new BoyerMoore(pattern);
        boolean search = boyermoore.search(text);

        System.out.println("result : " + search);
    }
}
