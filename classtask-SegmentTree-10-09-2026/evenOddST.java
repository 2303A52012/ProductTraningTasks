import java.util.Scanner;
public class evenOddST {
    static int[] arr;
    static int n;
    static Node tree[];

    static class Node{
        int odd,even;
        Node(){
            odd=0;
            even=0;
        }
    }


    public static void build(int idx, int s, int e){
        tree[idx]= new Node();
        if(s==e){
            if(arr[s]%2==0){
                tree[idx].even=1;
            }else{
                tree[idx].odd=1;    
            }
            return;
        }
        int l=(idx*2)+1;
        int r=(idx*2)+2;
        build(l,s,(s+e)/2);
        build(r,(s+e)/2+1,e);
        tree[idx].even=tree[l].even+tree[r].even;
        tree[idx].odd=tree[l].odd+tree[r].odd;
    }


    public static Node query(int idx, int s,int e,int l,int r){
        if(s>r || e<l){
            return new Node();
        }

        if(s>=l && e<=r){
            return tree[idx];
        }

        int mid=(s+e)/2;
        int lidx= (idx*2)+1;
        int ridx= (idx*2)+2;
        Node left= query(lidx,s,mid,l,r);
        Node right= query(ridx,mid+1,e,l,r);
        Node ans= new Node();
        ans.even=left.even+right.even;
        ans.odd=left.odd+right.odd;
        return ans;
    }


    public static void update(int idx, int s, int e, int i, int val){
        if(s==e){
            arr[i]=val;
            if(arr[s]%2==0){
                tree[idx].even=1;
                tree[idx].odd=0;
            }else{
                tree[idx].even=0;
                tree[idx].odd=1;    
            }
            return;
        }
        int l=(idx*2)+1;
        int r=(idx*2)+2;

        int mid=(s+e)/2;

        if(i<=mid) update(l,s,mid,i,val);
        else update(r,mid+1,e,i,val);
        
        tree[idx].even=tree[l].even+tree[r].even;
        tree[idx].odd=tree[l].odd+tree[r].odd;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n =scan.nextInt();

        arr= new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scan.nextInt();
        }

        tree= new Node[4*n];

        build(0,0,n-1);

        int q=scan.nextInt();
        while(q-->0){
            int type=scan.nextInt();
            if(type==1){
                int i=scan.nextInt();
                int val=scan.nextInt();
                update(0,0,n-1,i,val);
            }else{
                int l=scan.nextInt();
                int r=scan.nextInt();
                Node ans=query(0,0,n-1,l,r);
                System.out.println("Even: "+ans.even+" Odd: "+ans.odd);
            }
        }
    }
}
