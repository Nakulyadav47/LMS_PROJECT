import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class representing each Book
class Book {
    int id;
    String title;
    String author;
    boolean isAvailable;
    String borrowerName;
    LocalDate borrowDate;
    int borrowDays;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.borrowDays = 0;
    }
}

// Library class managing books
class Library {
    private final Map<Integer, Book> books = new HashMap<>();  // Efficient book lookup

    // Add a book to the library
    public void addBook(int id, String title, String author) {
        books.put(id, new Book(id, title, author));
    }

    // Display all books in the library
    public void displayBooks() {
        for (Book book : books.values()) {
            System.out.print("ID: " + book.id + ", Title: " + book.title + ", Author: " + book.author);
            System.out.println(", Available: " + (book.isAvailable ? "Yes" : "No"));
            if (!book.isAvailable) {
                System.out.println("   Borrower: " + book.borrowerName);
            }
        }
    }

    // Borrow a book
    public void borrowBook(int id) {
        if (!books.containsKey(id)) {
            System.out.println("Book not found!");
            return;
        }

        Book book = books.get(id);
        if (!book.isAvailable) {
            System.out.println("The book is currently not available.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        book.borrowerName = scanner.nextLine();

        System.out.print("Enter the number of days to borrow: ");
        if (scanner.hasNextInt()) {
            book.borrowDays = scanner.nextInt();
            if (book.borrowDays <= 0) {
                System.out.println("Invalid borrow days. Please enter a positive number.");
                return;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        book.isAvailable = false;
        book.borrowDate = LocalDate.now();
        System.out.println("You have borrowed the book: ID " + book.id + " | Title: " + book.title);
    }

    // Return a book
    public void returnBook(int id) {
        if (!books.containsKey(id)) {
            System.out.println("Book not found!");
            return;
        }

        Book book = books.get(id);
        if (book.isAvailable) {
            System.out.println("The book was not borrowed.");
            return;
        }

        int fine = calculateFine(book.borrowDate, book.borrowDays);
        if (fine > 0) {
            System.out.println("You have a fine of Rs. " + fine + " for late return.");
        }

        book.isAvailable = true;
        book.borrowerName = "";
        book.borrowDays = 0;
        System.out.println("You have returned the book: ID " + book.id + " | Title: " + book.title);
    }

    // Calculate fine for late return
    private int calculateFine(LocalDate borrowDate, int borrowDays) {
        long daysPassed = ChronoUnit.DAYS.between(borrowDate, LocalDate.now());
        return daysPassed > borrowDays ? (int) ((daysPassed - borrowDays) * 10) : 0;
    }

    // Display current status of borrowed books
    public void displayCurrentStatus() {
        boolean anyBorrowed = false;
        for (Book book : books.values()) {
            if (!book.isAvailable) {
                long daysBorrowed = ChronoUnit.DAYS.between(book.borrowDate, LocalDate.now());
                System.out.println("ID: " + book.id + " | Title: " + book.title);
                System.out.println("   Borrower: " + book.borrowerName);
                System.out.println("   Days Borrowed: " + daysBorrowed);
                System.out.println("   Return in: " + (book.borrowDays - daysBorrowed) + " days");
                anyBorrowed = true;
            }
        }
        if (!anyBorrowed) {
            System.out.println("No books are currently borrowed.");
        }
    }
}

// Main program
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding 50 books
        for (int i = 1; i <= 50; ++i) {
            lib.addBook(i, "Title" + i, "Author" + i);
        }

        int choice, id;
        while (true) {
            System.out.println("\nEnter 1 to borrow a book,\nEnter 2 to return a book,");
            System.out.println("Enter 3 to check the current status of borrowed books,");
            System.out.println("Enter 4 to check availability of all Library Books,");
            System.out.println("Enter 0 to exit.");
            System.out.print("Your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.print("Enter the book ID: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                        if (id < 1 || id > 50) {
                            System.out.println("Invalid book ID! Please enter a valid ID.");
                        } else {
                            lib.borrowBook(id);
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a valid ID.");
                        scanner.next(); // Clear invalid input
                    }
                    break;
                case 2:
                    System.out.print("Enter the book ID: ");
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                        if (id < 1 || id > 50) {
                            System.out.println("Invalid book ID! Please enter a valid ID.");
                        } else {
                            lib.returnBook(id);
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a valid ID.");
                        scanner.next(); // Clear invalid input
                    }
                    break;
                case 3:
                    lib.displayCurrentStatus();
                    break;
                case 4:
                    lib.displayBooks();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Library Management System.");
    }
}
