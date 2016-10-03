package me.ggikko.datastructure;

/**
 * Created by admin on 2016. 10. 3..
 */
public class HashTable {

    int[] arr;
    int capacity;

    /**
     * 생성자
     **/
    public HashTable(int capacity) {
        this.capacity = nextPrime(capacity);
        arr = new int[this.capacity];
    }

    /**
     * insert
     **/
    public void insert(int ele) {
        arr[ele % capacity] = ele;
    }

    /**
     * clear
     **/
    public void clear() {
        arr = new int[capacity];
    }

    /**
     * contains
     **/
    public boolean contains(int ele) {
        return arr[ele % capacity] == ele;
    }

    /**
     * delete
     **/
    public void delete(int ele) {
        if (arr[ele % capacity] == ele)
            arr[ele % capacity] = 0;
        else
            System.out.println("\nError : Element not found\n");
    }

    /**
     * generate next prime number >= n
     **/
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        for (; !isPrime(n); n += 2) ;

        return n;
    }

    /**
     * check if given number is prime
     **/
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    /**
     * print hash table
     **/
    public void printTable() {
        System.out.print("\nHash Table = ");
        for (int i = 0; i < capacity; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10); // instance 생성
        hashTable.insert(4);

        System.out.printf("has 4 ? : " + hashTable.contains(4) + "\n");
        System.out.printf("has 3 ? : " + hashTable.contains(3) + "\n");
    }
}
