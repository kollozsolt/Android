import java.util.*
import kotlin.math.sqrt

fun main(args: Array<String>) {

    //1
    val first_num = 2;
    val second_num = 3;
    val sum = first_num + second_num;
    println("$first_num + $second_num = $sum");

    //2
    //a
    val daysOfWeek = mutableListOf("MONDAY", "Tuesday", "Wednesday", "ThursDAY", "FRIDAY", "Saturday", "Sunday");
    for (s in daysOfWeek){
        println(s);
    }
    //b + c
    println(
        daysOfWeek
            .filter { it.startsWith("T") && it.contains( "e") }
    );
    //d
    println(
        daysOfWeek
            .filter { it.length == 6 }
    )

    //3
    //var range = readLine();
    val range = 5;
    var colCount = 0;
    for ( i in 0..(range?.toInt() ?: 100)){
        if ( isPrime(i) ){
            print("$i ");
            colCount ++;
        }
        if (colCount == 10){
            colCount = 0;
            println();
        }
    }
    println();

    //4
    val str = "Hello world!"
    val str2 = "SGVsbG8gd29ybGQh"
    println(Base64.getEncoder().encodeToString("Hello world!".toByteArray()))
    println(String(Base64.getDecoder().decode("SGVsbG8gd29ybGQh")))

    println(messageCoding(str, ::encode))
    println(messageCoding(str2, ::decode))

    //5
    val listOfNumbers = listOf(1,2,3,4,5,6,7,8,9,10,11,12,16)
    evenNumbers(listOfNumbers);

    //6
    val listOfNumbers2 = listOf(1.5,2.6,5,6.6,8.6,1,2,4,9)
    println( listOfNumbers2
        .filter { it::class.simpleName == "Int" }
        .map { it.toDouble() * 2 }
    )

    println(daysOfWeek.map{ it.toUpperCase() })

    println(daysOfWeek.map{ it.toLowerCase().capitalize() })

    println(daysOfWeek.map { it.length })

    var x = 0.0;
    daysOfWeek.map { x += it.length }
    println(x / daysOfWeek.size);

    //7
    val daysWithN = mutableListOf<String>()
    daysOfWeek.filter { it.toLowerCase().contains('n') }.forEach{daysWithN.add(it)}
    daysOfWeek.removeAll(daysWithN)
    println(daysOfWeek)

    daysOfWeek.withIndex().forEach{println("Item at ${it.index} is ${it.value}")}

    daysOfWeek.sort()
    println(daysOfWeek)

    //8
    val array = Array(10) { (0..100).random() }
    array.forEach { println(it) }

    array.sort()
    array.forEach { print("${it} ") }

    println()
    println(containEvenNumber(array))

    println(allIsEven(array))

    val arrayWithOneElement = Array(1){0.0};
    array.forEach { arrayWithOneElement[0] += it.toDouble() }
    arrayWithOneElement[0] /= array.size.toDouble();
    arrayWithOneElement.forEach { println(it) }

}

fun isPrime( num: Int): Boolean{
    if ( num == 0 || num == 1 ){
        return false;
    }
    if ( num == 2 ){
        return true;
    }
    for ( i in 2..num/2){
        if (num % i == 0){
            return false;
        }
    }
    return true;
}

fun encode(str: String): String{
    return Base64.getEncoder().encodeToString(str.toByteArray())
}

fun decode(str: String): String{
    return String(Base64.getDecoder().decode(str))
}

fun messageCoding(msg: String, func: (String) -> String): String{
    return func(msg);
}

fun evenNumbers(list: List<Int>) = println(list.filter { x -> x%2 == 0 })

fun containEvenNumber (array: Array<Int>): Boolean{
    array.forEach { if(it % 2 == 0) return true }
    return false
}

fun allIsEven (array: Array<Int>): Boolean{
    array.forEach { if(it % 2 != 0) return false }
    return true
}