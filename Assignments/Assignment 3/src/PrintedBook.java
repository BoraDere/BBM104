import java.time.LocalDate;

public class PrintedBook extends Book {
    int id;
    protected final String type = "P"; // book type
    private LocalDate borrowTime;
    private LocalDate readTime;
    private LocalDate deadline;
    boolean borrowed = false; // states if a book is borrowed, initially false
    protected static int amount = 0; // printed book amount
    private boolean extended = false; // states if a book has been extended, initially false
    private boolean readIn = false; // states if a book is read in library, initially false
    private int borrower;
    private int reader;

    PrintedBook(int id) {
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
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
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
    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    @Override
    public boolean isExtended() {
        return extended;
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
    public int getBorrower() {
        return borrower;
    }

    @Override
    public void setBorrower(int borrower) {
        this.borrower = borrower;
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
