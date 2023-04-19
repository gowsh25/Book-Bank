import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookBank {
    public  static Scanner in = new Scanner(System.in);
    public  static List<Book> lst = new ArrayList<>();
    public static  List<String> history=new ArrayList<>();
    public static void main(String[] args) {
        int flag=1;
        lst.add(new Book(1,"aaaaaa","AAAAAA",2,2000,100));
        lst.add(new Book(2,"bbbbbb","BBBBBB",3,1000,50));
        lst.add(new Book(3,"cccccc","CCCCCC",3,200,250));
        lst.add(new Book(4,"dddddd","DDDDDD",6,300,10));
        System.out.println("***************WELCOME TO BOOK STORE***************");
        System.out.println();
        System.out.println("# # # # # # # # # # ADMIN PAGE # # # # # # # # # #");
        System.out.println();
        System.out.println("USER NAME:");
        String user =in.next();
        System.out.println("PASSWORD:");
        String password =in.next();
        if(user.equals("Admin") && password.equals("@admin")){
            while(flag==1) {
                System.out.println("1.Search Book\n2.Add Book\n3.Sell Book\n4.History\n5.Exit");
                int choice = in.nextInt();
                switch (choice) {
                    case 1:
                        search_book();
                        break;
                    case 2:
                        add_book();
                        break;
                    case 3:
                        sell_book();
                        break;
                    case 4:
                        Histroy(history);
                        break;
                    case 5:
                        break;

                    default:
                        System.out.println("Invalid Choice!!!");
                }
                System.out.println("Enter 1 to continue or 0 to exit!!!");
                flag=in.nextInt();
            }
        }
        else{
            System.out.println("Invalid user name and password!!!");
        }
    }
    public static void search_book(){
        System.out.println("1.Search By Name\n2.Show All\n3.Exit");
        int choice = in.nextInt();
        switch (choice){
            case 1:
                boolean flag=false;
                System.out.println("Enter the book to be search:");
                String bk=in.next();
                System.out.println("Book Author:");
                String bk_author=check_name();
                System.out.println("Edition:");
                int bk_edition=check_Integer();
                for(Book book : lst){
                    if(bk.equalsIgnoreCase(book.getBook_name())&& bk_author.equalsIgnoreCase(book.getAuthor())&&  bk_edition== book.getEdition()){
                        flag=true;
                        System.out.printf("Book ID:%d\nBook Name:%s\nBook Author:%s\nBook Edition:%d\nBook Amount:%.2f\nCopies:%d\n",
                                book.getBook_id(),book.getBook_name(),book.getAuthor(),book.getEdition(),book.getRps(),
                                book.getCopies());
                    }
                }
                if(!flag){
                System.out.println("Book is not found!!!");
                search_book();
                }
                break;
            case 2:
                System.out.println("Show All");
                for(Book book:lst){
                    System.out.printf("Book ID:%d\nBook Name:%s\nBook Author:%s\nBook Edition:%d\nBook Amount:%.2f\nCopies:%d\n",
                            book.getBook_id(),book.getBook_name(),book.getAuthor(),book.getEdition(),book.getRps(),
                            book.getCopies());
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid Choice!!!");

        }
    }
    public static void add_book(){
        boolean flag=false;
        System.out.println("Enter the Book to be add:");
        String bk_name=in.next();
        System.out.println("Book Author:");
        String bk_author=check_name();
        System.out.println("Edition:");
        int bk_edition=check_Integer();
        for(Book book : lst){
            if(bk_name.equalsIgnoreCase(book.getBook_name()) && bk_author.equalsIgnoreCase(book.getAuthor())&& bk_edition== book.getEdition()){
                System.out.println("Book Is Already Present!!!");
                System.out.printf("Enter the copies to be add in %s book:\n",book.getBook_name());
                int copy=check_Integer();
                int cur_copy= book.getCopies()+copy;
                book.setCopies(cur_copy);
                flag=true;
                System.out.println("Book copies are updated in store!!");
                break;
            }

        }
        if(!flag){
            System.out.println("Book Copies:");
            int bk_copies=check_Integer();
            System.out.println("Book Amount:");
            float bk_rps=check_Float();
            lst.add(new Book(lst.size()+1, bk_name,bk_author,bk_edition,bk_rps,bk_copies));
            System.out.println("New Book is Added!!!");
        }
    }
    public static void sell_book(){
        boolean flag = false;
        System.out.println("Enter the Book to be sell:");
        String bk_name=in.next();
        System.out.println("Book Author:");
        String bk_author=check_name();
        System.out.println("Edition:");
        int bk_edition=check_Integer();
        for(Book book : lst){
            if(bk_name.equalsIgnoreCase(book.getBook_name()) && bk_author.equalsIgnoreCase(book.getAuthor())&& bk_edition== book.getEdition()){
                System.out.println("Enter the number of copies to be sell:");
                int buy=check_Integer();
                int cur_copies=book.getCopies();
                if(cur_copies>=buy){
                    float bill= (float) (buy*book.getRps());
                    System.out.println("-----BUYER DETAILS-----");
                    System.out.println("Name:");
                    //String buyer=in.next();
                    String buyer=check_name();
                    System.out.println("Contact Number:");
                    long buyer_con=check_num();
                    System.out.println("No of Books Buy:"+buy);
                    System.out.println("Bill Amount:"+bill);
                    String his=buyer+" "+buyer_con+" "+book.getBook_id()+ " "+buy+" "+bill;
                    history.add(his);
                    int sold_bk=cur_copies-buy;
                    book.setCopies(sold_bk);
                    System.out.printf("Current copy of %s Book : %d\n",bk_name,book.getCopies());
                    flag=true;
                    break;
                }
                else{
                    System.out.println("Insufficient Book!!");
                    flag=true;
                    break;
                }
            }
        }
        if(!flag){
            System.out.println("Book is not found!!!");
        }
    }
    public static void Histroy(List<String> history){
        System.out.println("-------History of the Book Store-------");
        if(history.isEmpty())
            System.out.println("No Transaction History!!!");
        else {
            System.out.println("Buyer Name || Contact Number || Book ID || Sold Copies || Bill ");
            for (String i : history) {
                String[] str = i.split(" ");
                System.out.printf("%-11s||%-16s||%-9s||%-13s||%-6s\n",str[0],str[1],str[2],str[3],str[4]);

            }
        }
    }
    public static String check_name(){
        boolean flag=false;
        String str="";
        while(!flag){
            String name=in.next();
            if(name.matches("^[a-zA-Z]*$")){
                flag=true;
                str+=name;
                break;
            }
            else{
                System.out.println("Invalid--Enter the valid name again!!!");
            }
        }
        return str;
    }
    public static int check_Integer(){
        boolean flag=false;
        String str="";
        while(!flag){
            String num=in.next();
            if(num.matches("[0-9]+")){
                flag=true;
                str+=num;
                break;
            }
            else{
                System.out.println("Invalid--Enter the valid number again!!!");
            }
        }
        int number=Integer.parseInt(str);
        return number;
    }
    public static float check_Float(){
        boolean flag=false;
        String str="";
        while(!flag){
            String num=in.next();
            if(num.matches("[0-9]+")){
                flag=true;
                str+=num;
                break;
            }
            else{
                System.out.println("Invalid--Enter the valid amount again!!!");
            }
        }
        float number=Float.parseFloat(str);
        return number;
    }
    public static Long check_num(){
        boolean flag=false;
        String str="";
        while(!flag){
            String num=in.next();
            if(num.matches("[6-9][0-9]{9}")){
                flag=true;
                str+=num;
                break;
            }
            else{
                System.out.println("Invalid--Enter the valid number again!!!");
            }
        }
        Long number=Long.parseLong(str);
        return number;
    }
}
