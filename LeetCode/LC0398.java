// Random Pick Index
// Given an array of integers with possible duplicates, randomly output the index of a given target number.
// You can assume that the given target number must exist in the array.
//
//Note:
//The array size can be very large. Solution that uses too much extra space will not pass the judge.
//

package LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LC0398 {
    private List<Integer> list;
    private Random rd;
    public LC0398 (int[] nums) {
        this.rd = new Random();
        this.list = new LinkedList<>();
        for (int n : nums) {
            list.add(n);
        }
    }

    public int pick(int target) {
        int count = 0;
        int retIdx = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {
                if (count == 0) {
                    retIdx = i;
                } else {
                    int rndNum = rd.nextInt(count+1);
                    if (rndNum == 0) {
                        retIdx = i;
                    }
                }
                count ++;
            }
        }
        return retIdx;
    }
}
