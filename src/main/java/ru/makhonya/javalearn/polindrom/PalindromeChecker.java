package ru.makhonya.javalearn.polindrom;

public class PalindromeChecker {

    public static void main(String[] args) {

    }

    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        str = str.toLowerCase().replaceAll("[^a-zA-Zа-яА-ЯёЁ0-9]", "");

        if (str.length() < 2) {
            return true;
        }

        for (int i = 0; i <= (str.length() - 1) / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
