import java.util.*;

public class zerosCountSegmentTree {

    static int[] tree;
    static int[] arr;
    static int n;

    // Build Segment Tree
    public static void build(int idx, int s, int e) {

        if (s == e) {
            tree[idx] = (arr[s] == 0) ? 1 : 0;
            return;
        }

        int mid = (s + e) / 2;

        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        build(left, s, mid);
        build(right, mid + 1, e);

        tree[idx] = tree[left] + tree[right];
    }

    // Point Update
    public static void update(int idx, int s, int e, int pos, int val) {

        if (s == e) {
            arr[pos] = val;
            tree[idx] = (val == 0) ? 1 : 0;
            return;
        }

        int mid = (s + e) / 2;

        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if (pos <= mid) {
            update(left, s, mid, pos, val);
        } else {
            update(right, mid + 1, e, pos, val);
        }

        tree[idx] = tree[left] + tree[right];
    }

    // Range Query - Count Zeros
    public static int query(int idx, int s, int e, int l, int r) {

        // No overlap
        if (e < l || s > r) {
            return 0;
        }

        // Complete overlap
        if (s >= l && e <= r) {
            return tree[idx];
        }

        // Partial overlap
        int mid = (s + e) / 2;

        int leftZeros = query(
            2 * idx + 1,
            s,
            mid,
            l,
            r
        );

        int rightZeros = query(
            2 * idx + 2,
            mid + 1,
            e,
            l,
            r
        );

        return leftZeros + rightZeros;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        tree = new int[4 * n];

        build(0, 0, n - 1);

        int q = sc.nextInt();

        while (q-- > 0) {

            int type = sc.nextInt();

            if (type == 1) {

                // 0-based index
                int index = sc.nextInt();
                int value = sc.nextInt();

                update(
                    0,
                    0,
                    n - 1,
                    index,
                    value
                );

            } else if (type == 2) {

                int l = sc.nextInt();
                int r = sc.nextInt();

                System.out.println(
                    query(0, 0, n - 1, l, r)
                );
            }
        }

        sc.close();
    }
}