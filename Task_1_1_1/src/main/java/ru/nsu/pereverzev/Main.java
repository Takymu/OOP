package ru.nsu.pereverzev;

public class Main {
    public static void heapsort(int[] array) {
        Heap heap = new Heap(array.length);
        for(int i = 0; i < array.length; i++) {
            heap.add(array[i]);
        }
        for(int i = array.length; i > 0; i--) {
            array[i-1] = heap.pop();
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        heapsort(arr);
    }
}

class Heap {
    int[] heap;
    int cursize = 0;
    Heap(int heapsize) {
        heap = new int[heapsize];
    }

    public void add(int n) {
        heap[cursize] = n;
        cursize++;
        sift_up();
    }

    public int pop() {
        int res = heap[0];
        sift_down();
        return res;
    }

    private void sift_up() {
        int curId = cursize - 1;
        while (curId != 0)
        {
            int parId = (curId - 1) / 2;
            if (heap[parId] < heap[curId])
            {
                int temp = heap[parId];
                heap[parId] = heap[curId];
                heap[curId] = temp;
            }
            curId = parId;
        }
    }

    private void sift_down() {
        cursize--;
        int curid = 0;
        heap[0] = heap[cursize];
        while(curid * 2 + 1 < cursize) {
            if(heap[curid] > heap[curid * 2 + 1] && heap[curid] > heap[curid * 2 + 2]) {
                break;
            }
            int max_id = curid * 2 + 1;
            if(heap[max_id] < heap[curid * 2 + 2]) {
                max_id = curid * 2 + 2;
            }
            int temp = heap[curid];
            heap[curid] = heap[max_id];
            heap[max_id] = temp;
            curid = max_id;
        }
    }
}
