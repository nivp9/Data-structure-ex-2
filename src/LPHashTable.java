import java.util.Random;

public class LPHashTable extends OAHashTable {
	private ModHash hashFunc;
	public LPHashTable(int m, long p) {
		super(m);
		hashFunc=ModHash.GetFunc(m,p);
		// TODO Complete hash table constructor.
	}
	
	@Override
	public int Hash(long x, int i) {
		return (hashFunc.Hash(x)+i)%tableSize;
	}
	
}
