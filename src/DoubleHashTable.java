import java.util.Random;

public class DoubleHashTable extends OAHashTable {

	private ModHash hashFunc;
	private ModHash secondHashFunc;

	private long p;
	public DoubleHashTable(int m, long p) {
		super(m);
		this.p = p;
		hashFunc=ModHash.GetFunc(m,p);
		secondHashFunc=ModHash.GetFunc(m-1,p);
	}
	@Override
	public int Hash(long x, int i) {
		int z= (hashFunc.Hash(x)+ (i)*((secondHashFunc.Hash(x)+1)))%tableSize;
		if(z<0) {
			z += tableSize;
		}
		return z;
	}
	
}
