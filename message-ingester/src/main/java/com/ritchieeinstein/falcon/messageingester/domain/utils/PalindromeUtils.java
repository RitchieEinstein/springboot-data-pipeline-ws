package com.ritchieeinstein.falcon.messageingester.domain.utils;

import org.springframework.util.StringUtils;

/**
 * The Palindrome Calculating Util helper. This was deisgned to be a static method But due to test coverage complexity
 * and time contraint, have to settle up with an object approach
 */

public class PalindromeUtils {

    public long getLongestPalindromeLength(String val){
        if(StringUtils.isEmpty(val)) return 0;
        int longestLength=1;
        char[] valArray = val.toUpperCase().toCharArray();
        boolean charFlag = false;
        for (int i = 0; i < val.length(); i++) {
            if(Character.isAlphabetic(val.charAt(i))) charFlag = true;
            int oddSeriesLength = compareNeighbours(valArray, i, i);
            int evenSeriesLength = compareNeighbours(valArray, i, i+1);
            longestLength = Math.max(longestLength, Math.max(oddSeriesLength, evenSeriesLength));
        }
        if(!charFlag && longestLength == 1) return 0;
        return longestLength;
    }

    private int compareNeighbours(char[] arr, int left, int right){
        while(left >= 0 && right < arr.length && (Character.isAlphabetic(arr[left] )|| Character.isAlphabetic(arr[right]))
            && arr[left] == arr[right]){
            left--;
            right++;
        }
        return right - left - 1;
    }

}
