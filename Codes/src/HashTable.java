
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class HashTable<K, V> {
	public static int TABLE_SIZE = 128;
	private static int DoubleTableSýze = pr(TABLE_SIZE);
	private static int oldtablesize = 0;
	public static double capacity = 0;
	private static int tempcapacity = 0;
	private static int loc = 0;
	private static int v = 0;
	private static int w = 0;
	private static int f = 1;
	public static ArrayList<String> mainkey = new ArrayList<>();
	private static int primenumber = 7;
	private static int word = 0;
	private static double loadfactor = 0.5;
	private static int[] temp = new int[TABLE_SIZE];
	private static int[] oldtemp = new int[TABLE_SIZE];
	private static int[] remtep = new int[TABLE_SIZE];
	public static HashEntry[] table;
	public static HashEntry[] oldtable;
	public static int oldcapacity = 0;
	private static HashEntry[] remtable = new HashEntry[TABLE_SIZE];;
	private static HashEntry[] temptable;
	private static HashEntry[] maptemp;
	private static HashMap<String, Integer> map = new HashMap();
	public static HashMap<String, Integer> hm = new HashMap();
	private static int r = 0;
	private static int sum = 0;
	private static String alphabet = " abcdefghijklmnopqrstuvwxyz";
	private static char[] alph_char = alphabet.toCharArray();
	private static int count = 1;
	public static int linearcollisioncount=0;
	public static int doublecollisioncount=0;
	private static int size = 0;
	private static int index = 0;
	private static int newindex = 0;
	private static HashEntry tempp;
	private static HashMap mapf;
	private static String a;
	private static char[] char_a;
	private static String temp_a;
	private static String[] t_a;
	private static String main_a;
	private static String b;
	String temp_b;
	char[] char_b;
	int c = 0;
	int t = 0;

	public HashTable() {
		temptable = new HashEntry[TABLE_SIZE];
		for (int i = 0; i <TABLE_SIZE; i++) {
			temptable[i] = null;
		}
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = null;
		}
		maptemp = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			maptemp[i] = null;
		}

	}

	public static int ssf(String s) {

		char[] ss = s.toCharArray();
		sum = 0;
		for (int k = 0; k < ss.length; k++) {
			for (int m = 0; m < alph_char.length; m++) {
				if (alph_char[m] == ss[k]) {
					sum += m * (k + 1);
				}
				if (ss[k] == 'ý' && alph_char[m] == 'i') {
					sum += m * (k + 1);
				}
			}
		}
		return sum;
	}

	public static int paf(String s) {
		count = 1;
		size = s.length();
		char[] pp = s.toCharArray();
		sum = 0;
		for (int j = 0; j < pp.length; j++) {
			for (int a = 0; a < alph_char.length; a++) {
				if (alph_char[a] == pp[j]) {
					sum += (a * (Math.pow(37, size - count)) % TABLE_SIZE);
					count++;
				}
				if (alph_char[a] == 'i' && pp[j] == 'ý') {
					sum += (a * (Math.pow(37, size - count)) % TABLE_SIZE);
					count++;
				}

			}

		}
		return sum;
	}

	public int HashLinearFunctýon(String key) {
		word = ssf(key);
		index = (word % TABLE_SIZE);
		return index;
	}

	public int LinearCollision(int index, String key) {
		newindex = 0;
		for (int i = 0; i < TABLE_SIZE; i++) {
			index = (index + 1) % TABLE_SIZE;

			if (table[index] != null && table[index].getKey().equals(key))
			{
				newindex = index;
				linearcollisioncount++;
				break;
			} 
			else if (table[index] == null) 
			{
				newindex = index;
				linearcollisioncount++;
				break;
			}
		}
		
		return newindex;
	}

	public int HashDoubleFunction(String key) {
		word = ssf(key);
		index = (word % DoubleTableSýze);
		return index;

	}

	public int DoubleCollision(int index,String key) {

		newindex = 0;
		int oldindex = index;
		for (int j = 0; j < DoubleTableSýze; j++) 
		{

			index = (oldindex + j * (primenumber - (word % primenumber))) % DoubleTableSýze;
			if (table[index] != null && table[index].getKey().equals(key))
			{
				newindex = index;
				doublecollisioncount++;
				break;
			} 
			else if (table[index] == null) 
			{
				newindex = index;
				doublecollisioncount++;
				break;
			}

		}
		
		return newindex;
	}

	public void put(String key, HashMap<String, Integer> value) {

		loc = HashLinearFunctýon(key);
		if (loc == 0) {
			loc = loc + 1;
		}
		for (int i = 0; i < temp.length; i++) {

			if (table[loc] == null) {
				capacity++;
				tempcapacity++;
				map = new HashMap();
				test.count = 1;
				table[loc] = new HashEntry(key, map);
				map.put(value.keySet().toString(), test.count);
				for (int d = v; d < capacity; d++) {
					temp[d] = loc;
				}
				v++;
				break;

			} else if (table[loc] != null && table[loc].getKey().equalsIgnoreCase(key)) {

				b = value.keySet().toString();
				char_b = b.toCharArray();
				temp_b = "";
				for (int bb = 0; bb < char_b.length; bb++) {
					if (char_b[bb] == '[' || char_b[bb] == ']') {
						char_b[bb] = ' ';
					} else
						temp_b += char_b[bb];
				}
				temp_b.trim();
				tempp = table[loc];
				mapf = tempp.getValue();
				a = mapf.keySet().toString();
				char_a = a.toCharArray();
				temp_a = "";
				for (int dd = 0; dd < char_a.length; dd++) {
					if (char_a[dd] == '[' || char_a[dd] == ']') {
						char_a[dd] = ' ';
					} else
						temp_a += char_a[dd];
				}
				temp_a.trim();
				t_a = temp_a.split(",");
				main_a = "";
				for (int zz = 0; zz < t_a.length; zz++) {
					if (temp_a.contains(temp_b)) {
						if (t_a[zz].trim().equalsIgnoreCase(temp_b)) {
							c = (int) table[loc].getValue().get(b) + 1;
							test.count = c;
							mapf.replace(b, test.count);
						}
					} else if (!temp_a.contains(temp_b)) {
						t = 1;
						mapf.put(value.keySet().toString(), t);
						table[loc].setDocumentsNum(table[loc].getDocumentsNum()+1);
						break;
					}

				}
				break;

			} else if (table[loc] != null && !table[loc].getKey().equalsIgnoreCase(key)) {
				loc = LinearCollision(loc, key);
				if(table[loc]!=null && table[loc].getKey().equalsIgnoreCase(key) )
				{
					b = value.keySet().toString();
					char_b = b.toCharArray();
					temp_b = "";
					for (int bb = 0; bb < char_b.length; bb++) {
						if (char_b[bb] == '[' || char_b[bb] == ']') {
							char_b[bb] = ' ';
						} else
							temp_b += char_b[bb];
					}
					temp_b.trim();
					tempp = table[loc];
					mapf = tempp.getValue();
					a = mapf.keySet().toString();
					char_a = a.toCharArray();
					temp_a = "";
					for (int dd = 0; dd < char_a.length; dd++) {
						if (char_a[dd] == '[' || char_a[dd] == ']') {
							char_a[dd] = ' ';
						} else
							temp_a += char_a[dd];
					}
					temp_a.trim();
					t_a = temp_a.split(",");
					main_a = "";
					for (int zz = 0; zz < t_a.length; zz++) {
						if (temp_a.contains(temp_b)) {
							if (t_a[zz].trim().equalsIgnoreCase(temp_b)) {
								c = (int) table[loc].getValue().get(b) + 1;
								test.count = c;
								mapf.replace(b, test.count);
							}
						} else if (!temp_a.contains(temp_b)) {
							t = 1;
							table[loc].setDocumentsNum(table[loc].getDocumentsNum()+1);
							mapf.put(value.keySet().toString(), t);
							break;
						}

					}
					break;
				}
				else  {
					capacity++;
					tempcapacity++;
					map = new HashMap();
					test.count = 1;
					table[loc] = new HashEntry(key, map);
					map.put(value.keySet().toString(), test.count);
					for (int d = v; d < capacity; d++) {
						temp[d] = loc;
					}
					v++;
					break;
				}
			}

		}
		if (capacity >=TABLE_SIZE* loadfactor) {

			resize();
			for (int h = 0; h < oldtemp.length; h++) {
				if (oldtable[oldtemp[h]] != null) {
					loc = HashLinearFunctýon(oldtable[oldtemp[h]].getKey());
					if (loc == 0) {
						loc = loc + 1;
					}
					if (table[loc] == null) {
						tempcapacity++;
						HashMap<String, Integer> rt = new HashMap();
						rt = oldtable[oldtemp[h]].getValue();
						table[loc] = new HashEntry(oldtable[oldtemp[h]].getKey(), rt);
						for (int df = v; df < capacity; df++) {
							temp[df] = loc;
						}
						v++;

					} else if (table[loc] != null) {
						loc = LinearCollision(loc, key);
						tempcapacity++;
						HashMap<String, Integer> cl = new HashMap();
						cl = oldtable[oldtemp[h]].getValue();
						int docNum=oldtable[oldtemp[h]].getDocumentsNum();
						table[loc] = new HashEntry(oldtable[oldtemp[h]].getKey(), cl);
						table[loc].setDocumentsNum(docNum+1);
						for (int gf = v; gf < capacity; gf++) {
							temp[gf] = loc;
						}
						v++;

					}
				} else if (oldtable[oldtemp[h]] == null)
					break;
			}
		}
		for (int uu = 0; uu < oldtablesize; uu++) {
			oldtable[uu] = null;
		}
		oldtemp = new int[TABLE_SIZE];

	}

	public static void resize() {
		//System.out.println(TABLE_SIZE);
		oldtable = new HashEntry[TABLE_SIZE];
		oldtablesize = TABLE_SIZE;
		TABLE_SIZE= pr(2 * TABLE_SIZE);
		for (int i = 0; i < oldtablesize; i++) {
			if (table[i] != null) {
				HashEntry restemp = table[i];
				HashMap maps = restemp.getValue();
				oldtable[i] = new HashEntry(table[i].getKey(), maps);
				oldcapacity++;
				for (int y = w; y < oldcapacity; y++) {
					oldtemp[y] = i;
				}
				w++;
			} else if (table[i] == null)
				continue;
		}

		for (int i = 0; i < oldtablesize; i++) {

			table[i] = null;
		}

		tempcapacity = 0;
		v = 0;
		f = 1;
		table = new HashEntry[TABLE_SIZE];
		temptable = new HashEntry[TABLE_SIZE];
		temp = new int[TABLE_SIZE];
		remtable = new HashEntry[TABLE_SIZE];
		remtep = new int[TABLE_SIZE];
		oldcapacity = 0;
		w = 0;
		// oldtemp=new int[TABLE_SIZE];
	}

	public static String getKey(int i) {

		return table[temp[i]].getKey();

	}

	public HashMap<String, Integer> getVal(String key) {
		int w = 0;
		int y = 1;
		for (int i = 0; i < remtable.length; i++) {
			if (remtable[i] != null) {
				for (int j = w; j < y; j++) {
					remtep[j] = i;
				}
				w++;
				y++;
			}
		}
		HashMap<String, Integer> z = new HashMap<String, Integer>();
		for (int i = 0; i < TABLE_SIZE; i++) {
			for (int j = 0; j < TABLE_SIZE; j++) {
				if (table[temp[j]] != null && table[temp[j]].getKey().equalsIgnoreCase(key)) {
					z = table[temp[j]].getValue();
					//System.out.println(table[temp[j]].getDocumentsNum());
					break;
				} else if (table[temp[j]] == null && remtable[remtep[j]] != null
						&& remtable[remtep[j]].getKey() == key) {

					z = null;
					break;
				} else if (table[temp[j]] == null && remtable[temp[j]].getKey() != key) {
					for (int x = 0; x < remtep.length; x++) {
						if (table[temp[j]] == null && remtable[remtep[x]] != null
								&& remtable[remtep[x]].getKey() == key) {

							z = null;
							break;
						} else if (table[temp[j]] == null && remtable[remtep[x]] != null
								&& remtable[remtep[x]].getKey() != key) {
							continue;
						}
						break;
					}

				} else if (table[temp[j]] == null && remtable[temp[j]].getKey() == key) {

					z = null;
					break;
				} else if (table[temp[j]] != null && table[temp[j]].getKey() != key)
					continue;

			}

			break;
		}
		return z;

	}

	public void remove(String key) {
		for (int i = 0; i <TABLE_SIZE; i++) {
			if (table[i] != null) {
				for (int k = r; k < r + 1; k++) {
					temptable[k] = new HashEntry(table[i].getKey(), table[i].getValue());
				}
				r++;
			}
		}
		r = 0;
		for (int i = 0; i <TABLE_SIZE; i++) {
			if (table[i] != null && table[i].getKey() == key) {
				for (int j = r; j < r + 1; j++) {
					if (temptable[j] != null && temptable[j].getKey() == key) {
						remtable[i] = new HashEntry(temptable[j].getKey(), temptable[j].getValue());
						r++;
					} else if (temptable[j] != null && temptable[j].getKey() != key) {
						r++;
						continue;
					}
				}
				table[i] = null;
			}
		}
		r = 0;
		for (int i = 0; i < remtep.length; i++) {
			remtep[i] = 0;
		}
		for (int h = 0; h < mainkey.size(); h++) {
			if (mainkey.get(h) == key) {
				mainkey.remove(h);
			} else if (mainkey.get(h) != key) {
				continue;
			}

		}
		capacity--;
	}

	public static int pr(int a) {
		for (int i = 1; i < 10; i++) {
			for (int j = 2; j < a; j++) {
				if (a % j == 0) {
					a += 1;
					break;
				}
			}
		}
		return a;
	}

	public static int size() {
		return (int) capacity;
	}

}
