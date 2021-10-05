package Problem3

class MyComparator: Comparator<Date> {
    override fun compare(o1: Date?, o2: Date?): Int {
        if(o1 == null || o2 == null){
            return 0;
        }
        return o1.day.compareTo(o2.day)
    }

}