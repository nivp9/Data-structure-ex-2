import java.util.Random;

public class ModHash {
	private long a, b, p;
	private int m;
	private ModHash(long a,long b, long p, int m){
		this.a=a;
		this.b=b;
		this.p = p;
		this.m = m;
	}
	public static ModHash GetFunc(int m, long p){
		Random random=new Random();
		long a = random.nextLong(p)+1;
		long b = random.nextLong(p+1);
		return new ModHash(a,b,p,m);
	}
	
	public int Hash(long key) {
		return (int)((a*key +b)%p)%m;
	}
}
