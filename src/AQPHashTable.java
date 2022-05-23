import java.util.Random;

public class AQPHashTable extends OAHashTable {

	private ModHash hashFunc;
	private long p;
	public AQPHashTable(int m, long p) {
		super(m);
		this.p = p;
		hashFunc=ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		int sign = i%2==0 ? 1 : -1;
		int z= (hashFunc.Hash(x)+(int)((sign*(i*i))%tableSize + tableSize))%tableSize;
		if(z<0) {
			z += tableSize;
		}
		return z;
	}
}
