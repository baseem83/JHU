package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello");
        char[] a = new char[5];
        "abcdef".getChars(0, 4, a, 0);
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        sb.append("world");
        "abcdef".split("");
        System.out.println(sb);
    }
}
