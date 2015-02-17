
package juice;


public class JuiceCount {
    public int WashNumber;
    public String A[];
    public int counter;
    JuiceCount (String a[],int n,String b[]){
        int wn=0;
        A = new String[80];
        for (int i = 0 ; i < n ; i++)
        {
            A[i] = a[i];
        }
        counter = n;
        for (int i = 0 ; i < n-1 ; i++)
        {
            for (int j = 0 ; j < b.length ; j++)
            {
               if(A[i].equals(b[j]))
               {
                   wn++;
               }
            }
        }
        WashNumber=wn;
    }
    public void printArray(){
     try {
            System.out.print("Times to wash: " + this.WashNumber + "Recepie: ");
            int n = this.counter;
            for(int i = 0 ; i < n ; i++)
            {
                System.out.print(this.A[i] + " ");
            }
            System.out.println();
        }
        catch(NullPointerException e){}
    }
}
