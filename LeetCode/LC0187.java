//Repeated DNA Sequences
//All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T',
// for example: "ACGAATTCCG". When studying DNA,
// it is sometimes useful to identify repeated sequences within the DNA.
//
//Write a function to find all the 10-letter-long sequences (substrings)
// that occur more than once in a DNA molecule.

package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC0187 {
    public List<String> findRepeatedDnaSequences(String s) {
        //cc
        if (s == null || s.length() < 10) throw new IllegalArgumentException();

        List<String> res = new ArrayList<>();
        HashMap<Integer, Boolean> map = new HashMap<>();
        int window = 0;
        for (int i = 0; i < s.length(); i++) {
            int chBit = convertChar(s.charAt(i));
            window = (window << 2) & (0xfffff) | chBit;

            if (i < 9) continue; // skip for first 9

            Boolean status = map.get(window);
            if (status == null) {
                map.put(window, false);
            } else {
                if (!status) {
                    res.add(s.substring(i-9,i+1));
                    map.put(window, true);
                }
            }
        }
        return res;
    }

    private int convertChar(char ch) {
        if (ch == 'A') return 0;
        if (ch == 'C') return 1;
        if (ch == 'G') return 2;
        if (ch == 'T') return 3;
        throw new IllegalArgumentException("Invalid char exists");
    }
}
