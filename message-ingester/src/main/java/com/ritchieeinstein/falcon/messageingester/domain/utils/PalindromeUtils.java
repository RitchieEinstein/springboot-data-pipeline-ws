package com.ritchieeinstein.falcon.messageingester.domain.utils;

import org.springframework.util.StringUtils;

public class PalindromeUtils {

    public long getLongestPalindromeLength(String val){
        if(StringUtils.isEmpty(val)) return 0;
        int longestLength=1;
        char[] valArray = val.toUpperCase().toCharArray();
        for (int i = 0; i < val.length(); i++) {
            int oddSeriesLength = compareNeighbours(valArray, i, i);
            int evenSeriesLength = compareNeighbours(valArray, i, i+1);
            longestLength = Math.max(longestLength, Math.max(oddSeriesLength, evenSeriesLength));
        }
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
