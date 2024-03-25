package MergeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }
    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
            }
        } else
            ret = "";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }
    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }
    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            }
        }
        return first;
    }
}

public class Merge {

    public static SLL<Integer> merge(SLL<Integer> list1, SLL<Integer> list2) {
        SLLNode<Integer> curr = list1.getFirst();
        SLLNode<Integer> other = list2.getFirst();
        SLL<Integer> res = new SLL<>();

        while(curr != null && other !=null) {
            for (int i = 0; i < 2; i++) {
                if(curr != null) {
                    res.insertLast(curr.element);
                    curr = curr.succ;
                }
            }
            for (int i = 0; i < 2; i++) {
                if(other !=null) {
                    res.insertLast(other.element);
                    other = other.succ;
                }
            }
        }
            if(curr != null){
                while (curr!=null){
                    res.insertLast(curr.element);
                    curr = curr.succ;
                }
            }
            if(other != null){
                while (other!=null){
                    res.insertLast(other.element);
                    other = other.succ;
                }
            }




        return res;

    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        SLL<Integer> list1 = new SLL<>();
        for(int i=0; i<n; i++){
            list1.insertLast(sc.nextInt());
        }

        int m = sc.nextInt();
        SLL<Integer> list2 = new SLL<>();
        for(int i=0; i<m; i++){
            list2.insertLast(sc.nextInt());
        }
        System.out.println(merge(list1, list2));
    }
}