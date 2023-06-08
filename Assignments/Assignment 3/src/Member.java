abstract class Member {

    protected static int id = 1; // static because members cannot have the same id, it seemed like good practice
    protected String type; // member type
    protected int borrowedBook; // borrowed book amount to use on boundaries
    protected boolean borrow; // to determine if a member can borrow a book
    protected int weeks; // to calculate the deadline

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean canBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public int getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(int borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }
}
