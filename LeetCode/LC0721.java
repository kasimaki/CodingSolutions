//Account Merge Amazon
//Given a list accounts, each element accounts[i] is a list of strings, where the
// first element accounts[i][0] is a name, and the rest of the elements are emails
// representing emails of the account.
//
//Now, we would like to merge these accounts. Two accounts definitely belong to
// the same person if there is some email that is common to both accounts.
// Note that even if two accounts have the same name, they may belong to
// different people as people could have the same name. A person can have
// any number of accounts initially, but all of their accounts definitely
// have the same name.
//
//After merging the accounts, return the accounts in the following format:
// the first element of each account is the name, and the rest of the elements
// are emails in sorted order. The accounts themselves can be returned in any order.

package LeetCode;

import java.util.*;

class UnionFindLC721 {
    int[] parent;
    int[] size;

    public UnionFindLC721(int n) {
        this.parent = new int[n];
        this.size = new int[n];
    }

    public int getRoot(int p) {
        int cur = p;
        while (cur != parent[cur]) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        parent[p] = cur;
        return cur;
    }

    public boolean find(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public void addNode(int p) {
        parent[p] = p;
        size[p] = 1;
    }

    public boolean isAdded(int p) {
        return size[p] > 0;
    }
}

public class LC0721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //cc
        if (accounts == null || accounts.size() <= 1) return accounts;

        List<List<String>> res = new ArrayList<>();
        UnionFindLC721 uf = new UnionFindLC721(accounts.size());
        HashMap<String, Integer> emailToAccNum = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) { //O(n)
            if (!uf.isAdded(i)) {
                uf.addNode(i);
            }
            List<String> all = accounts.get(i);
            for (int j = 1; j < all.size(); j++) { //O(k)
                String email = all.get(j);
                Integer accNum = emailToAccNum.get(email);
                if (accNum == null) {
                    emailToAccNum.put(email, i);
                } else {
                    if (i != accNum) {
                        if (!uf.find(i, accNum)) { // O(logn)
                            uf.union(i, accNum);
                        }
                    }
                }
            }
        }

        HashMap<Integer, HashSet<String>> accNumToEmail = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) { //O(n)
            int rootNum = uf.getRoot(i); //O(logn)
            HashSet<String> set = accNumToEmail.getOrDefault(rootNum, new HashSet<>());
            List<String> all = accounts.get(i);
            for (int j = 1; j < all.size(); j++) { //O(k)
                set.add(all.get(j));
            }
            accNumToEmail.put(rootNum, set);
        }

        for (Map.Entry<Integer, HashSet<String>> entry : accNumToEmail.entrySet()) { //O(n)
            List<String> list = new ArrayList<>();
            list.add(accounts.get(entry.getKey()).get(0));
            list.addAll(entry.getValue());
            Collections.sort(list); //O(klogk)
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));

        LC0721 sol = new LC0721();
        List<List<String>> res = sol.accountsMerge(accounts);
        System.out.println(res);
    }
}
