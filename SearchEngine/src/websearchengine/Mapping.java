package websearchengine;

import java.util.Comparator;

public class Mapping implements Comparator<Mapping>, Comparable<Mapping> {

	private int coun;
	private String f1;

	public int get_Counter() {
		return coun;
	}

	public void set_Counter(int count_val) {
		this.coun = count_val;
	}

	public String get_File() {
		return f1;
	}

	public void set_File(String file) {
		this.f1 = file;
	}

	public int compareTo(Mapping pm) {
		return (this.f1).compareTo(pm.f1);
	}

	public int compare(Mapping p, Mapping p1) {
		return (p.coun - p1.coun);

	}
}
