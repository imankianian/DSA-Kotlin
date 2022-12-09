package queue.priorityqueue

import queue.Queue
import tree.binary.heap.Heap

abstract class AbstractPriorityQueue<T: Any>: Queue<T> {

    abstract val heap: Heap<T>
        get

    override val count: Int
        get() = heap.count

    override fun enqueue(element: T): Boolean {
        heap.insert(element)
        return true
    }

    override fun dequeue() = heap.remove()

    override fun peek() = heap.peak()
}