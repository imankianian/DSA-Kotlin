package queue.priorityqueue

import java.util.*

class ComparablePriorityQueueArrayList<T: Comparable<T>>: AbstractPriorityQueueArrayList<T>() {

    override fun sort() {
        Collections.sort(elements)
    }
}