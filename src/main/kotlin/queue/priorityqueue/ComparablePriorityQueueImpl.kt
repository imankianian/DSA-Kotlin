package queue.priorityqueue

import tree.binary.heap.ComparableHeapImpl

class ComparablePriorityQueueImpl<T: Comparable<T>>: AbstractPriorityQueue<T>() {

    override val heap = ComparableHeapImpl<T>()
}