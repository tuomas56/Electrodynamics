package electrodynamics.util;

import java.util.ArrayList;
import java.util.List;


public class StringUtil {

	public static List<String> splitStringAfterNChars(String string, int length) {
		ArrayList<String> strings = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String[] splitStrings = string.split(" ");
		
		for (int i=0; i<splitStrings.length; i++) {
			String splitString = splitStrings[i];
			int stringLength = splitString.length();
			
			if (sb.toString().length() + stringLength <= length) {
				sb.append(splitString);
				sb.append(" ");
				
				if (i == splitStrings.length - 1) {
					strings.add(sb.toString().trim());
				}
			} else {
				strings.add(sb.toString().trim());
				sb = new StringBuilder();
			}
		}
		
		return strings;
	}

}
