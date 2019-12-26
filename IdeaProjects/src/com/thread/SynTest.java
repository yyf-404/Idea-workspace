package com.thread;

public class SynTest {
    public static void main(String[] args) {
        int var=2;
        int arr[] = null;
        synchronized (int.class) {

        }
//        synchronized (var) {//编译错误
//
//        }
        synchronized (arr) {

        }
    }
}
