public class BookObj {
    private String name;
    private String author;
    private int price;
    private String isbn;
    private String img;

    public BookObj(String name, String author, int price, String isbn, String img) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
