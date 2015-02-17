
package juice;


public class Juice {
    public String A[];
    public int counter;
    Juice (String a[],int n) {
        A=new String[80];
        for (int i = 0 ; i < n ; i++) {
            A[i] = a[i];
        }
        counter = n;
    }

    public void printArray() {
     try {
            int n = this.counter;
            for(int i = 0; i < n; i++) {
                System.out.print(this.A[i]+" ");
            }
            System.out.println();
        }
        catch(NullPointerException e){}
    }
}
