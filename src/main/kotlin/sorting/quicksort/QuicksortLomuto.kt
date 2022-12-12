package sorting.quicksort

import sorting.swapAt
import java.util.Stack

fun <T: Comparable<T>> MutableList<T>.partitionLomuto(low: Int, high: Int): Int {
    val pivot = this[high]
    var i = low
    for (j in low until high) {
        if (this[j] <= pivot) {
            this.swapAt(i, j)
            i++
        }
    }
    this.swapAt(i, high)
    return i
}

fun <T: Comparable<T>> MutableList<T>.quicksortLomuto(low: Int, high: Int) {
    if (low < high) {
        val pivot = this.partitionLomuto(low, high)
        this.quicksortLomuto(low, pivot - 1)
        this.quicksortLomuto(pivot + 1, high)
    }
}

fun <T: Comparable<T>> MutableList<T>.quicksortIterableLomuto(low: Int, high: Int) {
    val stack = Stack<Int>()
    stack.push(low)
    stack.push(high)
    while (!stack.isEmpty()) {
        val end = stack.pop() ?: continue
        val start = stack.pop() ?: continue
        val pivot = this.partitionLomuto(start, end)
        if (pivot - 1 > start) {
            stack.push(start)
            stack.push(pivot - 1)
        }
        if (pivot + 1 < end) {
            stack.push(pivot + 1)
            stack.push(end)
        }
    }
}