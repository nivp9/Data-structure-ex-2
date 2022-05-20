import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Experiments {

    public static void main(String[] args) throws Exception {
//        experiment1();
//        experiment2(QPHashTable.class);
//        experiment2(AQPHashTable.class);
        experiment3();
        experiment4();
//        experiment5();
    }

    public static void experiment1() {
        int q = 6571;
        long Q1 = IntStream.range(0, q)
                .map(i -> (i * i) % q)
                .distinct()
                .count();

        long Q2 = IntStream.range(0, q)
                .map(i -> ((int) Math.pow(-1, i) * (i * i)) % q)
                .distinct()
                .count();

        System.out.println(Q1 + " " + Q2);
    }

    public static void experiment2(Class<? extends OAHashTable> cls) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        System.out.println("Executing experiment 2 on class " + cls.getSimpleName());
        int iterations = 100;
        int m = 6571;
        int p = 1000000007;
        int totalNumOfExceptions = 0;
        for (int i = 0; i < iterations; i++) {
            AtomicInteger exceptionsCount = new AtomicInteger();
            IHashTable table = cls.getConstructor(int.class, long.class).newInstance(m, p);
            getSequence(m).forEach(element -> {
                try {
                    table.Insert(element);
                } catch (IHashTable.TableIsFullException | IHashTable.KeyAlreadyExistsException e) {
                    exceptionsCount.getAndIncrement();
                }
            });
            System.out.println("Number of exceptions: " + exceptionsCount);
            totalNumOfExceptions += exceptionsCount.get();
        }
        System.out.println("Total: " + totalNumOfExceptions + ", Average: " + (float) totalNumOfExceptions / 100);
    }

    public static void experiment3() throws Exception {
        System.out.println("\n------ experiment 3 ------");
        int m = 10000019;
        int p = 1000000007;
        int n = Math.floorDiv(m, 2);
        List<IHashTable> testedTables = Arrays.asList(new LPHashTable(m, p), new QPHashTable(m, p),
                new AQPHashTable(m, p), new DoubleHashTable(m, p));

        List<HashTableElement> sequence = getSequence(n);
        for (IHashTable table : testedTables) {
            insertToTable(table, sequence);
        }
    }

    public static void experiment4() throws Exception {
        System.out.println("\n------ experiment 4 ------");
        int m = 10000019;
        int p = 1000000007;
        int n = Math.floorDiv(19 * m, 20);
        List<IHashTable> testedTables = Arrays.asList(new LPHashTable(m, p), new AQPHashTable(m, p),
                new DoubleHashTable(m, p));

        List<HashTableElement> sequence = getSequence(n);
        for (IHashTable table : testedTables) {
            insertToTable(table, sequence);
        }
    }

    public static void experiment5() {
        System.out.println("\n------ experiment 5 ------");
        int m = 10000019;
        int p = 1000000007;
        int n = Math.floorDiv(m, 2);
        IHashTable table = new DoubleHashTable(m, p);

        int total = 0;
        for (int i = 0; i < 6; i++) {
            List<HashTableElement> randomSeq = getSequence(n);
            long startTime = Instant.now().toEpochMilli();
            randomSeq.forEach(element -> {
                try {
                    table.Insert(element);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            randomSeq.forEach(element -> {
                try {
                    table.Delete(element.GetKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            long endTime = Instant.now().toEpochMilli();
            long timeElapsed = endTime - startTime;
            System.out.println(i + " took " + timeElapsed);
            total += timeElapsed;
            if (i == 2 || i == 5) {
                System.out.println("Total: " + total);
                total = 0;
            }
        }
    }

    private static List<HashTableElement> getSequence(long end) {
        Random random = new Random();
        return LongStream.range(0, end)
                .map(l -> (100 * l + random.nextInt(100)))
                .mapToObj(l -> new HashTableElement(l, 0))
                .collect(Collectors.toList());
    }

    private static void insertToTable(IHashTable table, List<HashTableElement> sequence)
            throws Exception {

        long startTime = Instant.now().toEpochMilli();
        for (HashTableElement element : sequence) {
            table.Insert(element);
        }
        long endTime = Instant.now().toEpochMilli();
        long timeElapsed = endTime - startTime;
        System.out.println(table.getClass().getSimpleName() + " execution time (ms): " + timeElapsed);
    }
}
