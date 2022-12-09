package queue.priorityqueue

import queue.Queue

abstract class AbstractPriorityQueueArrayList<T: Any>: Queue<T> {

    protected val elements = ArrayList<T>()

    abstract fun sort()

    override val count: Int
        get() = elements.size

    override fun peek() = elements.firstOrNull()

    override fun enqueue(element: T): Boolean {
        elements.add(element)
        sort()
        return true
    }

    override fun dequeue() = if (isEmpty) null else elements.removeAt(0)

    override fun toString() = elements.toString()
}