package queue.priorityqueue

class CustomPriorityQueueArrayList<T: Comparable<T>>: AbstractPriorityQueueArrayList<T>() {

    override fun sort() {
        var index = count - 2
        while (index >= 0 && elements[index + 1].compareTo(elements[index]) > 0) {
            swap(index, index + 1)
            index--
        }
    }

    private fun swap(i: Int , j: Int) {
        val temp = elements[i]
        elements[i] = elements[j]
        elements[j] = temp
    }
}