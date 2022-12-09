package queue.priorityqueue

import tree.binary.heap.ComparatorHeapImpl
import tree.binary.heap.Heap

class ComparatorPriorityQueueImpl<T: Any>(private val comparator: Comparator<T>): AbstractPriorityQueue<T>() {

    override val heap = ComparatorHeapImpl(comparator)
}