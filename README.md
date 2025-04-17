# LMS_PROJECT
Library-Management-System
## Overview
This Library Management System is a Java-based console application that allows users to manage book borrowing and returning efficiently. The system keeps track of book availability, borrower details, and due dates while also calculating fines for late returns.

## Features
- 📚 Add Books to Library: Initializes with 50 books, each with a unique ID, title, and author.
- 👤 Borrow a Book: Users can borrow a book by providing their name and the number of days they intend to keep it.
- ✅ Return a Book: Users can return a borrowed book, and the system will check if any late fine is applicable.
- 📋 Check Borrowed Books Status: Displays currently borrowed books with borrower details and return deadlines.
- 🔍 View All Books: Lists all books with their availability status.
- ⚖ Fine Calculation: If a book is returned late, a fine of ₹10 per extra day is applied.

## Technologies Used
- Java for core programming.
- Java Collections (HashMap) for efficient book storage and lookup.
- Java Time API (LocalDate, ChronoUnit) for date calculations.
- Scanner (User Input Handling) for interactive console-based operations.

## How to Run
1. Clone the Repository:
   ```sh
   git clone https://github.com/your-username/library-management-system.git
   ```
2. Navigate to the Project Directory:
   ```sh
   cd library-management-system
   ```
3. Compile the Java Code:
   ```sh
   javac LibraryManagementSystem.java
   ```
4. Run the Application:
   ```sh
   java LibraryManagementSystem
   ```

## Usage Guide
Upon running the program, users can interact with the system through a menu-driven interface:

```
Enter 1 to borrow a book
Enter 2 to return a book
Enter 3 to check the current status of borrowed books
Enter 4 to check availability of all library books
Enter 0 to exit
```
### Example:
```
Your choice: 1  
Enter the book ID: 5  
Enter your name: John  
Enter the number of days to borrow: 7  
You have borrowed the book: ID 5 | Title: Title5  
```

## Contribution
Feel free to contribute by submitting issues or pull requests:
1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Open a Pull Request

