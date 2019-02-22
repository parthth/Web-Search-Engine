package websearchengine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class indexer {
	
	
	static JPanel j_panel=new JPanel();
	  static JLabel h=new JLabel("Find Word");
	  static JLabel j_label=new JLabel();
	  static JFrame j_frame=new JFrame("Find Word");
	  static JTextField jtextfield = new JTextField(30);
	  static JButton jbuttonSearchWeb = new JButton("Find Link");
	  static JButton jbuttonSearchDictionary = new JButton("find Synonym");
	  static JTextArea jtextarea=new JTextArea(10,20);
	  static JTextArea jtextarea2=new JTextArea(10,20);
	

		static Hashtable<String, String> hash = new Hashtable<>();

		@SuppressWarnings("resource")
		public indexer() throws IOException {
			BufferedReader buff = new BufferedReader(new FileReader("Dictionary.txt"));

			String line;

			while ((line = buff.readLine()) != null) {
				String a[] = line.split("=");
				hash.put(a[0].trim().toLowerCase(), a[1].trim());
			}
		}

		@SuppressWarnings("rawtypes")
		public static String editDistance(String word) {

			Set s1 = hash.entrySet();
			Iterator i1 = s1.iterator();
			int edit_distance = 0;
			int edit_distance2 = 5;
			String k = "";
			while (i1.hasNext()) {
				Map.Entry map_entry = (Map.Entry) i1.next();
				edit_distance = Edit_Dist.ed(word, map_entry.getKey().toString());
				if (edit_distance2 > edit_distance) {
					edit_distance2 = edit_distance;
					k = map_entry.getKey().toString();
				}
			}
			return k;
		}

		public void display_Same(String w) {
			jtextarea2.setText("");
			if (hash.containsKey(w)) {
				jtextarea2.setText("Synonym  of : ");
				jtextarea2.append(w);
				jtextarea2.append(" is : ");
				jtextarea2.append(hash.get(w));
			} else {
				String sugg = editDistance(w);
				jtextarea2.setText("Do you mean? ");
				jtextarea2.append(sugg);
				jtextarea2.append("\n");
				jtextarea2.append("Synonym of ");
				jtextarea2.append(sugg);
				jtextarea2.append(" is : ");
				jtextarea2.append(hash.get(sugg));
			}
			System.out.println();
			System.out.println();
			
		}
	

	static LinkedHashSet<String> hl = new LinkedHashSet<>();
	static ArrayList<String> c = new ArrayList<>();
	static TreeMap<String, Integer> wc = new TreeMap<String, Integer>();
	
	
	public static void main(String[] args) throws IOException, InterruptedException {

		
		Find_Text word_to_text = new Find_Text();
		Hashing_Dictionary dictionary_Custom = new Hashing_Dictionary();

		dictionary_Custom.convert();
		Hashtable<String, Value_Custom> hash_table = word_to_text.dictionary_find();

		  
		  j_frame.setVisible(true);
		  j_frame.setSize(2500, 2500);
		  j_frame.setResizable(false);
		  j_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		  j_panel.setLayout(null);
		  h.setBounds(650, 125, 750, 130);
		  h.setFont(new Font("Times New Roman", Font.BOLD, 45));
		  h.setForeground(new Color(94,100,117));
		  j_panel.add(h);
		  jtextfield.setBounds(205,245,1100,25);
		  j_panel.add(jtextfield); 
		  
		  j_panel.setBackground(new Color(255, 255, 255));
		  jtextfield.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String inp=jtextfield.getText();
				indexer i_i = null;
				try {
					i_i = new indexer();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				try {
					i_i.get(hash_table, inp);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		  jbuttonSearchWeb.setBounds(570, 300, 150, 25);
		  jbuttonSearchWeb.setForeground(new Color(255,255,255));
		  jbuttonSearchWeb.setBackground(new Color(94,100,102));
		  j_panel.add(jbuttonSearchWeb);
		  
		  jbuttonSearchWeb.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  String inp=jtextfield.getText();
				  indexer i_i = null;
				try {
					i_i = new indexer();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
					try {
						i_i.get(hash_table, inp);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			  }
		  });
		  
		  jbuttonSearchDictionary.setBounds(800, 300, 150, 25);
		  jbuttonSearchDictionary.setForeground(new Color(255,255,255));
		  jbuttonSearchDictionary.setBackground(new Color(94,100,102));
		  j_panel.add(jbuttonSearchDictionary);
		  
		  jbuttonSearchDictionary.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  String inp=jtextfield.getText();
				  indexer i_i1 = null;
				try {
					i_i1 = new indexer();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
					i_i1.display_Same(inp);
			  }
		  });
		  
		  j_frame.add(j_panel);
		  j_panel.add(j_label);
		  jtextarea2.setBounds(800,340,600,300);
		  jtextarea.setBounds(125,340,600,300);
		  
		  jtextarea2.setBackground(new Color(94,100,102));
		  jtextarea.setBackground(new Color(94,100,102));

		  jtextarea2.setFont(new Font("Arial", Font.BOLD, 15));
		  jtextarea.setFont(new Font("Arial", Font.BOLD, 15));
		  jtextarea2.setForeground(new Color(255, 255, 255));
		  jtextarea.setForeground(new Color(255, 255, 255));
		  j_panel.add(jtextarea2);
		  j_panel.add(jtextarea);
  }
	
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
		jtextarea.setText("");
		jtextarea.setText("Showing Results of:   ");
		for (String Value_Custom : corr) {
			String string1=Value_Custom;
			jtextarea.append(" "+string1);
			
		}
		jtextarea.append("\n");
		jtextarea.append("\nResults Found:\n");
		ArrayList<String> wordCount1 = Comparator(wc);
		for (String Value_Custom1 : wordCount1) {
			jtextarea.append(Value_Custom1.replace(".txt", ".html"));
			jtextarea.append("\n");
		}
		
		jtextarea.append("\n");
		j_frame.add(j_label);
		

	}

	private static void calculate_page(String in, Hashtable<String, Value_Custom> hash_table) {
		Value_Custom Value_Custom = hash_table.get(in);
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

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

		ArrayList<String> disp = new ArrayList<>();
		for (Iterator<Map.Entry<String, Integer>> it = list1.iterator(); it
				.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
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

