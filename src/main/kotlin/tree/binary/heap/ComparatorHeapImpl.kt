package tree.binary.heap

class ComparatorHeapImpl<T: Any>(private val comparator: Comparator<T>): AbstractHeap<T>() {

    override fun compare(a: T, b: T): Int = comparator.compare(a, b)

    companion object {
        fun <T: Any> create(elements: ArrayList<T>, comparator: Comparator<T>): Heap<T> {
            val heap = ComparatorHeapImpl<T>(comparator)
            heap.heapify(elements)
            return heap
        }
    }
}