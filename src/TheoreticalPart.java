import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TheoreticalPart {
    public static void main(String[] args) {
        Set<Integer> Q = new HashSet();
        Set<Integer> AQ = new HashSet();
        for(int i = 0; i<6571;i++){
            Q.add((i*i)%6571);
            int x = (int)(Math.pow(-1,i)*(i*i))%6571;
            AQ.add(x<0? x+6571:x);
        }
            Random rnd = new Random();

        int time=0;


        DoubleHashTable QTable = new DoubleHashTable(10000019, 1000000007);
            for(int k=0;k<6;k++){
                long start = System.currentTimeMillis();

                int[] b = new int[5000009];
                for (int i = 0; i < 5000009; i++) {
                    b[i] = rnd.nextInt(100);
                }
                for (int i = 0; i < 5000009; i++) {
                    try {
                        QTable.Insert(new HashTableElement(100 * i + b[i], 5));

                    } catch (Exception e) {
                        System.out.println(i+ " insert");
                        System.out.println(QTable.Hash(100 * i + b[i],i));

                        System.out.println(e);
                    }
                }
                for (int i = 0; i < 5000009; i++) {
                    try {
                        QTable.Delete(100 * i + b[i]);

                    } catch (Exception e) {
                        System.out.println(i+ " delete");

                        System.out.println(e);
                    }
                }
                long finish = System.currentTimeMillis();

                System.out.println(finish - start);
                time+=finish - start;


            }







    }
}
