import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Main {

    // The method used while adding books.
    static void addBook(ArrayList<Book> books, String[] strips, String[] args) {
        String type = null;
        if (strips[1].equals("H")) {
            books.add(new HandwrittenBook(Book.id));
            Book.id++;
            HandwrittenBook.amount++;
            type = "Handwritten";
        }
        else if (strips[1].equals("P")) {
            books.add(new PrintedBook(Book.id));
            Book.id++;
            PrintedBook.amount++;
            type = "Printed";
        }
        FileWrite.fileWrite(String.format("Created new book: %s [id: %d]\n", type, Book.id - 1), args[1]); // -1 because id was set to 1 initially, then increased. This is
    }                                                                                                      // just a simple workaround

    // The method used while adding members.
    static void addMember(ArrayList<Member> members, String[] strips, String[] args) {
        String type = null;
        if (strips[1].equals("S")) {
            members.add(new Student(Member.id));
            Member.id++;
            Student.amount++;
            type = "Student";
        }
        else if (strips[1].equals("A")) {
            members.add(new Academic(Member.id));
            Member.id++;
            Academic.amount++;
            type = "Academic";
        }
        FileWrite.fileWrite(String.format("Created new member: %s [id: %d]\n", type, Member.id - 1), args[1]); // Same thing here
    }

    // The method used for book-borrowing operations.
    static void borrowBook(ArrayList<Member> members, ArrayList<Book> books, Book bookToOperate, Member memberToOperate, int bookID, int memberID, LocalDate date, String[] args) {
        if (bookID <= Book.id) { // Checking if asked book exists.
            if (memberID <= Member.id) { // Checking if asked member exists.
                for (Book book : books) {
                    if (book.getId() == bookID) {
                        bookToOperate = book;
                    }
                }
                for (Member member : members) {
                    if (member.getId() == memberID) {
                        memberToOperate = member;
                    }
                }
                if (bookToOperate.isBorrowed() || bookToOperate.getType().equals("H")) {
                    FileWrite.fileWrite("You cannot borrow this book!\n", args[1]);
                }
                else if (!memberToOperate.canBorrow()) {
                    FileWrite.fileWrite("You have exceeded the borrowing limit!\n", args[1]);
                }
                else { // Borrowing process.
                    bookToOperate.setBorrowed(true);
                    memberToOperate.setBorrowedBook(memberToOperate.getBorrowedBook() + 1); // To use while deciding if the member can borrow more books.
                    bookToOperate.setBorrowTime(date);
                    bookToOperate.setDeadline(date.plusWeeks(memberToOperate.getWeeks()));
                    bookToOperate.setBorrower(memberID);
                    Book.borrowedAmount++;
                    FileWrite.fileWrite(String.format("The book [%d] was borrowed by member [%d] at %s\n", bookID, memberID, date), args[1]);
                    if (memberToOperate.getType().equals("S") && memberToOperate.getBorrowedBook() == 2) { // So that members will not be
                        memberToOperate.setBorrow(false);                                                  // able to exceed their limit.
                    }
                    else if (memberToOperate.getType().equals("A") && memberToOperate.getBorrowedBook() == 4) {
                        memberToOperate.setBorrow(false);
                    }
                }
            }
            else {
                FileWrite.fileWrite("There is not a member with the given ID!\n", args[1]);
            }
        }
        else {
            FileWrite.fileWrite("There is not a book with the given ID!\n", args[1]);
        }
    }

    // The method used for book-returning operations.
    static void returnBook(ArrayList<Member> members, ArrayList<Book> books, Book bookToOperate, Member memberToOperate, int bookID, int memberID, LocalDate date, String[] strips, String[] args) {
        if (bookID <= Book.id) {
            if (memberID <= Member.id) {
                for (Book book : books) {
                    if (book.getId() == bookID) {
                        bookToOperate = book;
                    }
                }
                for (Member member : members) {
                    if (member.getId() == memberID) {
                        memberToOperate = member;
                    }
                }
                long fee = date.isAfter(bookToOperate.getDeadline()) ? ChronoUnit.DAYS.between(bookToOperate.getDeadline(), date) : 0;
                if (bookToOperate.isBorrowed()) {
                    Book.borrowedAmount--;
                    bookToOperate.setBorrower(0);
                    bookToOperate.setBorrowed(false);
                    memberToOperate.setBorrowedBook(memberToOperate.getBorrowedBook() - 1);
                    if (!memberToOperate.canBorrow()) {   // A simple workaround. If members have reached their limit (and since they cannot exceed it)
                        memberToOperate.setBorrow(true);  // returning even one single book will make them able to borrow another book.
                    }
                    if (!bookToOperate.isExtended() && fee == 0) {
                        memberToOperate.setWeeks(memberToOperate.getWeeks() * 2);
                    }
                    FileWrite.fileWrite(String.format("The book [%d] was returned by member [%d] at %s Fee: %d\n", bookID, memberID, strips[3], fee), args[1]);
                }
                else if (bookToOperate.isReadIn()) {
                    Book.readInAmount--;
                    bookToOperate.setReader(0);
                    bookToOperate.setReadIn(false);
                    FileWrite.fileWrite(String.format("The book [%d] was returned by member [%d] at %s Fee: %d\n", bookID, memberID, strips[3], fee), args[1]);
                }
                else {
                    FileWrite.fileWrite("You cannot return this book!\n", args[1]);
                }
            }
            else {
                FileWrite.fileWrite("There is not a member with the given ID!\n", args[1]);
            }
        }
        else {
            FileWrite.fileWrite("There is not a book with the given ID!\n", args[1]);
        }
    }

    // The method used to extend a book's deadline.
    static void extendBook(ArrayList<Member> members, ArrayList<Book> books, Book bookToOperate, Member memberToOperate, int bookID, int memberID, String[] strips, String[] args) {
        if (bookID <= Book.id) {
            if (memberID <= Member.id) {
                for (Book book : books) {
                    if (book.getId() == bookID) {
                        bookToOperate = book;
                    }
                }
                for (Member member : members) {
                    if (member.getId() == memberID) {
                        memberToOperate = member;
                    }
                }
                if (bookToOperate.isExtended()) {
                    FileWrite.fileWrite("You cannot extend the deadline!\n", args[1]);
                }
                else {
                    bookToOperate.setDeadline(bookToOperate.getDeadline().plusWeeks(memberToOperate.getWeeks()));
                    bookToOperate.setExtended(true);
                    FileWrite.fileWrite(String.format("The deadline of book [%d] was extended by member [%d] at %s", bookID, memberID, strips[3]) +
                            String.format("\nNew deadline of book [%d] is %s\n", bookID, bookToOperate.getDeadline()), args[1]);
                }
            }
            else {
                FileWrite.fileWrite("There is not a member with the given ID!\n", args[1]);
            }
        }
        else {
            FileWrite.fileWrite("There is not a book with the given ID!\n", args[1]);
        }
    }

    // The method used when a book is read in library.
    static void readInLibrary(ArrayList<Member> members, ArrayList<Book> books, Book bookToOperate, Member memberToOperate, int bookID, int memberID, LocalDate date, String[] args) {
        if (bookID <= Book.id) {
            if (memberID <= Member.id) {
                for (Book book : books) {
                    if (book.getId() == bookID) {
                        bookToOperate = book;
                    }
                }
                for (Member member : members) {
                    if (member.getId() == memberID) {
                        memberToOperate = member;
                    }
                }
                if (!bookToOperate.isReadIn() && !bookToOperate.isBorrowed()) {
                    if (bookToOperate.getType().equals("H")) {
                        if (memberToOperate.getType().equals("S")) {
                            FileWrite.fileWrite("Students can not read handwritten books!\n", args[1]);
                        }
                        else {
                            Book.readInAmount++;
                            bookToOperate.setReader(memberID);
                            bookToOperate.setReadTime(date);
                            bookToOperate.setReadIn(true);
                            FileWrite.fileWrite(String.format("The book [%d] was read in library by member [%d] at %s\n", bookID, memberID, date), args[1]);
                        }
                    }
                    else if (bookToOperate.getType().equals("P")) {
                        Book.readInAmount++;
                        bookToOperate.setReader(memberID);
                        bookToOperate.setReadTime(date);
                        bookToOperate.setReadIn(true);
                        FileWrite.fileWrite(String.format("The book [%d] was read in library by member [%d] at %s\n", bookID, memberID, date), args[1]);
                    }
                }
                else {
                    FileWrite.fileWrite("You can not read this book!\n", args[1]);
                }
            }
            else {
                FileWrite.fileWrite("There is not a member with the given ID!\n", args[1]);
            }
        }
        else {
            FileWrite.fileWrite("There is not a book with the given ID!\n", args[1]);
        }
    }

    // The method used to get the history.
    static void getTheHistory(ArrayList<Member> members, ArrayList<Book> books, String[] args) {
        FileWrite.fileWrite("History of library:", args[1]);
        FileWrite.fileWrite("\n\nNumber of students: " + Student.amount, args[1]);
        for (Member member : members) {
            if (member.getType().equals("S")) {
                FileWrite.fileWrite(String.format("\nStudent [id: %d]", member.getId()), args[1]);
            }
        }
        FileWrite.fileWrite("\n\nNumber of academics: " + Academic.amount, args[1]);
        for (Member member : members) {
            if (member.getType().equals("A")) {
                FileWrite.fileWrite(String.format("\nAcademic [id: %d]", member.getId()), args[1]);
            }
        }
        FileWrite.fileWrite("\n\nNumber of printed books: " + PrintedBook.amount, args[1]);
        for (Book book : books) {
            if (book.getType().equals("P")) {
                FileWrite.fileWrite(String.format("\nPrinted [id: %d]", book.getId()), args[1]);
            }
        }
        FileWrite.fileWrite("\n\nNumber of handwritten books: " + HandwrittenBook.amount, args[1]);
        for (Book book : books) {
            if (book.getType().equals("H")) {
                FileWrite.fileWrite(String.format("\nHandwritten [id: %d]", book.getId()), args[1]);
            }
        }
        FileWrite.fileWrite("\n\nNumber of borrowed books: " + Book.borrowedAmount, args[1]);
        for (Book book : books) {
            if (book.isBorrowed()) {
                FileWrite.fileWrite(String.format("\nThe book [%d] was borrowed by member [%d] at %s", book.getId(), book.getBorrower(), book.getBorrowTime()), args[1]);
            }
        }
        FileWrite.fileWrite("\n\nNumber of books read in library: " + Book.readInAmount, args[1]);
        for (Book book : books) {
            if (book.isReadIn()) {
                FileWrite.fileWrite(String.format("\nThe book [%d] was read in library by member [%d] at %s", book.getId(), book.getReader(), book.getReadTime()), args[1]);
            }
        }
    }


    public static void main(String[] args) {
        String[] lines = ReadFile.readFile(args[0], false, false);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Member> members = new ArrayList<>();
        Book bookToOperate = null;
        Member memberToOperate = null;
        LocalDate date = null;
        int bookID = 0, memberID = 0;
        for (String line : lines) {
            String[] strips = line.split("\t");
            try { // since commands with 4 elements have the same element order, it is just a simple workaround to avoid assigning them multiple times in the code
                bookID = Integer.parseInt(strips[1]);
                memberID = Integer.parseInt(strips[2]);
                date = LocalDate.parse(strips[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch (Exception e) {
                // nothing
            }
            if (strips[0].equals("addBook") && strips.length == 2) {
                addBook(books, strips, args);
            }
            else if (strips[0].equals("addMember") && strips.length == 2) {
                addMember(members, strips, args);
            }
            else if (strips[0].equals("borrowBook") && strips.length == 4) {
                borrowBook(members, books, bookToOperate, memberToOperate, bookID, memberID, date, args);
            }
            else if (strips[0].equals("returnBook") && strips.length == 4) {
                returnBook(members, books, bookToOperate, memberToOperate, bookID, memberID, date, strips, args);
            }
            else if (strips[0].equals("extendBook") && strips.length == 4) {
                extendBook(members, books, bookToOperate, memberToOperate, bookID, memberID, strips, args);
            }
            else if (strips[0].equals("readInLibrary")) {
                readInLibrary(members, books, bookToOperate, memberToOperate, bookID, memberID, date, args);
            }
            else if (strips[0].equals("getTheHistory")) {
                getTheHistory(members, books, args);
            }
            else {
                FileWrite.fileWrite("Unknown command!\n", args[1]);
            }
        }
    }
}
