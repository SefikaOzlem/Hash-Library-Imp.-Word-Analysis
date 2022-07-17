
import java.util.HashMap;

public class HashEntry<K,V> {
	private String key;
	public int documentsNum;
	private HashMap<String, Integer> value;
	HashEntry(String key,HashMap<String, Integer> value)
	{
		this.documentsNum=0;
		this.key=key;
		this.value=value;
		
	}
	public int getDocumentsNum() {
		return documentsNum;
	}
	public void setDocumentsNum(int documentsNum) {
		this.documentsNum = documentsNum;
	}
	public String getKey() {
		return key;
	}

	public HashMap<String, Integer> getValue() {
		return value;
	}
	
}
