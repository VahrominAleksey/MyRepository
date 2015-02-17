package juice;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.String;
import java.util.Collections;


public class Main {
    public static void main(String[] args)throws IOException, EOFException, ClassNotFoundException {
        InputStream I = null;
        InputStreamReader R = null;
        BufferedReader Buf = null;
        String arrStr[]=new String[80];
        String[] lineArr = new String[20];
        int counterArray[]=new int[80];
        String[][] ComponentArray=new String[80][80];
        ArrayList<Juice> field=new ArrayList<Juice>(0);
        int n = 0;
        int K = 0;
        try {
            I = new FileInputStream("juice.in");
            R = new InputStreamReader(I);
            Buf = new BufferedReader(R);
            String tmp;
            n = 0;
            while((tmp = Buf.readLine()) != null) {
                lineArr[n] = tmp;
                n++;
            }
            K = 0;
            for(int j = 0; j < n; j++) {
                arrStr = lineArr[j].split("[ ;,]+");
                int t = 0;
                for(int i=0 ; i<arrStr.length ; i++) {
                    ComponentArray[j][i]=arrStr[i];
                    t++;
                }
                counterArray[K] = t;
                K++;
            }
        }
        catch(IOException e){}
        finally {
            if(I != null)
                I.close();
            if(R != null)
                R.close();
            if(Buf != null)
                Buf.close();
        }
        for(int i = 0; i < K; i++) {
            Juice Arr = new Juice(ComponentArray[i], counterArray[i]);
            Arr.printArray();
            field = createField(field, ComponentArray[i], counterArray[i]);
        }
        printArrayList(field);
        System.out.println();
        sortByName2(field);
        int WC=CounteringOfWashing(field);
        System.out.println();
        PrintStream p = new PrintStream("juice3.out");
        System.out.print(WC + " ");
        p.print(WC);
    }
    public static ArrayList<Juice> createField(ArrayList<Juice> list, String a[], int n) {
        list.add(new Juice(a,n));
        return list;
       }
    public static ArrayList<JuiceCount> createCountField(ArrayList<JuiceCount> list, String a[], int n, String b[]) {
        list.add(new JuiceCount(a,n,b));
        return list;
       }
    public static void printArrayList(ArrayList<Juice> list) throws FileNotFoundException {
            PrintStream p = new PrintStream("juice1.out");
            for(Iterator j = list.iterator(); j.hasNext();) {
                   Juice tmp = (Juice)j.next();
                   int n = tmp.counter;
                   for(int i = 0 ; i < n ; i++) {
                       p.print(tmp.A[i] + " ");
                   }
                   p.println();
            }
    }
    public static void printArrayList3(ArrayList<Juice> list) throws FileNotFoundException {
            PrintStream p = new PrintStream("juice3.out");
            for(Iterator j = list.iterator() ; j.hasNext();) {
                   Juice tmp = (Juice)j.next();
                   int n=tmp.counter;
                   for(int i = 0 ; i < n ; i++) {
                       p.print(tmp.A[i] + " ");
                   }
                   p.println();
            }
    }
    static class stringComparator implements Comparator{
       public int compare(Object o1, Object o2){
          return ((String)o1).compareToIgnoreCase((String)o2);

       }
   }
    public static void sortByName2(ArrayList<Juice> list) throws FileNotFoundException  {
        stringComparator sc = new stringComparator();
        ArrayList<String> ar = new ArrayList<String>(0);
        PrintStream p = new PrintStream("juice2.out");
        ar.add(list.get(0).A[0]);
        for(int i = 0 ; i < list.size() ; i++)
        {
            for(int j = 0 ; j < list.get(i).counter ; j++)
            {
                int t = 0;
                for(int k = 0 ; k < ar.size() ; k++)
                {
                    if(list.get(i).A[j].equals(ar.get(k)))
                    {
                        t++;
                    }
                }
                if(t == 0)
                {
                    ar.add(list.get(i).A[j]);
                }
                t = 0;
            }
        }
        Collections.sort(ar, sc);
        for(int k = 0 ; k < ar.size() ; k++){
               p.print(ar.get(k)+ " ");
            }
        }
    public static int CounteringOfWashing(ArrayList<Juice> list) {
       stringComparator sc = new stringComparator();
       ArrayList<Juice> field=new ArrayList<Juice>(0);
       int ar[] = new int[80];
       for(int j = 0; j < list.size(); j++){
           ar[j] = 0;
       }
       for(int i = 0; i < list.size(); i++)
       {
           ArrayList<String> field2 = new ArrayList<String>(0);
           String Arr[] = new String[80];
           for(int j = 0; j < list.get(i).counter; j++) {
               Arr[j] = list.get(i).A[j];
           }
           for(int j = 0;j < list.get(i).counter; j++) {
              field2.add(Arr[j]);
           }
           Collections.sort(field2, sc);
           for(int j = 0; j < list.get(i).counter; j++) {
              list.get(i).A[j] = field2.get(j);
           }
       }
       while(list.size() > 0)
       {
           int min = list.get(0).counter;
           int minNum = 0;
           for(int i = 0; i < list.size(); i++)
           {
              if(list.get(i).counter < min)
              {
                  min = list.get(i).counter;
                  minNum = i;
              }
           }
           field.add(list.get(minNum));
           list.remove(minNum);
       }
       int WC = 0;
       int P = 0;
       int T = 0;
       while(field.size() > 0)
       {
           T = 0;
           int t[] = new int[80];
           for(int i = 0; i < field.size(); i++)
           {

              if((i != P)&&(T == 0))
              {
                  for(int j = 0; j < field.get(P).counter; j++){
                      t[j] = 0;
                  }
                  for(int j = 0; j<field.get(P).counter; j++)
                  {
                     for(int k = 0; k<field.get(i).counter; k++)
                     {
                        if(field.get(P).A[j].equals(field.get(i).A[k]))
                        {
                           t[j]++;
                        }
                     }
                  }
              }
              int tt = 0;
              for(int j = 0; j < field.get(P).counter; j++){
                  if(t[j] == 0)
                  {
                      tt++;
                  }
              }
              if(tt == 0)
              {
                  field.remove(P);
                  P = i - 1;
                  T++;
              }
           }
           if(T == 0)
           {
               field.remove(P);
               WC++;
               P = 0;
           }
       }
       return WC;
    }

}

