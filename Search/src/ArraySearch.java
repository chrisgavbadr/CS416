public class ArraySearch<T extends Comparable<? super T>> implements Search {

    private Comparable[] list;

    public ArraySearch(T[] list) {
        this.list = list;
    }

    @Override
    public boolean isEmpty() {
        return list.length == 0;
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean isSorted() {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i].compareTo(list[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int index(Object obj) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }
}
