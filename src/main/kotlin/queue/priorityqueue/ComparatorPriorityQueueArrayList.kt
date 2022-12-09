package queue.priorityqueue

import java.util.*
import kotlin.Comparator

class ComparatorPriorityQueueArrayList<T: Comparator<T>>(private val comparator: Comparator<T>):
AbstractPriorityQueueArrayList<T>(){

    override fun sort() {
        Collections.sort(elements, comparator)
    }
}