import Problem3.Date
import Problem3.leapYear
import Problem3.valid
import kotlin.random.Random

fun main(){
    val validDates = mutableListOf<Date>()
    var date = Date()
    println("------ A hibás dátumok ------")
    while (validDates.size < 10){
        date = Date((-10..2021).random(), (0..20).random() , (0..50).random())
        if ( !date.valid() ) {
            date.print()
        } else {
            validDates.add(date)
        }
    }

    println("------ A helyes dátumok ------")
    validDates.forEach{it.print()}

    validDates.sort()
    println("------ Dátumok rendezve ------")
    validDates.forEach{it.print()}

    validDates.reverse()
    println("------ Fordított sorrend ------")
    validDates.forEach{it.print()}
    
    validDates.sortWith(Comparator { o1: Date, o2: Date -> o1.getDay() - o2.getDay() })
    println("------ Rendezve napok szerint ------")
    validDates.forEach{it.print()}

}

