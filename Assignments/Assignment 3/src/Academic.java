public class Academic extends Member {
    int id;
    protected final String type = "A"; // member type
    private int borrowedBook = 0; // borrowed book amount to use on boundaries, initial 0
    private boolean borrow = true; // to determine if a member can borrow a book, initially true
    private int weeks = 2; // for academics
    protected static int amount; // academic amount

    Academic(int id) {
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
