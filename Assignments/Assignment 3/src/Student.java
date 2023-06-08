public class Student extends Member {
    int id;
    protected final String type = "S"; // member type
    private int borrowedBook = 0; // borrowed book amount to use on boundaries, initial 0
    private boolean borrow = true; // to determine if a member can borrow a book, initially true
    private int weeks = 1; // for students
    protected static int amount; // student amount

    Student(int id) {
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
    public boolean canBorrow() {
        return borrow;
    }

    @Override
    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    @Override
    public int getBorrowedBook() {
        return borrowedBook;
    }

    @Override
    public void setBorrowedBook(int borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    @Override
    public int getWeeks() {
        return weeks;
    }

    @Override
    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }
}
