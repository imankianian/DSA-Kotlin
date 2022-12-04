package search

fun <T: Comparable<T>> ArrayList<T>.customBinarySearch(value: T, range: IntRange = indices): Int? {
    if (range.first > range.last) return null
    println("range.first = ${range.first}")
    println("range.last = ${range.last}")
    val size = range.last - range.first + 1
    val middle = range.first + size / 2
    return when {
        this[middle] == value -> middle
        this[middle] > value -> customBinarySearch(value, range.first until middle)
        else -> customBinarySearch(value, (middle + 1)..range.last)
    }
}

fun <T: Comparable<T>> ArrayList<T>.findIndices(value: T): IntRange? {
    val startIndex = indexOf(value)
    val endIndex = lastIndexOf(value)
    if (startIndex == -1 || endIndex == -1) return null
    return startIndex..endIndex
}

private fun <T: Comparable<T>> ArrayList<T>.startIndex(value: T, range: IntRange): Int? {

    val middleIndex = range.first + (range.last - range.first + 1) / 2
    if (middleIndex == 0 || middleIndex == size - 1) {
        return if (this[middleIndex] == value) {
            middleIndex
        } else {
            null
        }
    }
    return if (this[middleIndex] == value) {
        if (this[middleIndex - 1] != value) {
            middleIndex
        } else {
            startIndex(value, range.first until middleIndex)
        }
    } else if (value < this[middleIndex]) {
        startIndex(value, range.first until middleIndex)
    } else {
        startIndex(value, middleIndex + 1..range.last)
    }
}

private fun <T:Comparable<T>> ArrayList<T>.endIndex(value: T, range: IntRange): Int? {

    val middleIndex = range.first + (range.last - range.first + 1) / 2
    if (middleIndex == 0 || middleIndex == size - 1) {
        return if (this[middleIndex] == value) {
            middleIndex + 1
        } else {
            null
        }
    }
    return if (this[middleIndex] == value) {
        if (this[middleIndex + 1] != value) {
            middleIndex + 1
        } else {
            endIndex(value, middleIndex + 1 .. range.last)
        }
    } else if (value < this[middleIndex]) {
        endIndex(value, range.first until middleIndex)
    } else {
        endIndex(value, middleIndex + 1 .. range.last)
    }
}