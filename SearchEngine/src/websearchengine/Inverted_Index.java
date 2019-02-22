package websearchengine;
import java.io.*;
import java.util.*;


public class Inverted_Index {

	static LinkedHashSet<String> hl = new LinkedHashSet<>();
	static ArrayList<String> c = new ArrayList<>();
	static TreeMap<String, Integer> wc = new TreeMap<String, Integer>();

	@SuppressWarnings("rawtypes")
	public void get(Hashtable<String, Value_Custom> hash_table, String input) throws IOException {

		ArrayList<String> corr = new ArrayList<>();
		ArrayList<String> array_list = new ArrayList<String>();
		
		String inp[] = input.split(" ");
		for (int i = 0; i < inp.length; i++)
			array_list.add(inp[i]);

		for (String in : array_list) {

			if (hash_table.containsKey(in)) {
				corr.add(in);
				calculate_page(in, hash_table);
			} else {

				Set s1 = hash_table.entrySet();
			
				Iterator i1 = s1.iterator();
				
				int edit_distance = 101;
				int edit_distance2 = 101;
				String K = "";
				while (i1.hasNext()) {
					Map.Entry map_entry = (Map.Entry) i1.next();

					edit_distance = Edit_Dist.ed(in, map_entry.getKey().toString());
					if (edit_distance2 > edit_distance) {
						edit_distance2 = edit_distance;
						K = map_entry.getKey().toString();
					}

				}
				corr.add(K);
				calculate_page(K, hash_table);

			}
		}
		
		
		System.out.print("Suggestions Found :");
		System.out.print("\"");
		for (String Value_Custom : corr) {
			System.out.print(Value_Custom + " ");
		}
		System.out.print("\"");
		System.out.println();
		System.out.println();
		ArrayList<String> wordCount1 = Comparator(wc);
		System.out.println("Links Found: ");
		for (String Value_Custom1 : wordCount1) {
			System.out.println(Value_Custom1.replace(".txt", ".html"));
		}
		System.out.println();
		

	}

	private static void calculate_page(String in, Hashtable<String, Value_Custom> ht) {
		Value_Custom Value_Custom = ht.get(in);
		int x = 0;

		ArrayList<Mapping> array_list = new ArrayList<>();

		Mapping pm = new Mapping();
		for (String page_name : Value_Custom.getPageName()) {

			if (x > 5) {

				break;
			} else {
				if (wc.containsKey(page_name)) {
					int c = wc.get(page_name);
					c += String_Count(in, page_name);
					wc.put(page_name, c);
				} else
					wc.put(page_name, String_Count(in, page_name));
			}

			x++;
		}
		System.out.println();

	}

	private static ArrayList<String> Comparator(Map<String, Integer> myMap) {

		List<Map.Entry<String, Integer>> list1 = new LinkedList<Map.Entry<String, Integer>>(
				myMap.entrySet());

		Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<String, Integer> sorted_Map = new LinkedHashMap<String, Integer>();

		ArrayList<String> disp = new ArrayList<>();
		for (Iterator<Map.Entry<String, Integer>> it = list1.iterator(); it
				.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sorted_Map.put(entry.getKey(), entry.getValue());
			disp.add(entry.getKey());
		}
		Collections.reverse(disp);

		return disp;
	}

	public static int String_Count(String stringToLookFor, String fileName) {
		int count = 0;
		try {

			FileInputStream input_stream = new FileInputStream("C:\\Users\\WORM\\Desktop\\SearchEngine\\SearchEngine\\W3C Web Pages\\ConvertedTextFiles\\" + fileName);
			DataInputStream in = new DataInputStream(input_stream);
			BufferedReader buff = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = buff.readLine()) != null) {
				int start_Index = line.toLowerCase().indexOf(stringToLookFor);

				while (start_Index != -1) {

					count++;
					start_Index = line.indexOf(stringToLookFor, start_Index
							+ stringToLookFor.length());
				}
			}

			in.close();
		} catch (Exception e) {
			System.err.println("Error is Found: " + e.getMessage());
		}
		return count;
	}

}

