package io.gen.desigin.pattern.decorator;

public class Sort {

    public int[] bubbleSort(int[] A, int n) {
        // write code here
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (A[j] > A[j + 1]){
                    swap(A, j, j + 1);
                }
            }
        }
        return A;
    }

    private void swap(int[] A, int a, int b){
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }


    public int[] selectionSort(int[] A, int n) {
        // write code here
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j] < A[minIndex]){
                    minIndex = j;
                }
            }
            swap(A, i, minIndex);
        }
        return A;
    }


    public int[] insertionSort(int[] A, int n) {
        // write code here
        for (int i = 1; i < n; i++) {
            int index = i;
            while (index > 0){
                if (A[index] < A[index - 1]){
                    swap(A, index, index - 1);
                    index--;
                }else {
                    break;
                }
            }
        }
        return A;
    }


    public int[] mergeSort(int[] A, int n) {
        // write code here
        divide(A, 0, n - 1);
        return A;
    }

    private void divide(int[] A, int left, int right){
        if (left >= right){
            return;
        }
        int mid = left + (right - left) >> 1;
        divide(A, left, mid);
        divide(A, mid + 1, right);
        merge(A, left, mid, right);
    }

    private void merge(int[] A, int left, int mid, int right){
        int[] tmp = new int[A.length];
        int i = left, l = left, r = mid + 1, count = left;
        while (l <= mid && r <= right){
            if (A[l] < A[r]){
                tmp[i++] = A[l++];
            }else {
                tmp[i++] = A[r++];
            }
        }
        while (l <= mid){
            tmp[i++] = A[l++];
        }
        while (r <= right){
            tmp[i++] = A[r++];
        }

        while (count <= right){
            A[count] = tmp[count];
            count++;
        }
    }


    public int[] quickSort(int[] A, int n) {
        // write code here
        sort(A,0,n-1);
        return A;
    }

    private void sort(int[] A, int left, int right){
        if (left < right){
            int l = left, r = right, tmp = A[left];
            while (l < r){
                while (l < r && A[r] > tmp){
                    r--;
                }
                if (l < r){
                    A[l++] = A[r];
                }
                while (l < r && A[l] < tmp){
                    l++;
                }
                if (l < r){
                    A[r--] = A[l];
                }
            }
            A[l] = tmp;
            sort(A, left, l - 1);
            sort(A, l + 1, right);
        }
    }




    public int[] heapSort(int[] A, int n){
        buildHeap(A, n);
        for (int i = A.length - 1; i >= 0; i--) {
            swap(A, i, 0);
            adjustHeap(A, i, 0);
        }
        return A;
    }

    private void buildHeap(int[] A, int n){
        for (int i = A.length/2; i >= 0; i--) {
            adjustHeap(A, n, i);
        }
    }

    private void adjustHeap(int[] A, int size, int parent){
        int i = 2 * parent + 1;
        int j = 2 * parent + 2;
        int max = parent;
        if (i < size && A[i] > A[max]) max = i;
        if (j < size && A[j] > A[max]) max = j;
        if (max != parent){
            swap(A, max, parent);
            adjustHeap(A, size, max);
        }
    }



}
