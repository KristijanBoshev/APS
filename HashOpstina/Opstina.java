package HashOpstina;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Opstina
{
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       CBHT<String,String> poz = new CBHT<>(2*n);
       CBHT<String,String> neg = new CBHT<>(2*n);
       
       for(int i = 0;i<n;i++){
           String opstina = sc.next();
           String prezime = sc.next();
           String rez = sc.next();
           
           if(rez.equals("позитивен")){
               poz.insert(opstina,prezime);
           }
           else{
               neg.insert(opstina,prezime);
           }
       }
       String opstina = sc.next();
       SLLNode<MapEntry<String,String>> curr = poz.search(opstina);
       int pp = 0;
       int nn = 0;

           while (curr != null) {
               if (curr.element.key.equals(opstina)) {
                   pp++;
               }
               curr = curr.succ;
           }

       curr = neg.search(opstina);

           while (curr != null) {
               if (curr.element.key.equals(opstina)) {
                   nn++;
               }
               curr = curr.succ;
           }

        float faktor = (float)pp / (pp+nn);
        System.out.println(String.format("%.2f",faktor));


    }
}
class Merenje
{
    double suma;
    int br;

    public Merenje(double suma, int br)
    {
        this.suma = suma;
        this.br = br;
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class MapEntry <K extends Comparable<K>,E> implements Comparable<K>
{
    K key;
    E value;

    public MapEntry (K key,E val)
    {
        this.key = key;
        this.value = val;
    }

    @Override
    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    @Override
    public String toString() {
        return "<" + key + "," + value + ">";
    }
}
class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {    // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        //for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
        //if (key.equals(((MapEntry<K, E>) curr.element).key)) {
        // Make newEntry replace the existing entry ...
        //curr.element = newEntry;
        // return;
        // }
        //}
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}