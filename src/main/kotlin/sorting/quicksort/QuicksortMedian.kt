package sorting.quicksort

import sorting.swapAt

fun <T: Comparable<T>> MutableList<T>.medianOfThree(low: Int, high: Int): Int {
    val center = (low + high) / 2
    if (this[low] > this[center]) this.swapAt(low, center)
    if (this[low] > this[high]) this.swapAt(low, high)
    if (this[center] > this[high]) this.swapAt(center, high)
    return center
}

fun <T: Comparable<T>> MutableList<T>.quicksortMedian(low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = medianOfThree(low, high)
        this.swapAt(pivotIndex, high)
        val pivot = this.partitionLomuto(low, high)
        this.quicksortLomuto(low, pivot - 1)
        this.quicksortHoare(pivot + 1, high)
    }
}