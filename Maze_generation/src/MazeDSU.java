public class MazeDSU {
    private int[] sets;
    private int[] size;
    private int count;

    public MazeDSU(int count) {
        this.count = count;
        sets = new int[count * count];
        size = new int[count * count];
        for (int i = 0; i < count * count; i++) {
            sets[i] = i;
            size[i] = 1;
        }
    }

    public int convert(int x, int y) {
        return count * x + y;
    }

    public int find(int x) {
        if (sets[x] == x) {
            return x;
        } else {
            sets[x] = find(sets[x]);
            return sets[x];
        }
    }

    public void unite(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        if (size[x] < size[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        sets[y] = x;
        size[x] += size[y];
    }

    public boolean inOneSet(int x, int y) {
        return find(x) == find(y);
    }
}
