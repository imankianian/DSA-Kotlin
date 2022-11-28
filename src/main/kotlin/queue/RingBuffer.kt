package queue

class RingBuffer<T: Any>(private val size: Int) {

    private var array = ArrayList<T?>(size)
    private var readIndex = 0
    private var writeIndex = 0

    private val availableSpaceForReading: Int
        get() = writeIndex - readIndex

    private val availableSpaceForWriting: Int
        get() = size - availableSpaceForReading

    val count: Int
        get() = availableSpaceForReading

    val isEmpty: Boolean
        get() = count == 0

    val isFull: Boolean
        get() = availableSpaceForWriting == 0

    val first: T?
        get() = array.getOrNull(readIndex)

    fun write(element: T): Boolean {
        if (!isFull) {
            if (array.size < size) {
                array.add(element)
            } else {
                array[writeIndex % size] = element
            }
            writeIndex++
            return true
        }
        return false
    }

    fun read(): T? {
        if (isEmpty) return null
        return array[readIndex++ % size]
    }

    override fun toString(): String {
        val values = (0 until availableSpaceForReading).map {
            offset -> "${array[(readIndex + offset) % size]!!}"
        }
        return values.joinToString(prefix = "[", separator = ", ", postfix = "]")
    }
}