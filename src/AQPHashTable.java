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
		int z= (hashFunc.Hash(x)+(int)((Math.pow(-1, i)*Math.pow(i,2))%tableSize + tableSize))%tableSize;
		if(z<0) {
			z += tableSize;
		}
		return z;
	}
}
