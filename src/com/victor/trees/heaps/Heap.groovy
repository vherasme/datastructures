package com.victor.trees.heaps

abstract class Heap<T extends Comparable<T>> {

    static MAX_SIZE = 40
    T[] arrayOfNodes
    int count
    HeapTypes heapType

    static final Map<Enum, Closure> heapify = new HashMap<>()
    static {
        heapify.put(HeapTypes.MAX, { Comparable i, Comparable j ->
            return (i != null && j != null && i.compareTo(j) > 0)
        })

        heapify.put(HeapTypes.MIN, { Comparable i, Comparable j ->
            return (i != null && j != null && i.compareTo(j) < 0)
        })
    }

    Heap(int size, HeapTypes type) {
        arrayOfNodes = (T[]) new Comparable[size + 1]
        heapType = type
    }

    abstract void add(T item)

    abstract T remove()

    abstract void traversal()

    abstract void heapSort()

    boolean isEmpty() {
        return count == 0
    }

    /**
     * This method returns the first member of the arrayOfNodes
     *
     * */
    T peek() {
        return arrayOfNodes[0]
    }

    T poll(int lastIndex) {
        int heapPeak = peek()
        swap(0, lastIndex - 1)
        fixHeap(0, lastIndex - 1)
        return heapPeak
    }

    void fixHeap(int index, int lastIndex) {
        int left = 2 * index + 1
        int right = 2 * index + 2
        int indexLargest = index

        //if left is greater than its parent...
        if (left < lastIndex && heapify[heapType].call(arrayOfNodes[left], arrayOfNodes[index]))
            indexLargest = left
        //But then right is even greater than left...
        if (right < lastIndex && heapify[heapType].call(arrayOfNodes[right], arrayOfNodes[indexLargest]))
            indexLargest = right

        //Swap element with the largest found, unless they have the same index
        if (index != indexLargest) {
            swap(index, indexLargest)
            fixHeap(indexLargest, lastIndex)
        }
    }

    int size() {
        return count
    }

    protected void swap(int indexOne, int indexTwo) {
        T tempNode = arrayOfNodes[indexOne]
        arrayOfNodes[indexOne] = arrayOfNodes[indexTwo]
        arrayOfNodes[indexTwo] = tempNode
    }
}
