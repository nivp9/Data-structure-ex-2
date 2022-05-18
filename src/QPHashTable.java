import java.util.Random;

public class QPHashTable extends OAHashTable {
	private ModHash hashFunc;
	private long p;
	public QPHashTable(int m, long p) {
		super(m);
		this.p = p;
		hashFunc=ModHash.GetFunc(m,p);
	}
	@Override
	public int Hash(long x, int i) {
		return (hashFunc.Hash(x) + (int)Math.pow(i,2))%tableSize;
	}
}
