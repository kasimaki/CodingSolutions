// zigzag as snake
package LeetCode;

import java.util.*;

public class LC0281F {
}

class ZigzagIteratorF implements Iterator<Integer> {
    private Stack<Iterator<Integer>> leftStack;
    private Stack<Iterator<Integer>> rightStack;
    private boolean toLeft;

    public ZigzagIteratorF(List<Integer> v1, List<Integer> v2) {
        this.leftStack = new Stack<>();
        this.rightStack = new Stack<>();
        if (v2.iterator().hasNext()) rightStack.push(v2.iterator());
        if (v1.iterator().hasNext()) rightStack.push(v1.iterator());
        this.toLeft = true;
    }

    @Override
    public Integer next() {
        Integer ret = null;
        Iterator<Integer> iter;
        if (toLeft) {
            iter = rightStack.pop();
            ret = iter.next();
            if (iter.hasNext()) {
                leftStack.push(iter);
            }
            if (rightStack.isEmpty()) {
                toLeft = false;
            }
        } else {
            iter = leftStack.pop();
            ret = iter.next();
            if (iter.hasNext()) {
                rightStack.push(iter);
            }
            if (leftStack.isEmpty()) {
                toLeft = true;
            }
        }
        return ret;
    }

    @Override
    public boolean hasNext() {
        return !leftStack.isEmpty() || !rightStack.isEmpty();
    }
}
