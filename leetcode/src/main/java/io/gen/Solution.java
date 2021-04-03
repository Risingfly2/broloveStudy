package io.gen;

import java.util.*;

public class Solution {
    //abcabcd
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3){
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i== 0 || (i > 0 && nums[i] != nums[i -1])){
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];//per position
                while (lo < hi){
                    if (nums[lo] + nums[hi] == sum){
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }else if (nums[lo] + nums[hi] < sum){
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        lo++;
                    }else {
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        hi--;
                    }
                }
            }
        }
        return res;

    }


    public int threeSumClosest(int[] nums, int target) {

        int res = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end){
                int sum = nums[start] + nums[end] + nums[i];
                if (sum > target){
                    end--;
                }else if (sum < target){
                    start++;
                }else {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)){
                    res = sum;
                }
            }
        }
        return res;
    }

    int len = 0;
    public List<List<Integer>> kSum(int[] nums, int target){
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4 , 0);
    }
    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (index >= len){
            return res;
        }
        if (k == 2){
            int i = index, j = len - 1;
            while (i < j){
                if (target - nums[i] == nums[j]){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(target - nums[i]);
                    res.add(tmp);
                    while (i < j && nums[i] == nums[i + 1]) i++;
                    while (i < j && nums[j] == nums[j - 1]) j--;
                    i++;
                    j--;
                }else if (target - nums[i] > nums[j]){
                    i++;
                }else {
                    j--;
                }
            }
        }else {
            for (int i = index; i < len - k + 1; i++) {
                ArrayList<List<Integer>> tmp = kSum(nums, target - nums[i], k - 1, i + 1);
                if (tmp != null){
                    for (List<Integer> t: tmp){
                        t.add(0, nums[i]);
                    }
                    res.addAll(tmp);
                }
                while (i < len - 1 && nums[i] == nums[i + 1]){
                    i++;
                }
            }
        }
        return res;
    }

    public void sortColors(int[] nums) {

        int low = 0, high = nums.length - 1;
        for (int i = low; i <= high; ) {
            if (nums[i] == 0){
                swap(nums, low, i);
                low++;
                i++;
            }else if (nums[i] == 2){
                swap(nums, high, i);
                high--;
            }else {
                i++;
            }
        }
    }
    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    public int removeDuplicates(int[] nums) {

        int i = 0;
        for (int n: nums){
            if (i < 2 || n > nums[i - 2]){
                nums[i++] = n;
            }
        }
        return i;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0 && j >= 0; k--) {
            nums1[k] = (i < 0 || nums1[i] < nums2[j]) ? nums2[j--] : nums1[i--];
        }
    }

    public boolean isPalindrome(String s) {

        if (s == null || s.isEmpty()){
            return true;
        }
        int left = 0, right = s.length() - 1;
        while (left < right){
            if (!Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }else if (!Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }else {
                if (!(Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)))){
                    return false;
                }else {
                    left++;
                    right--;
                }
            }
        }
        return true;
    }

    public int[] twoSum(int[] numbers, int target) {

//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < numbers.length; i++) {
//            map.put(numbers[i], i);
//        }
        int left = 0, right = numbers.length - 1;
        while (left < right){
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                return new int[]{left + 1, right + 1};
            }else if (sum < target){
                left++;
            }else {
                right --;
            }
        }
        return numbers;
    }

    public int minSubArrayLen(int target, int[] nums) {

        if (nums == null || nums.length == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0, sum = 0;
        while (j < nums.length){
            sum += nums[j++];
            while (sum >= target){
                min = Math.min(min, j - i);
                sum -= nums[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public void moveZeroes(int[] nums) {

        if (nums == null || nums.length == 0){
            return;
        }
        int pos = 0;
        for (int num: nums){
            if (num != 0){
                nums[pos++] = num;
            }
        }
        while (pos < nums.length){
            nums[pos++] = 0;
        }
    }

    public int findDuplicate(int[] nums) {

        if (nums == null || nums.length == 0){
            return -1;
        }
        int[] map = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            if (map[i] != 0){
                return nums[i];
            }else {
                map[nums[i]] = i;
            }
        }
        return -1;
    }

    public void reverseString(char[] s) {

        if (s == null || s.length == 0){
            return;
        }

        int left = 0, right = s.length - 1;
        while (left < right){

            swapchar(s, left, right);
            left++;
            right--;
        }
    }
    public void swapchar(char[] chars, int i, int j){
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }


    public String reverseVowels(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right){
            while (left < right && !isVowels(chars[left])) left++;
            while (left < right && !isVowels(chars[right])) right--;
            swapchar(chars, left, right);
            left++;
            right--;
        }
        return new String(chars);
    }

    public boolean isVowels(char c){
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
            return true;
        }
        return false;
    }

    public int[] intersection(int[] nums1, int[] nums2) {

        int i = 0, j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]){
                i++;
            }else if (nums1[i] > nums2[j]){
                j++;
            }else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] res = new int[list.size()];
        int k = 0;
        for (Integer num: list){
            res[k++] = num;
        }
        return res;
    }

    public int characterReplacement(String s, int k){
        int[] arr = new int[26]; //count character nums
        int left = 0, maxCount = 0, maxSize = 0;
        for (int right = 0; right < s.length(); right++) {
            maxCount = Math.max(maxCount, ++arr[s.charAt(right) - 'A']);
            if (right - left + 1 - maxCount > k){
                arr[s.charAt(left)- 'A']--;
                left++;
            }
            maxSize = Math.max(maxSize, right - left + 1);
        }
        return maxSize;
    }

    public String findLongestWord(String s, List<String> dictionary) {

        String res = "";
        for (String w : dictionary){
            if (isSubString(s, w)){
                if (w.length() > res.length()) res = w;
                if (w.length() == res.length() && (w.compareTo(res) < 0)) res = w;
            }
        }
        return res;
    }

    private boolean isSubString(String s, String w){

        int i = 0, j = 0;
        while (i < s.length() && j < w.length()){
            if (s.charAt(i) == w.charAt(j)){
                j++;
            }
            i++;
        }
        return j == w.length();
    }


    public int search(int[] nums, int target) {

        int midIndex = findMid(nums);
        if (target == nums[midIndex]) return midIndex;
        int left = (target <= nums[nums.length - 1] ? midIndex : 0);
        int right = (target > nums[nums.length - 1] ? midIndex : nums.length - 1);

        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;

    }

    private int findMid(int[] nums){
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }


    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];
        res[0] = findFirst(nums, target);
        res[1] = findLast(nums, target);
        return res;
    }

    private int findFirst(int[] nums, int target){
        int idx = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] >= target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
            if (nums[mid] == target) idx = mid;
        }
        return idx;
    }

    private int findLast(int[] nums, int target){
        int idx = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] <= target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
            if (nums[mid] == target) idx = mid;
        }
        return idx;
    }

    public int searchInsert(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }

    public double myPow(double x, int n){
        if (n == 0){
            return 1;
        }
        if (n == Integer.MIN_VALUE){
            x = x * x;
            n = n >>1;
        }
        if (n < 0){
            n = -n;
            x = 1/x;
        }
        return n % 2 == 0 ? myPow(x * x, n/2) : x * myPow(x * x, n/2);
    }


    public int mySqrt(int x) {

        int left = 1, right = x;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (mid == x/mid){
                return mid;
            }else if (mid < x/mid){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return right;
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length - 1;
        int n = matrix[0].length - 1;

        int i = m, j = 0;
        while (i >= 0 && j <= n){
            int mid = matrix[i][j];
            if (mid == target){
                return true;
            }else if (mid < target){
                j++;
            }else {
                i--;
            }
        }
        return false;
    }


    //1.first ele equal to the target
    public int searchFirstEleIndex(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                //
                if (mid == 0 || (nums[mid - 1] != target)){
                    return mid;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    //2.last ele equal to the target
    public int searchLastEleIndex(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                //
                if (mid == nums.length - 1 || (nums[mid + 1] != target)){
                    return mid;
                }else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    //3.first ele more than the target
    public int searchFirstMoreThanTheTarget(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target){
                left = mid + 1;
            }else {
                if (mid == 0 || (nums[mid - 1] < target)){
                    return mid;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    //4.last ele less than the target
    public int searchLastMoreThanTheTarget(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else {
                if (mid == nums.length - 1 || (nums[mid + 1] > target)){
                    return mid;
                }else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    int findMin(int[] nums){
        int left = 0, right = nums.length - 1;
        while (left < right){
            if (nums[left] < nums[right]){
                return nums[left];
            }
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= nums[left]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }

    public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }

    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    int count = 0;
    int number = 0;
    public int kthSmallest(TreeNode root, int k) {

        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode root){
        if (root.left != null){
            helper(root.left);
        }
        count--;
        if (count == 0){
            number = root.val;
            return;
        }
        if (root.right != null){
            helper(root.right);
        }
    }


    public int firstBadVersion(int n) {

        int left = 1, right = n;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (!isBadVersion(mid)){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    public boolean isBadVersion(int val){
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs){
        if (strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs){
            char[] ca = new char[26];
            for (char c: s.toCharArray()){
                ca[c - 'a']++;
            }
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)){
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }


    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null){
            return res;
        }
        help(root);
        return res;
    }

    public void help(TreeNode root){
        //use extra function because of the return value
        if (root.left != null){
            res = inorderTraversal(root.left);
        }
        res.add(root.val);
        if (root.right != null){
            res = inorderTraversal(root.right);
        }
    }

    public int singleNumber(int[] nums) {

        if (nums == null || nums.length == 0){
            return -1;
        }
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            tmp = tmp ^ nums[i];
        }
        return tmp;
    }


    public List<String> findRepeatedDnaSequences(String s) {

        Set<String> seen = new HashSet<>();
        List<String> repeated = new ArrayList<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten)){
                repeated.add(ten);
            }
        }
        return repeated;
    }

    public boolean isHappy(int n) {

        Set<Integer> set = new HashSet<>();
        int sum = 0, tmp = 0;
        while (set.add(n)){
            while (n != 0){
                tmp = n % 10;
                sum += tmp * tmp;
                n /= 10;
            }
            if (sum == 1){
                return true;
            }else {
                n = sum;
            }
        }
        return false;
    }
}
