package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
/* @NoArgsConstructor
 @AllArgsConstructor*/
public class AddBookBody {
    private String userId;
    private List<BookIsbn> collectionOfIsbns;

     @Data
/*     @NoArgsConstructor
     @AllArgsConstructor*/
    public static class BookIsbn {
        private String isbn;

         public BookIsbn(String isbn) {
             this.isbn = isbn;
         }
     }

    public AddBookBody(String userId, List<BookIsbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public AddBookBody() {
    }

    public AddBookBody(String userId) {
        this.userId = userId;
    }
}
