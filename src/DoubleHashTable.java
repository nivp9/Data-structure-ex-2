import java.util.Random;

public class DoubleHashTable extends OAHashTable {

	private ModHash hashFunc;
	private ModHash secondHashFunc;

	private long p;
	public DoubleHashTable(int m, long p) {
		super(m);
		this.p = p;
		hashFunc=ModHash.GetFunc(m,p);
		secondHashFunc=ModHash.GetFunc(m,p);
	}
	@Override
	public int Hash(long x, int i) {
		return (hashFunc.Hash(x)+ i*secondHashFunc.Hash(x))%tableSize;
	}
	
}
