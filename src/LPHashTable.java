import java.util.Random;

public class LPHashTable extends OAHashTable {
	private ModHash hashFunc;
	private long p;
	public LPHashTable(int m, long p) {
		super(m);
		this.p = p;
		hashFunc=ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (hashFunc.Hash(x)+i)%tableSize;
	}
	
}
