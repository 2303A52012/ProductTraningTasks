import java.util.*;
class SegmentTree {
    static int[] sumTree;
    static int[] minTree;
    static int[] maxTree;
    static int n;
    static int[] arr;

    public static void build(int idx, int s,int e){
        if(s==e){
            sumTree[idx]=arr[s];
            minTree[idx]=arr[s];
            maxTree[idx]=arr[s];
            return;
        }
        int l=(idx*2)+1;
        int r=(idx*2)+2;
        int mid=(s+e)/2;
        build(l,s,mid);
        build(r,mid+1,e);
        sumTree[idx]=sumTree[l]+sumTree[r];
        minTree[idx]=Math.min(minTree[l], minTree[r]);
        maxTree[idx]=Math.max(maxTree[l],maxTree[r]);
    }

    public static void update(int idx, int s, int e, int i, int val){

        if(s==e){
            arr[i]=val;
            sumTree[idx]=val;
            minTree[idx]=val;
            maxTree[idx]=val;
            return;
        }
        int l=(idx*2)+1;
        int r=(idx*2)+2;

        int mid=(s+e)/2;

        if(i<=mid) update(l,s,mid,i,val);
        else update(r,mid+1,e,i,val);
        
        sumTree[idx]=sumTree[l]+sumTree[r];
        minTree[idx]=Math.min(minTree[l], minTree[r]);
        maxTree[idx]=Math.max(maxTree[l],maxTree[r]);
    }

    public static int querySum(int idx, int s, int e, int l, int r){
        if(s > r || e < l)
            return 0;

        if(l<=s &&  r<=e)
            return sumTree[idx];

        int mid = (s + e) / 2;

        int left = querySum((idx * 2) + 1, s, mid, l, r);
        int right = querySum((idx * 2) + 2, mid + 1, e, l, r);

        return left + right;
    }

    public static int queryMin(int idx, int s, int e, int l, int r){
        if(s > r || e < l)
            return Integer.MAX_VALUE;

        if(s >= l && e <= r)
            return minTree[idx];

        int mid = (s + e) / 2;

        int left = queryMin((idx * 2) + 1, s, mid, l, r);
        int right = queryMin((idx * 2) + 2, mid + 1, e, l, r);

        return Math.min(left, right);
    }

    public static int queryMax(int idx, int s, int e, int l, int r){
        if(s > r || e < l)
            return Integer.MIN_VALUE;

        if(s >= l && e <= r)
            return maxTree[idx];

        int mid = (s + e) / 2;

        int left = queryMax((idx * 2) + 1, s, mid, l, r);
        int right = queryMax((idx * 2) + 2, mid + 1, e, l, r);

        return Math.max(left, right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        sumTree = new int[4 * n];
        minTree = new int[4 * n];
        maxTree = new int[4 * n];

        arr= new int[n];
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();
        build(0,0,n-1);
        System.out.println("1. update\n2. sum\n3. avg\n4. min\n5. max");

        while(true){
            int type = sc.nextInt();
            if(type==1){
                int idx = sc.nextInt();
                int val = sc.nextInt();
                update(0, 0, n-1, idx, val);
            }else if (type==2){
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(querySum(0, 0, n-1, l, r));
            }else if (type==3){
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(querySum(0, 0, n-1, l, r)/(r-l+1));
            }else if(type==4){
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(queryMin(0, 0, n-1, l, r));
            }else if(type==5){
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(queryMax(0, 0, n-1, l, r));
            }else if(type==6){
                break;
            }else{
                System.out.println("Invalid query type");
            }
        }


    }

}