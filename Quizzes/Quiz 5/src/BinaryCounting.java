public class BinaryCounting {
    /**
     * Counting in binary up to the asked decimal.
     * @param n is the asked decimal.
     * @param location is the output location.
     */
    public static void countUpToBinary(int n, String location) {
        Structure<String> queue = new Structure<>();
        FileWrite.fileWrite("Counting from 1 up to " + n + " in binary:\t", location);
        queue.enqueue("1");

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                FileWrite.fileWrite("\t", location);
            }
            String binary = queue.dequeue();
            FileWrite.fileWrite(binary, location);

            queue.enqueue(binary + "0"); // This actually seems like bad practice because at the end of the
            queue.enqueue(binary + "1"); // process there are unnecessary elements in the queue but I couldn't
        }                                       // figure out a better way.
    }
}
