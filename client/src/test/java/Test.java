public class Test{
    public static void main(String[] args) {
        int[] a={39,48,57,62,40,51,43,50};
        int[] b={9,36,17,5,28,30,24,19};

        System.err.println("结果为：");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int sum = a[i] + b[j];
                int differ = a[i] - b[j];
                System.err.println(a[i] +"+"+ b[j]+"="+sum +"\t\t\t"+a[i] +"-"+ b[j]+"="+differ);
            }
            System.err.println();
        }
    }
}