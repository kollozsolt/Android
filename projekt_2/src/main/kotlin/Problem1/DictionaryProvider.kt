package Problem1

class DictionaryProvider {
    fun createDictionary(type: DictionaryType): IDictionary{
        when (type){
            DictionaryType.ARRAY_LIST -> return ListDictionary
            DictionaryType.HASH_SET -> return HashSetDictionary
            DictionaryType.TREE_SET -> return TreeSetDictionary
        }
    }
}