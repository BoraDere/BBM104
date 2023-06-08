import java.util.Comparator;

public class idComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        int id1 = Integer.parseInt(a.split("\t")[0]);
        int id2 = Integer.parseInt(b.split("\t")[0]);
        return Integer.compare(id1, id2);
    }
}