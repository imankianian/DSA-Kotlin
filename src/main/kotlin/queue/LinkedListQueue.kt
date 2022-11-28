package queue

import linkedlist.DoublyLinkedList

class LinkedListQueue<T: Any>: Queue<T> {

    private val list = DoublyLinkedList<T>()
    private var size = 0
    override val count: Int
        get() = size

    override fun dequeue(): T? {
        if (isEmpty) return null
        val firstNode = list.pop()
        size--
        return firstNode
    }

    override fun peek(): T? {
        return list.nodeAt(0)?.value
    }

    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++
        return true
    }

    override fun toString(): String {
        return list.toString()
    }
}