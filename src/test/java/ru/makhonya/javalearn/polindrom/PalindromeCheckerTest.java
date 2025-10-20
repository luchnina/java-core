package ru.makhonya.javalearn.polindrom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class PalindromeCheckerTest {

    @Test
    public void isPalindrome_whenNull_thenFalse() {
        boolean result = PalindromeChecker.isPalindrome(null);
        Assertions.assertFalse(result);
    }

    @Test
    public void isPalindrome_whenEmptyString_thenTrue() {
        boolean result = PalindromeChecker.isPalindrome("*?&$");
        Assertions.assertTrue(result);
    }

    @Test
    public void isPalindrome_whenSymbol_thenTrue() {
        boolean result = PalindromeChecker.isPalindrome("!:(())1");
        Assertions.assertTrue(result);
    }

    @Test
    public void isPalindrome_whenPalindrome_thenTrue() {
        String[] polindromes = {"!!шалаш&", "казак", "комок!", "1:1", "121", "10.02.2001", "21радар12",
                "А роза упала на лапу Азора", "Молебен о коне белом", "Я иду с мечем судия"};

        for (String polindrome : polindromes) {
            boolean result = PalindromeChecker.isPalindrome(polindrome);
            Assertions.assertTrue(result, polindrome);
        }
    }

    @Test
    public void isPalindrome_whenNotPalindrome_thenFalse() {
        String[] notPolindromes = {"камыш", "программа", "12", "123", "21радар21"};

        for (String notPolindrome : notPolindromes) {
            boolean result = PalindromeChecker.isPalindrome(notPolindrome);
            Assertions.assertFalse(result, notPolindrome);
        }
    }
}