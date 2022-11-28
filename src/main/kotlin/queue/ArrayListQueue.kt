package queue

class ArrayListQueue<T: Any>: Queue<T> {

    private val list = arrayListOf<T>()

    override val count: Int
        get() = list.size

    override fun dequeue(): T? =
        if (isEmpty) null else list.removeAt(0)

    override fun peek(): T? {
        return list.getOrNull(0)
    }

    override fun enqueue(element: T): Boolean {
        list.add(element)
        return true
    }

    override fun toString() = list.toString()
}