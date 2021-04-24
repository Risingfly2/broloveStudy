package io.gen;

import io.gen.desigin.pattern.decorator.MinStack;
import io.gen.desigin.pattern.decorator.MyHashMap;
import io.gen.desigin.pattern.decorator.MyStack;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class Test {

    private String name;

    @org.junit.jupiter.api.Test
    public void test(){
        assert name != null;
        System.out.println("kkkkkkk");
    }

    @org.junit.jupiter.api.Test
    public void testchar(){
        String str = "zbcd";
        char[] chars = new char[26];
        for (char c: str.toCharArray()){
            chars[c - 'a']++;
        }
        System.out.println(Arrays.toString(chars));
    }

    @org.junit.jupiter.api.Test
    public void testN(){
        Assertions.assertEquals(true, isHappy(19));
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
            sum = 0;
        }
        return false;
    }
    public int countPrimes(int n){
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]){
                count++;
                for (int j = 2; i * j < n; j++) {// prime * some = !prime
                    notPrime[i * j] = true;
                }
            }

        }
        return count;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> repeated = new HashSet<>();
        for (int n: nums){
            if (repeated.contains(n)){
                return true;
            }else {
                repeated.add(n);
            }
        }
        return false;
    }


    boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer lastIndex = indices.put(nums[i], i);
            if (lastIndex != null && (i - lastIndex) <= k)
                return true;
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {

        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chart);
        return Arrays.equals(chars, chart);
    }

    public boolean wordPattern(String pattern, String s) {

        String[] str = s.split(" ");
        if (pattern.length() != str.length){
            return false;
        }
        Map<Character, Integer> index1 = new HashMap<>();
        Map<String, Integer> index2 = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            if (!Objects.equals(index1.put(pattern.charAt(i), i), index2.put(str[i], i))){
                return false;
            }
        }
        return true;
    }

    public int longestPalindrome(String s) {

        if (s == null || s.length() == 0){
            return 0;
        }

        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        int res = 0;
        boolean odd = false;
        for (int i = 0; i < 256; i++) {
            if (map[i] % 2 == 0){
                res += map[i];
            }else {
                res += map[i] - 1;
                odd = true;
            }
        }
        if (odd){
            return res + 1;
        }
        return res;
    }

    public int firstUniqChar(String s) {

        if (s == null || s.length() == 0){
            return -1;
        }
        int[] map = new int[128];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] == 1){
                return i;
            }
        }
        return -1;
    }

    public int islandPerimeter(int[][] grid) {

        int count = 0, neighbor = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    count++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1){
                        neighbor++;
                    }
                    if (j < grid[0].length  - 1 && grid[i][j + 1] == 1){
                        neighbor++;
                    }
                }
            }
        }
        return count * 4 - neighbor * 2;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    for (int l = 0; l < D.length; l++) {
                        if (A[i] + B[j] + C[k] + D[l] == 0){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }


    public String frequencySort(String s) {

        int[] map = new int[128];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        Arrays.sort(map);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i]; j++) {
                    sb.append(s.charAt(i));
                }
        }
        return sb.toString();
    }


    public int[][] merge(int[][] intervals) {

        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (intervals[i][0] <= intervals[i - 1][1]){
                    intervals[i][0] = intervals[i - 1][0];
                    intervals[i - 1][0] = -1;
                    intervals[i - 1][1] = -1;
                    count++;
                }
            }
        }
        int[][] res = new int[count][2];
        int k = 0, l = 0;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (intervals[i][j] != -1){
                        res[k++][l++] = intervals[i][j];
                        if (l >= 2){
                            l = 0;
                        }
                }
            }
        }
        return res;
    }

     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


    public ListNode insertionSortList(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = head;
        ListNode pre = dummy;
        ListNode next = null;
        while (cur != null){
            next = cur.next;
            while (pre.next != null && pre.next.val < cur.val){
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            pre = dummy;
            cur = next;
        }
        return dummy.next;
    }


    public String largestNumber(int[] nums) {

        if (nums == null || nums.length == 0){
            return "";
        }
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str, (a, b) -> (b + a).compareTo(a + b));
        if (str[0].charAt(0) == '0'){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s: str){
            sb.append(s);
        }
        return sb.toString();
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

    public boolean isSymmetric(TreeNode root) {

        if (root == null){
            return false;
        }
        return help(root.left, root.right);
    }

    private boolean help(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        return help(left.left, right.right) && help(left.right, right.left);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> pipe = new LinkedList<>();
        pipe.add(root);
        while (!pipe.isEmpty()){
            int size = pipe.size(); //current layer size
            List<Integer> num = new ArrayList<>(size);
            for (int i = size; i > 0; i--) {
                TreeNode tmp = pipe.poll();
                num.add(tmp.val);
                if (tmp.left != null){
                    pipe.add(tmp.left);
                }
                if (tmp.right != null){
                    pipe.add(tmp.right);
                }
            }
            res.add(num);
        }
        return res;
    }
    private boolean ismirror(TreeNode root){

        return true;
    }

    private boolean isSym(List<Integer> list){
        if (list.size() % 2 == 1){
            return false;
        }
        int start = 0, end = list.size() - 1;
        while (start != end){
            if (!list.get(start).equals(list.get(end))){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> mid = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (flag){
                    mid.add(0,tmp.val);
                }else {
                    mid.add(tmp.val);
                }
                if (tmp.left != null){
                    queue.add(tmp.left);
                }
                if (tmp.right != null){
                    queue.add(tmp.right);
                }
            }
            flag = !flag;
            res.add(mid);
        }
        return res;
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> mid = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                mid.add(tmp.val);
                if (tmp.left != null){
                    queue.add(tmp.left);
                }
                if (tmp.right != null){
                    queue.add(tmp.right);
                }
            }
            res.add(0, mid);
        }
        return res;
    }

    public int minDepth(TreeNode root) {

        if (root == null){
            return 0;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null){
                    queue.add(tmp.left);
                }
                if (tmp.right != null){
                    queue.add(tmp.right);
                }
                if (tmp.left == null && tmp.right == null){
                    return count;
                }
            }
        }
        return count;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {

        if (root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                if (i == size - 1){
                    tmp.next = null;
                }else {
                    Node next = queue.peek();
                    tmp.next = next;
                }
                if (tmp.left != null){
                    queue.add(tmp.left);
                }
                if (tmp.right != null){
                    queue.add(tmp.right);
                }

            }
        }
        return root;
    }


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (i == size - 1){
                    res.add(tmp.val);
                }
                if (tmp.left != null){
                    queue.add(tmp.left);
                }
                if (tmp.right != null){
                    queue.add(tmp.right);
                }
            }

        }
        return res;
    }


    int n = 0;
    int m = 0;
    public int numIslands(char[][] grid){
        n = grid.length;
        m = grid[0].length;
        int count = 0;
        if (n == 0){
            return 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j){
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
    }



    @org.junit.jupiter.api.Test
    public void testStack(){
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
    }


    @org.junit.jupiter.api.Test
    public void testStack2(){
        for (;;){
            MyStack stack = new MyStack();
            stack.push(1);
            stack.push(2);
            int a = stack.top();
            int b = stack.pop();
            stack.empty();
            System.out.printf("a = %s, b = %s%n", a,b);
        }

    }

    @org.junit.jupiter.api.Test
    public void testMyMap(){
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1,2);
        myHashMap.put(1,3);
        myHashMap.put(3,4);
        myHashMap.put(5,6);
        myHashMap.put(7,8);
        myHashMap.put(11, 100);
        Assertions.assertEquals(3, myHashMap.get(1));
        Assertions.assertEquals(4, myHashMap.get(3));
        myHashMap.remove(1);
        Assertions.assertEquals(-1, myHashMap.get(1));
        Assertions.assertEquals(8, myHashMap.get(7));
        Assertions.assertEquals(100, myHashMap.get(11));
        for (int i = 0; i < 100000000; i++) {
            myHashMap.put(i, i+ 1);
        }
        for (int i = 0; i < 100_00_0000; i++) {
            System.out.println(myHashMap.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    public void testCombine(){
        List<String> res = letterCombinations("23");
        System.out.println(res);
    }

    private static final String[] KEYS = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

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
        System.out.println(letters);
        for (char c : letters.toCharArray()) {
            System.out.println(prefix);
            prefix.append(c);                         // 添加
            doCombination(prefix, combinations, digits);
            prefix.deleteCharAt(prefix.length() - 1); // 删除
        }
    }
}
