package Problem1

/////lzoli02
enum class DictionaryType{
    ARRAY_LIST,
    TREE_SET,
    HASH_SET
}

fun main(){
    val dictProvider = DictionaryProvider()
    val dict: IDictionary = dictProvider.createDictionary(DictionaryType.TREE_SET)
    println("Number of words: ${dict.size()}")
    println(dict.javaClass.simpleName)
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
}
