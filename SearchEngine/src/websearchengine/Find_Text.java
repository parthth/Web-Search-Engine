package websearchengine;
import java.io.*;
import java.util.*;

public class Find_Text {

	public Hashtable<String, Value_Custom> dictionary_find() throws IOException {

		
		ArrayList<String> array_list = new ArrayList<>();
		String l;
		ArrayList<Value_Custom> array_list_value = new ArrayList<>();

		Hashtable<String, Value_Custom> hash_table = new Hashtable<String, Value_Custom>();

		File path = new File("C:\\Users\\WORM\\Desktop\\SearchEngine\\SearchEngine\\W3C Web Pages\\ConvertedTextFiles\\");

		for (File f : path.listFiles()) {

			BufferedReader buff = new BufferedReader(new FileReader(f));

			while ((l = buff.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(l,
						",.:;<>/?[)({}]+-*&%=#@^'!|_$`~");

				while (st.hasMoreTokens()) {

					String string1 = st.nextToken().toLowerCase().replaceAll("\"","");
					String string2[] = string1.split(" ");

					for (int i = 0; i < string2.length; i++) {

						if (!hash_table.containsKey(string2[i])) {
							Value_Custom Value_Custom = new Value_Custom();
							ArrayList<String> array_List = new ArrayList<String>();
							int not = 1;
							array_List.add(f.getName());
							Value_Custom.set_Number_Of_Times(not);
							Value_Custom.set_Page_Name(array_List);
							hash_table.put(string2[i], Value_Custom);
						} else {

							Value_Custom Value_Custom = hash_table.get(string2[i]);
							ArrayList<String> array_List = Value_Custom
									.getPageName();
							if (!array_List.contains(f.getName()))
								array_List.add(f.getName());

							Value_Custom.set_Number_Of_Times(Value_Custom
									.get_Number_Of_Times() + 1);
							Value_Custom.set_Page_Name(array_List);
							hash_table.put(string2[i], Value_Custom);
						}
					}
				}
			}
		}

		return hash_table;
	}

	

}
