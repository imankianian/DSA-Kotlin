package queue

class RingBufferQueue<T: Any>(size: Int): Queue<T> {

    private val ringBuffer: RingBuffer<T> = RingBuffer(size)
    override val count: Int
        get() = ringBuffer.count

    override fun peek() = ringBuffer.first

    override fun enqueue(element: T): Boolean = ringBuffer.write(element)

    override fun dequeue(): T? = ringBuffer.read()

    override fun toString(): String {
        return ringBuffer.toString()
    }
}