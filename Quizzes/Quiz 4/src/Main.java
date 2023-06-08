import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] poem = ReadFile.readFile(args[0]);
        idComparator comparator = new idComparator();
        ArrayList<String> poemArrayList = new ArrayList<>(Arrays.asList(poem));
        HashSet<String> poemHashSet = new HashSet<>(Arrays.asList(poem));
        TreeSet<String> poemTreeSet = new TreeSet<>(Arrays.asList(poem));
        TreeSet<String> sortedPoemTreeSet = new TreeSet<>(comparator);
        sortedPoemTreeSet.addAll(poemTreeSet);
        HashMap<Integer, String> poemHashMap = new HashMap<>();
        for (String str : poem) {
            poemHashMap.put(Integer.parseInt(str.split("\t")[0]), str.split("\t")[1]);
        }
        for (String verse : poemArrayList) {
            FileWrite.fileWrite(verse + "\n", "poemArrayList.txt");
        }
        poemArrayList.sort(comparator);
        for (String verse : poemArrayList) {
            FileWrite.fileWrite(verse + "\n", "poemArrayListOrderByID.txt");
        }
        for (String verse : poemHashSet) {
            FileWrite.fileWrite(verse + "\n", "poemHashSet.txt");
        }
        for (String verse : poemTreeSet) {
            FileWrite.fileWrite(verse + "\n", "poemTreeSet.txt");
        }
        for (String verse : sortedPoemTreeSet) {
            FileWrite.fileWrite(verse + "\n", "poemTreeSetOrderByID.txt");
        }
        for (int i = 1; i <= poemHashMap.size(); i++) {
            FileWrite.fileWrite(i + "\t" + poemHashMap.get(i) + "\n", "poemHashMap.txt");
        }
    }
}