package Problem3

import java.time.LocalDateTime
import java.time.Month
import java.time.Year
import java.util.*
import kotlin.Comparator

class Date: Comparable<Date>{
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0

    constructor(){
        year = LocalDateTime.now().year
        month = LocalDateTime.now().monthValue
        day = LocalDateTime.now().dayOfMonth
    }

    constructor(year: Int, month: Int, day: Int){
        this.year = year
        this.month = month
        this.day = day
    }

    fun getYear(): Int {
        return year;
    }

    fun getMonth(): Int {
        return month;
    }

    fun getDay(): Int {
        return day;
    }

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
    if ( this.getYear() % 400 == 0 ){
        return true
    } else if (this.getYear() % 4 == 0 && this.getYear() % 100 != 0){
        return true
    }
    return false
}

fun Date.valid(): Boolean {
    val february: Int = if (this.leapYear()) {29} else {28}
    val numberOfDays = listOf(31,february,31,30,31,30,31,31,30,31,30,31)
    if (this.getYear() < 0) return false
    if (this.getMonth() !in 1..12) return false
    if (this.getDay() > numberOfDays.get(this.getMonth()-1)) return false
    return true
}