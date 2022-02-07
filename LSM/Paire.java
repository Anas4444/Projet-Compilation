public class Paire<K, V> {
    public K v1;
    public V v2;
    
    public Paire() {}
    public Paire(K v1, V v2)
    {
        this.v1 = v1;
        this.v2 = v2;
    }

    public void put(K v1, V v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public K getV1() { return this.v1; }

    public V getV2() { return this.v2; }

    public String toString() {
        return "[" +  v1  + ", " + v2 + "]";
    }
}
