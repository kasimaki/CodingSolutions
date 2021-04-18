// Gas Station
//There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
//
//You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station
// to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
//
//Given two integer arrays gas and cost, return the starting gas station's index if you can travel around
// the circuit once in the clockwise direction, otherwise return -1. If there exists a solution,
// it is guaranteed to be unique

package LeetCode;

public class LC0134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //cc
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
            return -1;

        int len= gas.length;

        int start = 0; //[start, end)
        int end = 0;
        int accumCost = 0;
        int count = 0;
        while (count < len) {
            if (accumCost >= 0) {
                accumCost += gas[end] - cost[end];
                end = (end + 1) % len;
            } else {
                start = (start - 1 + len) % len;
                accumCost += gas[start] - cost[start];
            }
            count++;
        }
        return accumCost >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,1,2};

        LC0134 sol = new LC0134();
        int res = sol.canCompleteCircuit(gas, cost);
        System.out.println(res);
    }
}
