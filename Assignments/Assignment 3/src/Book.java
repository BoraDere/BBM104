import java.time.LocalDate;

abstract class Book {
    static int id = 1; // static because books cannot have the same id, it seemed like good practice
    protected String type; // book type
    protected LocalDate borrowTime;
    protected LocalDate readTime;
    protected LocalDate deadline;
    boolean borrowed; // states if a book is borrowed
    protected boolean readIn; // states if a book is read in library
    protected static int borrowedAmount; // borrowed book count
    protected static int readInAmount; // read-in-library book count
    protected boolean extended; // states if a book has been extended
    protected int borrower;
    protected int reader;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public LocalDate getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDate borrowTime) {
        this.borrowTime = borrowTime;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isReadIn() {
        return readIn;
    }

    public void setReadIn(boolean readIn) {
        this.readIn = readIn;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public boolean isExtended() {
        return extended;
    }

    public int getBorrower() {
        return borrower;
    }

    public void setBorrower(int borrower) {
        this.borrower = borrower;
    }

    public int getReader() {
        return reader;
    }

    public void setReader(int reader) {
        this.reader = reader;
    }

    public LocalDate getReadTime() {
        return readTime;
    }

    public void setReadTime(LocalDate readTime) {
        this.readTime = readTime;
    }
}
