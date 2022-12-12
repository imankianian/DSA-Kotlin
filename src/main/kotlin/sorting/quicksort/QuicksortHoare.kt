package sorting.quicksort

import sorting.swapAt

fun <T: Comparable<T>> MutableList<T>.partitionHoare(low: Int, high: Int): Int {
    val pivot = this[low]
    var i = low - 1
    var j = high + 1
    while (true) {
        do {
            j--
        } while (this[j] > pivot)
        do {
            i++
        } while (this[i] < pivot)
        if (i < j) {
            this.swapAt(i, j)
        } else {
            return j
        }
    }
}

fun <T: Comparable<T>> MutableList<T>.quicksortHoare(low: Int, high: Int) {
    if (low < high) {
        val pivot = this.partitionHoare(low, high)
        this.quicksortHoare(low, pivot)
        this.quicksortHoare(pivot + 1, high)
    }
}