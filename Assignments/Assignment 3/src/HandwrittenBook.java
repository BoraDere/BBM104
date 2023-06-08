import java.time.LocalDate;

public class HandwrittenBook extends Book {
    int id;
    protected final String type = "H"; // book type
    private LocalDate borrowTime;
    private LocalDate readTime;
    private LocalDate deadline;
    private boolean readIn = false; // states if a book is read in library, initially false
    protected static int amount = 0; // handwritten book amount
    private boolean extended = false; // states if a book has been extended, initially false
    private int reader; // since it only can be read

    HandwrittenBook(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public LocalDate getBorrowTime() {
        return borrowTime;
    }

    @Override
    public void setBorrowTime(LocalDate borrowTime) {
        this.borrowTime = borrowTime;
    }

    @Override
    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean isReadIn() {
        return readIn;
    }

    @Override
    public void setReadIn(boolean readIn) {
        this.readIn = readIn;
    }

    @Override
    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    @Override
    public boolean isExtended() {
        return extended;
    }

    @Override
    public int getReader() {
        return reader;
    }

    @Override
    public void setReader(int reader) {
        this.reader = reader;
    }

    @Override
    public LocalDate getReadTime() {
        return readTime;
    }

    @Override
    public void setReadTime(LocalDate readTime) {
        this.readTime = readTime;
    }
}
