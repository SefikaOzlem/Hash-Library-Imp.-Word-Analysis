import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class test {
	static Scanner scan=new Scanner(System.in);
	public static ArrayList<String>doc=new ArrayList<String>();		
	public static ArrayList<String>searchword=new ArrayList<String>();	
	public static HashTable<String,HashMap<String,Integer>>m=new HashTable();
	public static HashMap<String,Integer>hm=new HashMap<String,Integer>();
	public static List<String>foldtext=new ArrayList<>();
	public static int count;
	public static int u=0;
	public static BufferedReader reader;
	public static void main(String[] args) throws IOException {

		List<String> files = new ArrayList<>();
		List<String> directories = new ArrayList<>();
		List<String>text=new ArrayList<>();
		List<String>foldname=new ArrayList<>();
		List<String>textname=new ArrayList<>();
		List<String>textf=new ArrayList<>();

		File folder = new File("bbc");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				directories.add(listOfFiles[i].getName());
			}
		}
		File subfold;
		File[] listtext = null;
		int z=0;
		int r=0;
		for(int i=0;i<listOfFiles.length;i++)
		{
			subfold=new File(listOfFiles[i].getPath());
			listtext=subfold.listFiles();
			foldname.add(listOfFiles[i].getName());
			for(int j=0;j<listtext.length;j++)
			{
				text.add(listtext[j].getPath());
				textf.add(listtext[j].getName());

				for(int g=z;g<z+1;g++)
				{
					for(int p=r;p<r+1;p++)
					{
						foldtext.add(foldname.get(i)+textf.get(p));
					}
					r++;
				}
				z++;
			}
		}
		String DELIMITERS = "[-+=" +
				" " +        //space
				"\r\n " +    //carriage return line fit
				"1234567890" + //numbers
				"’'\"" +       // apostrophe
				"(){}<>\\[\\]" + // brackets
				":" +        // colon
				"," +        // comma
				"‒–—―" +     // dashes
				"…" +        // ellipsis
				"!" +        // exclamation mark
				"." +        // full stop/period
				"«»" +       // guillemets
				"-‐" +       // hyphen
				"?" +        // question mark
				"‘’“”" +     // quotation marks
				";" +        // semicolon
				"/" +        // slash/stroke
				"⁄" +        // solidus
				"␠" +        // space?   
				"·" +        // interpunct
				"&" +        // ampersand
				"@" +        // at sign
				"*" +        // asterisk
				"\\" +       // backslash
				"•" +        // bullet
				"^" +        // caret
				"¤¢$€£¥₩₪" + // currency
				"†‡" +       // dagger
				"°" +        // degree
				"¡" +        // inverted exclamation point
				"¿" +        // inverted question mark
				"¬" +        // negation
				"#" +        // number sign (hashtag)
				"№" +        // numero sign ()
				"%‰‱" +      // percent and related signs
				"¶" +        // pilcrow
				"′" +        // prime
				"§" +        // section sign
				"~" +        // tilde/swung dash
				"¨" +        // umlaut/diaeresis
				"_" +        // underscore/understrike
				"|¦" +       // vertical/pipe/broken bar
				"⁂" +        // asterism
				"☞" +        // index/fist
				"∴" +        // therefore sign
				"‽" +        // interrobang
				"※" +          // reference mark
				"]";

		ArrayList<String>docw=new ArrayList<String>();
		double puttime=0;
        Time.start();
		for(int i=u;i<text.size();i++) 
		{
			reader = new BufferedReader(new FileReader(text.get(i)));
			String line;
			while ((line = reader.readLine()) != null) 
			{
				if (line.isEmpty())
				{
					line=line.trim();
					continue;
				}

				String[] parts = line.split(DELIMITERS);

				for(int j=0;j<parts.length;j++)
				{
					if(parts[j].isEmpty())
					{
						parts[j]=parts[j].trim();
						continue;
					}
					else 
					{
						doc.add(parts[j].toLowerCase());
					}
				}       
			}
			reader.close();
			BufferedReader stopw = new BufferedReader(new FileReader("stop_words_en.txt"));
			while(true)
			{
				String linew=stopw.readLine();
				if(linew==null)
				{
					break;
				}
				String[] parts = linew.split("\n");
				for(int f=0;f<parts.length;f++)
				{
					docw.add(parts[f].toLowerCase());
				}
			}
			stopw.close(); 
			for(int t=0;t<docw.size();t++)
			{		
				for(int j=0;j<doc.size();j++)
				{
					if(doc.get(j).equalsIgnoreCase(docw.get(t)))
					{
						doc.remove(j);
					}
				}

			}	

			hm.put(foldtext.get(i),count);
			for(int k=0;k<doc.size();k++)
			{
				m.put(doc.get(k).toLowerCase(), hm);
			}

			doc.clear();
			docw.clear();
			hm.clear();
			u++;	
			if(u==text.size())
				break;
		}
       Time.stop();
		puttime=Time.getElapsedSeconds();
		
		BufferedReader searchw = new BufferedReader(new FileReader("1000.txt"));
		{
			while(true)
			{
				String src=searchw.readLine();
				if(src==null)
				{
					break;
				}
				String[] partswords = src.split("\n");
				for(int f=0;f<partswords.length;f++)
				{
					searchword.add(partswords[f].toLowerCase());
				}
			}
			searchw.close(); 
		}

		
		double time=0;
        double mintime=99999;
        double maxtime=0;
        double avaragetime=0;
		for(int ss=0;ss<searchword.size();ss++)
		{
			try {
				if(m.getVal(searchword.get(ss)).isEmpty())
				{
					System.out.println(searchword.get(ss)+"==>"+"Word is Not Found");
					continue;
				}
				else if(!m.getVal(searchword.get(ss)).isEmpty()) {
					Time.start();
					System.out.println(searchword.get(ss)+"==>"+m.getVal(searchword.get(ss)));
					Time.stop();
					time=Time.getElapsedMilliseconds();
					//System.out.println(time);
					avaragetime+=time;
					if(time<mintime)
					{
						mintime=time;
					}
					if(time>maxtime)
					{
						maxtime=time;
					}
					
				}
				
			}catch(NullPointerException e)
			{
				System.out.println(searchword.get(ss)+" : "+" Word is Not Found");
			}
		}
		System.out.println("Indexing Time: "+puttime);
		System.out.println("Minimum time :"+mintime);
		System.out.println("Maximum time :"+maxtime);
		System.out.println("Avarage time :"+avaragetime/1000);
		System.out.println(m.size());
		System.out.println(HashTable.linearcollisioncount);
		
		/*String g=scan.nextLine();
		try {
		for(int ff=0;ff<m.size();ff++)
		{
			if(m.getKey(ff).equalsIgnoreCase(g))
			{
				m.remove(g);
				break;
			}
			else
				continue;
		}
		}catch(NullPointerException e)
		{
			System.out.println("The word you want to delete");
		}*/
		String h;
		
		while(true)
		{
			try {
				h=scan.nextLine();
				h.toLowerCase();
				if(m.getVal(h).isEmpty())
					System.out.println("not found");
				else if(!m.getVal(h).isEmpty())
					System.out.println(m.getVal(h));
			}catch(NullPointerException a)
			{
				System.out.println("word is not found");
			}
		}
		
		




	}
}

