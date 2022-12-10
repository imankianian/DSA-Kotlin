package sorting

fun <T: Comparable<T>> MutableList<T>.bubbleSort(showPasses: Boolean = false) {

    if (this.size < 2) return
    var swapped = false
    for (end in this.lastIndex downTo 0) {
        for (current in 0 until end) {
            if (this[current] > this[current + 1]) {
                swapAt(current, current + 1)
                swapped = true
            }
        }
        if (swapped) println(this)
        if (!swapped) return
    }
}