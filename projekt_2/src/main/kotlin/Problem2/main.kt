import java.awt.List

fun main(){
    val name = "John Smith"
    println(name.monogram())
    val list = mutableListOf("apple", "pear", "strawberry", "melon")
    println(list.joined('#'))
    println(list.longest())

}

fun String.monogram(): String{
    return this.split(' ')
                .map{it[0]}
                .joinToString (""){ it.toString() }
}

fun MutableList<String>.joined(by: Char): String = this.joinToString(by.toString())

fun MutableList<String>.longest():String? = this.maxByOrNull{ it.length }