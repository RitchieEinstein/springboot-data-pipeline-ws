package com.ritchieeinstein.falcon.messageingester.domain.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeUtilsTest {

    private PalindromeUtils util;

    @BeforeEach
    void setup(){
        util = new PalindromeUtils();
    }

    @Test
    void getLongestPalindromeLength_withNullValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength(null)).isEqualTo(0);
    }

    @Test
    void getLongestPalindromeLength_withEmptyValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength("")).isEqualTo(0);
    }

    @Test
    void getLongestPalindromeLength_withNonPalindromeString() {
        Assertions.assertThat(util.getLongestPalindromeLength("LONGLIVETHEKING")).isEqualTo(1);
    }

    @Test
    void getLongestPalindromeLength_withFullStringIsPalindrome() {
        Assertions.assertThat(util.getLongestPalindromeLength("malayalam")).isEqualTo(9);
    }

    @Test
    void getLongestPalindromeLength_withSubStringPalindromeEvenCount() {
        Assertions.assertThat(util.getLongestPalindromeLength("ritchieeinstein")).isEqualTo(4);
    }

    @Test
    void getLongestPalindromeLength_withEmptyValuePassedOddCount() {
        Assertions.assertThat(util.getLongestPalindromeLength("ritchieIeinstein")).isEqualTo(5);
    }

    @Test
    void getLongestPalindromeLength_withMultiplePalindromesInString() {
        Assertions.assertThat(util.getLongestPalindromeLength("AmmaAndAappppAaOrAKakA")).isEqualTo(8);
    }

    @Test
    void getLongestPalindromeLength_withNumberValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength("123321")).isEqualTo(0);
    }

    @Test
    void getLongestPalindromeLength_withNumberAndCharacterValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength("123RR321")).isEqualTo(2);
    }

    @Test
    void getLongestPalindromeLength_withSymbolsValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength("#--#")).isEqualTo(0);
    }

    @Test
    void getLongestPalindromeLength_withSymbolsAndCharacterValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength("%%%%%%%%madam#########")).isEqualTo(5);
    }

    @Test
    void getLongestPalindromeLength_withSymbolsNumberAndCharValuePassed() {
        Assertions.assertThat(util.getLongestPalindromeLength("&*123japan321*&##########000000000000000000")).isEqualTo(3);
    }
}