package QaDev;

import java.util.NoSuchElementException;
import java.util.Scanner;
public class Solutions
{
    public static void alterTeams(SLL devTeam, SLL qaTeam)
    {
        SLLNode najmlad = qaTeam.first;
        SLLNode pamti = null;
        SLLNode dvizi = devTeam.getFirst();
        int min = 9999;
        while(najmlad!=null)
        {
            if(najmlad.age<=min)
            {
                min = najmlad.age;
                pamti = najmlad;
            }
            najmlad=najmlad.succ;
        }
        qaTeam.delete(pamti);
        int size;
        if(devTeam.length()%2==0)
        {
            size = devTeam.length()/2;
        }
        else
        {
            size = devTeam.length()/2 + 1;
        }
        for(int i=0;i<size;i++)
        {
            dvizi=dvizi.succ;
        }
        devTeam.insertBefore(pamti.id, pamti.age, dvizi);
    }

    public static void main(String[] args)
    {
        SLL dev = new SLL();
        SLL qa = new SLL();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();

        for(int i=0;i<n;i++)
        {
            int id = input.nextInt();
            int age = input.nextInt();
            dev.insertLast(id,age);
        }
        for(int j=0;j<m;j++)
        {
            int id = input.nextInt();
            int age = input.nextInt();
            qa.insertLast(id,age);
        }
        alterTeams(dev,qa);
        System.out.println(dev);
        System.out.println(qa);
    }
}



class SLLNode
{
    int id;
    int age;
    SLLNode succ;

    public SLLNode(int id,int age,SLLNode succ)
    {
        this.id = id;
        this.age = age;
        this.succ = succ;
    }
    @Override
    public String toString()
    {
        return String.valueOf(id);
    }
}

class SLL
{
    SLLNode first;

    public SLL()
    {
        this.first = null;
    }

    public int length()
    {
        int ret;
        if (first != null)
        {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        }
        else
            return 0;
    }

    public void deleteFirst()
    {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
        }
        else
        {
            System.out.println("Listata e prazna");
        }
    }

    public void delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if(first ==node){
                this.deleteFirst();
                return;
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
            } else
            {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else
        {
            System.out.println("Listata e prazna");
        }

    }

    public void insertFirst(int id,int age)
    {
        SLLNode ins = new SLLNode(id, age, first);
        first = ins;
    }
    public void insertAfter(int id,int age,SLLNode node) {
        if (node != null) {
            SLLNode ins = new SLLNode(id,age,node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(int id,int age,SLLNode before) {

        if (first != null) {
            SLLNode tmp = first;
            if(first==before){
                this.insertFirst(id,age);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode ins = new SLLNode(id,age, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }


    public void insertLast(int id, int age)
    {
        if (first != null)
        {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, age, null);
            tmp.succ = ins;
        }
        else
        {
            insertFirst(id, age);
        }
    }
    public SLLNode getFirst()
    {
        return first;
    }

    @Override
    public String toString() {
        String s = new String();
        SLLNode dvizi = first;
        while(dvizi!=null)
        {
            s= s +dvizi.id + " ";
            dvizi = dvizi.succ;
        }
        return s;
    }
}