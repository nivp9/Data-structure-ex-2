
public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	protected int tableSize;
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		tableSize=m;
		// TODO add to constructor as needed
	}

	private int findIndex(long key){
		for(int i = 0; i<tableSize; i++){
			int j = Hash(key,i);
			if(table[j] != null && table[j].GetKey()==key){
				return j;
			}
			if(table[j] == null) {
				return -1;
			}
		}
		return -1;
	}
	
	@Override
	public HashTableElement Find(long key) {
		int res = findIndex(key);
		return res == -1 ? null : table[res];
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		long key = hte.GetKey();
		Integer insertionIndex=null;
		for(int i = 0; i<tableSize; i++){
			int j = Hash(key,i);
			if(table[j] == null){
				table[insertionIndex == null ? j:insertionIndex]=hte;
				return;
			}
			if(table[j].GetKey() == -1){
				insertionIndex=j;
			}
			else if(table[j].GetKey()==key){
				throw new KeyAlreadyExistsException(hte);    // todo: return hte or table[j]?
			}
		}
		if(insertionIndex!=null){
			table[insertionIndex]=hte;
			return;
		}
		throw new TableIsFullException(hte);
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int res = findIndex(key);
		if (res == -1)
			throw new KeyDoesntExistException(key);
		table[res] = new HashTableElement(-1,0);
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
