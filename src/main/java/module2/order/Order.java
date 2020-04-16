
package module2.order;

import java.util.List;

public class Order {
    
    static List<String> l1;
    static List<String[]> l2;
    
    public static List<String> orderList(List<String> l){
        l1= l;
        quickSort1(l1, 0, l1.size() - 1);
        return l1;
    }
    public static List<String[]> orderListVector(List<String[]> l){
        l2= l;
        quickSort2(l2, 0, l2.size() - 1);
        return l2;
    }
    
    private static void quickSort1(List<String> v, int left, int right) {
        int l = left;
        int r = right;
        String pivo = v.get((l + r) / 2);
        String troca;

        while (l <= r) {
            while (v.get(l).compareTo(pivo)<0) {
                l = l + 1;
            }
            while (v.get(r).compareTo(pivo)>0) {
                r = r - 1;
            }
            if (l <= r) {
                troca = v.get(l);
                v.set(l, v.get(r));
                v.set(r, troca);
                l = l + 1;
                r = r - 1;
            }
        }
        if (r > left) {
            quickSort1(v, left, r);
        }

        if (l < right) {
            quickSort1(v, l, right);
        }

    }
    
    private static void quickSort2(List<String[]> v, int left, int right) {
        int l = left;
        int r = right;
        double pivo = Double.parseDouble(v.get((l + r) / 2)[1]);
        String[] troca;

        while (l <= r) {
            while (Double.parseDouble(v.get(l)[1]) < pivo) {
                l = l + 1;
            }
            while (Double.parseDouble(v.get(r)[1]) > pivo) {
                r = r - 1;
            }
            if (l <= r) {
                troca = v.get(l);
                v.set(l, v.get(r));
                v.set(r, troca);
                l = l + 1;
                r = r - 1;
            }
        }
        if (r > left) {
            quickSort2(v, left, r);
        }

        if (l < right) {
            quickSort2(v, l, right);
        }

    }
    
     
}
