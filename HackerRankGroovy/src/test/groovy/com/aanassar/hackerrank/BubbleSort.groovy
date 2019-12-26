import groovy.transform.Canonical
import groovy.transform.Immutable

@Canonical
@Immutable
class Element implements Comparable<Element> {

    int value;
    int index;

    @Override
    int compareTo(Element o) {
        final int ech = Integer.compare(value, o.value);
        ech != 0 ? ech : Integer.compare(index, o.index)
    }
}

long countInversions(int[] a) {
    TreeSet m = []
    long result = 0L
    for (int i = a.length - 1; i >= 0; --i) {
        Element e = new Element(a[i], i)
        Set sub = m.headSet(e, false)
        result += sub.size()
        m << e
    }
    return result
}

def countInversions(String s) {
    def a = s.split.toInteger() as int[]
    countInversions a
}

def sample00 = '1 1 1 2 2'
println(countInversions(sample00))
def sample01 = '2 1 3 1 2'
println(countInversions(sample01))