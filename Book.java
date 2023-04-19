class Book {
    private String book_name;
    private int book_id;
    private String author;
    private float rps;
    private int edition;
    private int copies;
    Book(int book_id,String book_name,String author,int edition,float rps,int copies){
        this.book_id=book_id;
        this.book_name=book_name;
        this.author=author;
        this.edition=edition;
        this.rps=rps;
        this.copies=copies;
    }

    public float getRps() {
        return rps;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getCopies() {
        return copies;
    }

    public int getEdition() {
        return edition;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
