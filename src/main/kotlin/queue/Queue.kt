package queue

interface Queue<T: Any> {

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0

    fun enqueue(element: T): Boolean

    fun dequeue(): T?

    fun peek(): T?
}