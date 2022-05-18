import java.util.List;

public class TheoreticalPart {
    public static void main(String[] args) {
        testFillup();
        System.out.println("test: random stress");
        for (int i = 0; i < 100; i++) {
            List<IHashTable> hashTables = allHashTables(15, 97);
            for (IHashTable ht : hashTables) {
                if (!testRandomStress(ht)) {
                    System.out.println("in test case " + ht.getClass().getName());
                    break;
                }
            }
        }
    }
}
