package io.gen;

import java.util.ArrayList;
import java.util.List;

public class CountNumberWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        int m = (int)Math.pow(n, 10);
        List<Integer> res = new ArrayList<>();
        getCount(0, res, m);
        return res.size();
    }

    private void getCount(int start, List<Integer> res, int n){

        if (start >= n){
            return;
        }

        for (int i = start; i < n; i++) {
            char[] tmp = (i + "").toCharArray();
            if (!isDuplicate(tmp)){
                res.add(i);
            }
            getCount(i + 1, res, n);
        }
    }

    private boolean isDuplicate(char[] chars){
        int[] map = new int[26];
        for (char ch: chars){
            if (map[ch - '0'] != 0){
                return false;
            }
            map[ch - '0'] = 1;
        }
        return true;
    }
}
