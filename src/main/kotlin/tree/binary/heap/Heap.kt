package tree.binary.heap

interface Heap<T: Any>: Collection<T> {

    fun peak(): T?

    fun merge(heap: AbstractHeap<T>)

    fun isMinHeap(): Boolean
}