package tree.binary.heap

class ComparableHeapImpl<T: Comparable<T>>(): AbstractHeap<T>() {

    override fun compare(a: T, b: T): Int = a.compareTo(b)

    companion object {
        fun <T: Comparable<T>> create(elements: ArrayList<T>): Heap<T> {
            val heap = ComparableHeapImpl<T>()
            heap.heapify(elements)
            return heap
        }
    }
}