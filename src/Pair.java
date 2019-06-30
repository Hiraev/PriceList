/**
 * ---------------------------------------
 * Created by Hiraev Malik on 02.03.17.
 * GitHub: https://github.com/Hiraev
 * vk.com: https://vk.com/hiraev
 * ---------------------------------------
 * All rights reserved
 * ---------------------------------------
 * Copied from:
 * @see javafx.util.Pair
 */

public final class Pair<K, V> {
    private K k;
    private V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

    @Override
    public int hashCode() {
        return k.hashCode() * 13 + (v == null ? 0 : v.hashCode());
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another instanceof Pair) {
            Pair pair = (Pair) another;
            if (k != null ? !k.equals(pair.k) : pair.k != null) return false;
            if (v != null ? !v.equals(pair.v) : pair.v != null) return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return k.toString() + " - " + v.toString();
    }
}