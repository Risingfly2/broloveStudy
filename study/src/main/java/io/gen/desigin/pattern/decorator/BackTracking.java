package io.gen.desigin.pattern.decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BackTracking {


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        permute(res, new ArrayList<>(), nums);
        return res;
    }

    private void permute(List<List<Integer>> res, List<Integer> tmp, int[] nums){
       if (tmp.size() == nums.length){
           res.add(new ArrayList<>(tmp));
       }
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])){
                continue;
            }
            tmp.add(nums[i]);
            permute(res, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permute(res, tmp, nums, used);
        return res;
    }

    private void permute(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] used){
        if (tmp.size() == nums.length){
            res.add(new ArrayList<Integer>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            used[i] = true;
            tmp.add(nums[i]);
            permute(res, tmp, nums, used);
            used[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }


    public List<List<Integer>> backTrack(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        track(res, visited, new ArrayList<>(), nums);
        return res;
    }

    public void track(List<List<Integer>> res, boolean[] visited, List<Integer> tmp, int[] nums){
        if (tmp.size() == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]){
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]){
                continue;
            }
            visited[i] = true;
            tmp.add(nums[i]);
            track(res, visited, tmp, nums);
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }



//----------------------------------------combination-------------------------------------------------------------------------------

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combination(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void combination(List<List<Integer>> res, List<Integer> tmp, int[] candidates, int remain, int start){
        if (remain < 0){
            return;
        }else if (remain == 0){
            res.add(new ArrayList<>(remain));
        }else {
            for (int i = start; i < candidates.length; i++) {
                tmp.add(candidates[i]);
                combination(res, tmp, candidates, remain - candidates[i], i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }


    public List<List<Integer>> combinationSumII(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combination(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }


    private void combinationII(List<List<Integer>> res, List<Integer> tmp, int[] candidates, int remain, int start){
        if (remain < 0){
            return;
        }else if (remain == 0){
            res.add(new ArrayList<>(tmp));
        }else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]){
                    continue;
                }
                tmp.add(candidates[i]);
                combinationII(res, tmp, candidates, remain - candidates[i], i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }


    //----------------------------------------subset-------------------------------------------------------------------------------


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsets(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void subsets(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start){

        res.add(new ArrayList<>(tmp));
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            subsets(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }

    }



    public List<List<Integer>> subsetsII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subsetsII(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void subsetsII(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start){

        res.add(new ArrayList<>(tmp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]){
                continue;
            }
            tmp.add(nums[i]);
            subsets(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }

    }


    //-----------------------------------------------------------------------------------------------------------------------

    private static final String[] KEYS = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

//    public List<String> letterCombinations(String digits){
//        List<String> res = new ArrayList<>();
//        if (digits == null || "".equals(digits)){
//            return res;
//        }
//        combination("", digits, 0, res);
//        return res;
//    }
//
//    private void combination(String prefix, String digits, int offset, List<String> res){
//        if (offset == digits.length()){
//            res.add(prefix);
//            return;
//        }
//        String letters = KEYS[digits.charAt(offset) - '0'];
//        for (int i = 0; i < letters.length(); i++) {
//            combination(prefix + letters.charAt(i), digits, offset + 1, res);
//        }
//    }



    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        doCombination(new StringBuilder(), combinations, digits);
        return combinations;
    }

    private void doCombination(StringBuilder prefix, List<String> combinations, final String digits) {
        if (prefix.length() == digits.length()) {
            combinations.add(prefix.toString());
            return;
        }
        int curDigits = digits.charAt(prefix.length()) - '0';
        String letters = KEYS[curDigits];
        for (char c : letters.toCharArray()) {
            prefix.append(c);                         // 添加
            doCombination(prefix, combinations, digits);
            prefix.deleteCharAt(prefix.length() - 1); // 删除
        }
    }



    public List<String> restoreIpAddresses(String s){
        List<String> res = new ArrayList<>();
        backtracking(res, "", s, 0);
        return res;
    }

    private void backtracking(List<String> res, String path, String s, int k){
            if (s.isEmpty() && k == 4){
                res.add(path.substring(1));
            }

        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); i++) {
            String tmp = s.substring(0, i);
            if (Integer.parseInt(tmp) <= 255){
                backtracking(res, path + "." + tmp, s.substring(i), k + 1);
            }
        }
    }


    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0){
            return false;
        }
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isExit(board, i, j, word.toCharArray(), 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isExit(char[][] board, int i, int j, char[] word, int l){
        if (word.length == l){
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word[l]){
            return false;
        }

        board[i][j] = '*';
        boolean res = isExit(board,i, j + 1, word, l + 1) || isExit(board,i, j - 1, word, l + 1)
                || isExit(board,i + 1, j, word, l + 1) || isExit(board,i - 1, j, word, l + 1);
        board[i][j] = word[l];
        return res;

    }


    public List<List<Integer>> combinationSum3(int k, int n){
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3(res, new ArrayList<>(), 1, k, n);
        return res;
    }

    public void combinationSum3(List<List<Integer>> res, List<Integer> tmp, int start, int k, int remain){
        if (tmp.size() == k){
            if (remain == 0){
                res.add(new ArrayList<>(tmp));
            }
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (tmp.size() > k - 1 && remain - i > 9){
                return;
            }
            tmp.add(i);
            combinationSum3(res, tmp, i + 1, k, remain - i);
            tmp.remove(tmp.size() - 1);
        }
    }


    private void combinationSum(List<List<Integer>> res, List<Integer> tmp, int k, int remain, int start){
        if (remain == 0 && tmp.size() == k){
            res.add(new ArrayList<Integer>(tmp));
            return;
        }

        if  (tmp.size() == k){
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (tmp.size() > k - 1 && remain - i > 9){
                return;
            }
            tmp.add(i);
            combinationSum(res, tmp, k, remain - i, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }


    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partitionTrack(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void partitionTrack(List<List<String>> res, List<String> tmp, String s,int start){
        if (start >= s.length()){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i < s.length(); i++) {

            if (isPalindrome(s, start, i)){
                tmp.add(s.substring(start, i + 1));
                partitionTrack(res, tmp, s, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end){
        char[] chars = s.toCharArray();
        while (start < end){
            if (chars[start] != chars[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
