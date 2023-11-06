package Books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Books.Book;

public class Main {

    public static final int COL_TITLE = 1;
    public static final int COL_PUBLISHER = 1;

    public static void main(String[] args) throws Exception {
        if(args.length <=0) {
            System.err.println("Missing book CSV");
            System.exit(1);
        } 
            System.out.printf("Processing %s\n", args[0]);

            try (FileReader fr = new FileReader(args[0])) {
                BufferedReader br = new BufferedReader(fr);

                Map<String, List<Book>> classified = br.lines()
                //br.readLine() words to skip as well
                .skip(1) 
                // String -> String array
                .map(row -> row.trim().split(","))
                // String array to a book Object! Created in a new class
                .map(fields -> new Book(fields[COL_TITLE],fields[COL_PUBLISHER]))
                // groupintBy -> returns a value that classifies the input
                .collect(Collectors.groupingBy(book -> book.getPublisher()));
                ;
                
                for(String publisher: classified.keySet()) {
                    List<Book> books = classified.get(publisher);
                    System.out.printf("%s\n", publisher, books.size());
                    for(Book book:books)
                    System.err.printf("\t%s\n", book.getTitle());

                }

            }
        }
    }