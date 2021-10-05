package Problem3

import java.time.LocalDateTime
import java.time.Month
import java.time.Year
import java.util.*
import kotlin.Comparator

data class Date(var year: Int = LocalDateTime.now().year,
                var month: Int = LocalDateTime.now().monthValue,
                var day: Int = LocalDateTime.now().dayOfMonth): Comparable<Date>{

    fun print(){
        println("$year - $month - $day")
    }

    override fun compareTo(other: Date): Int {
        if (this.year != other.year) return this.year - other.year
        if (this.month != other.month) return this.month - other.month
        if (this.day != other.day) return this.day - other.day
        return 0
    }
}

fun Date.leapYear():Boolean {
    if ( this.year % 400 == 0 ){
        return true
    } else if (this.year % 4 == 0 && this.year % 100 != 0){
        return true
    }
    return false
}

fun Date.valid(): Boolean {
    val february: Int = if (this.leapYear()) {29} else {28}
    val numberOfDays = listOf(31,february,31,30,31,30,31,31,30,31,30,31)
    if (this.year < 0) return false
    if (this.month !in 1..12) return false
    if (this.day > numberOfDays.get(this.month-1)) return false
    return true
}