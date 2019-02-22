package websearchengine;

public class Edit_Dist {

	public static int ed(String word1, String word2) {
		int line1 = word1.length();
		int line2 = word2.length();

		int[][] a = new int[line1 + 1][line2 + 1];

		for (int i = 0; i <= line1; i++) {
			a[i][0] = i;
		}

		for (int j = 0; j <= line2; j++) {
			a[0][j] = j;
		}

		
		for (int i = 0; i < line1; i++) {
			char char1 = word1.charAt(i);
			for (int j = 0; j < line2; j++) {
				char char2 = word2.charAt(j);

				
				if (char1 == char2) {
					
					a[i + 1][j + 1] = a[i][j];
				} else {
					int replace = a[i][j] + 1;
					int insert = a[i][j + 1] + 1;
					int delete = a[i + 1][j] + 1;

					int minimum = replace > insert ? insert : replace;
					minimum = delete > minimum ? minimum : delete;
					a[i + 1][j+1] = minimum;
				}
			}
		}

		return a[line1][line2];
	}

}
