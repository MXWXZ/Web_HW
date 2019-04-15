public class BookObj {
    private int key;
    private String name;
    private String author;
    private int price;
    private String isbn;
    private int amount;
    private String img;

    public BookObj(int key, String name, String author, int price, String isbn, int amount, String img) {
        this.key = key;
        this.name = name;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.amount = amount;
        this.img = img;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
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
