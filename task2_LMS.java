import java.util.*;
class task2_LMS
{
    static int userid=1,bookid=1;
    static user us[]=new user[10000];//max 10000 users allowed hehe
    static book bk[]=new book[200000];//max 200000 books capacity of library 
    void addUser()
    {
        int temp=userid;
        userid++;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name of new user :");
        String name=sc.nextLine();
        int uid=temp;
        System.out.println("Enter user's age :");
        int age=sc.nextInt();
        System.out.println("NO OF BOOKS USER HAS ISSUED CURRENTLY AT TIME OF ID CREATION:  (MAX 4 BOOKS PER USER) ");
        int isn=sc.nextInt();
        int ibid[];
        if(isn<=4 && isn!=0 && isn>0)
        {
            ibid=new int[isn];
            System.out.println("ENTER BOOK-IDS OF BOOKS ISSUED : ");
            for(int i=0;i<isn;i++)
            {
                ibid[i]=sc.nextInt();
                if(ibid[i] < bookid && bk[ibid[i]] != null && bk[ibid[i]].qty > 0) 
                {
                    bk[ibid[i]].qty -= 1;
                } 
                else
                {
                    System.out.println("BOOK ID " + ibid[i] + " CANNOT BE ISSUED AS IT DOES NOT EXIST OR IS OUT OF STOCK");
                    isn--; // Reduce count of issued books
                }
            }
        }
        else if(isn>4)
        {
            System.out.println("MORE THAN 4 BOOKS NOT ALLOWED");
            ibid=new int[4];
        }
        else
        {
            ibid=new int[4];
        }
        us[temp]=new user(name,uid,age,isn,ibid);
        System.out.println("NEW USER REGISTERED:: "+us[temp].name+"\'s UID IS : "+us[temp].uid+"\n HE/SHE SHOULD NOTE HIS/HER UID AND REMEMBER IT FOR FUTURE REFERENCE...");

    }
    void getUser(int u) 
    {
        if (u < userid && us[u] != null) 
        {  // Ensure user exists
            System.out.println("---USER DETAILS---");
            System.out.println("NAME: " + us[u].name);
            System.out.println("UID: " + us[u].uid);
            System.out.println("AGE: " + us[u].age);
        } 
        else 
        {
            System.out.println("USER DOES NOT EXIST...");
        }
    }
    
    void getBook(int b) 
    {
        if (b < bookid && bk[b] != null) 
        {  // Ensure book exists
            System.out.println("-----BOOK DETAILS-----");
            System.out.println("NAME OF BOOK: " + bk[b].name);
            System.out.println("BOOK ID: " + bk[b].bid);
            System.out.println("ISBN: " + bk[b].isbn);
            System.out.println("AUTHOR: " + bk[b].author);
            System.out.println("AVAILABLE QUANTITY: " + bk[b].qty);
        } 
        else 
        
        {
            System.out.println("INVALID BOOK ID..");
        }
    }
    void addBook()
    {
        int temp=bookid;
        bookid++;
        Scanner sc=new Scanner(System.in);
        System.out.println("ENTER ISBN OF BOOK : ");
        String isbn=sc.nextLine();
        sc.next();//avoiding string buffer..
        System.out.println("ENTER NAME OF BOOK:");
        String name=sc.nextLine();
        sc.next();//avoiding string buffer..
        System.out.println("ENTER NAME OF AUTHOR : ");
        String author=sc.nextLine();
        System.out.println("ENTER NO OF AVAILABLE QUANTITY OF THIS BOOK : ");
        int qty=sc.nextInt();
        bk[temp]=new book(isbn,temp,name,author,qty);
        System.out.println("BOOK "+bk[temp].name+" ADDED SUCCESSFULLY WITH BOOK ID "+bk[temp].bid);
    }
    void removeBook(int bd)
    {
        if (bd < bookid && bk[bd] != null) 
        {
            bk[bd] = null;
            System.out.println("BOOK REMOVED SUCCESSFULLY.");
        } 
        else 
        {
            System.out.println("BOOK DOES NOT EXIST.");
        }
    }
    
    void removeUser(int ud) 
    {
        if (ud < userid && us[ud] != null) 
        {
            us[ud] = null;
            System.out.println("USER REMOVED SUCCESSFULLY.");
        } 
        else 
        {
            System.out.println("USER DOES NOT EXIST.");
        }
    }
    void issueBook(int uid,int bd)
    {
        if(bk[bd].qty!=0 && us[uid].nis<4 && us[uid].uid<=userid && bk[bd].bid!=0)
        {
            bk[bd].qty-=1;
            for(int i=0;i<4;i++)
            {
                if(us[uid].issuedbookid[i]==0)
                {
                    us[uid].issuedbookid[i]=bd;
                    us[uid].nis+=1;
                    break;
                }
            }
            System.out.println("BOOK-ID:"+bd+"ISSUED TO USER-"+us[uid].name+"USER-ID :"+us[uid].uid);
        }
        else if(bk[bd].qty==0)
        {
            System.out.println("ANOTHER COPY OF THIS BOOK IS NOT AVAILABLE AT THIS MOMENT... PLEASE TRY AGAIN AFTER FEW DAYS...");
        }
        else if(us[uid].nis>4)
        {
            System.out.println("CAN'T ISSUE MORE THAN 4 BOOKS...");
        }
        else if(us[uid].uid>userid)
        {
            System.out.println("USER DOES NOT EXIST...");
        }
        else if(bk[bd].bid==0)
        {
            System.out.println("BOOK DOES NOT EXIST...");
        }
    }
    void depositBook(int uid,int bd)
    {
        bk[bd].qty+=1;
        for(int i=0;i<4;i++)
        {
            if(us[uid].issuedbookid[i]==bd)
            {
                us[uid].issuedbookid[i]=0;
                us[uid].nis-=1;
                break;
            }
        }
        System.out.println(""+bk[bd].name+" BOOK WITH ID-"+bk[bd].bid+" DEPOSITED SUCCESSFULLY...\n\t---THANK YOU "+us[uid].name+". DO VISIT US AGAIN.---");
    }
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        task2_LMS ob=new task2_LMS();
        String adminpass="1";
        while(true)
        {
            System.out.println("\t------WELCOME TO PERIYAR CENTRAL LIBRARY------");
            //System.out.println("USERS CAN READ/ISSUE A BOOK, STAFFS CAN CREATE UPDATE AND DELETE");
            //MAY ADD LIBRARY RULES BELOW 
            System.out.println("PRESS 1 FOR USER AND 2 FOR LIBRARY STAFF/ADMIN");
            int z=sc.nextInt();
            if(z==1)
            {
                System.out.println("----YOU'VE ENTERED USER'S PANEL----");
                System.out.println("ENTER YOUR USER-ID FOR IDENTIFICATION: ");
                int m=sc.nextInt();
                if(m<userid)
                {
                    System.out.println("1. ISSUE A BOOK\n2. DEPOSIT ISSUED BOOK\n3. VIEW BOOK DETAIL BY BOOK-ID\n4. SELF-UPDATE DETAILS");
                    int f=sc.nextInt();
                    switch(f)
                    {
                        case 1:
                            while(true)
                            {
                                System.out.println("ENTER BOOK-ID TO ISSUE THAT BOOK : ");
                                int bd=sc.nextInt();
                                if(!(us[m].nis<=4))
                                {
                                    ob.issueBook(m,bd);
                                }
                                else
                                {
                                    System.out.println("CAN'T ISSUE MORE THAN 4 BOOKS...");
                                    break;
                                }
                                System.out.println("ENTER 1 TO ISSUE ANOTHER BOOK AND ENTER 0 TO EXIT.");
                                int v=sc.nextInt();
                                if(v==0)break;
                                else if(v==1)continue;
                            }
                            break;
                        case 2:
                            while(true)
                            {
                                System.out.println("ENTER BOOK-ID TO DEPOSIT THAT BOOK : ");
                                int bd=sc.nextInt();
                                ob.depositBook(m,bd);
                                System.out.println("ENTER 1 TO DEPOSIT ANOTHER BOOK AND ENTER 0 TO EXIT.");
                                int v=sc.nextInt();
                                if(v==0)break;
                                else if(v==1)continue;
                            }
                            break;
                        case 3:
                            while(true)
                            {
                                System.out.println("ENTER BOOK-ID OF BOOK FOR VIEWING IT'S DETAILS :");
                                int k=sc.nextInt();
                                try
                                {
                                    ob.getBook(k);
                                }
                                catch(Exception e)
                                {
                                    System.out.println("BOOK NOT FOUND!!");
                                };
                                System.out.println("ENTER 1 TO VIEW ANOTHER BOOK'S DETAILS AND 0 TO EXIT");
                                int v=sc.nextInt();
                                if(v==0)break;
                                else if(v==1)continue;
                            }
                            break;
                    }

                }
                else
                {
                    System.out.println("USER DOES NOT EXIST!! TRY AGAIN...");
                }
            }
            else if(z==2)
            {
                System.out.println("ENTER LIBRARY ADMIN PASSWORD :");
                String s=sc.next();
                if(s.compareTo(adminpass)==0)
                {
                    while(true)
                    {
                        System.out.println("ACCESS GRANTED.... ");
                        System.out.println("\t ---------YOU'VE ENTERED ADMIN PANEL---------");
                        System.out.println("1. ADD A BOOK \n2. REMOVE AN OUTDATED BOOK\n3. ADD NEW USER\n4. REMOVE AN INACTIVE USER\n5. VIEW USER DETAILS BY USER-ID");
                        System.out.println("ENTER YOUR CHOICE:");
                        int x=sc.nextInt();
                        switch(x)
                        {
                            case 1:
                                while(true)
                                {
                                    ob.addBook();
                                    System.out.println("ENTER 1 TO ADD ANOTHER BOOK AND 0 TO EXIT");
                                    int v=sc.nextInt();
                                    if(v==0)break;
                                    else if(v==1)continue;
                                }
                                break;
                            case 2:
                                while(true)
                                {
                                    System.out.println("ENTER BOOK-ID OF OUTDATED BOOK TO BE REMOVED : ");
                                    int bd=sc.nextInt();
                                    ob.removeBook(bd);
                                    System.out.println("ENTER 1 TO REMOVE ANOTHER OUTDATED BOOK AND 0 TO EXIT");
                                    int v=sc.nextInt();
                                    if(v==0)break;
                                    else if(v==1)continue;
                                }
                            break;
                            case 3:
                                while(true)
                                {
                                    ob.addUser();
                                    System.out.println("ENTER 1 TO ADD ANOTHER USER AND 0 TO EXIT");
                                    int v=sc.nextInt();
                                    if(v==0)break;
                                    else if(v==1)continue;
                                }
                                break;
                            case 4:
                                while(true)
                                {
                                    System.out.println("ENTER USER-ID OF INACTIVE USER TO BE REMOVED : ");
                                    int ud=sc.nextInt();
                                    ob.removeUser(ud);
                                    System.out.println("ENTER 1 TO REMOVE ANOTHER INACTIVE USER AND 0 TO EXIT");
                                    int v=sc.nextInt();
                                    if(v==0)break;
                                    else if(v==1)continue;
                                }
                                break;
                            case 5:
                                while(true)
                                {
                                    System.out.println("ENTER USER-ID OF USER FOR VIEWING IT'S DETAILS :");
                                    int k=sc.nextInt();
                                    try
                                    {
                                        ob.getUser(k);
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println("USER NOT FOUND!!");
                                    };
                                    System.out.println("ENTER 1 TO VIEW ANOTHER USER'S DETAILS AND 0 TO EXIT");
                                    int v=sc.nextInt();
                                    if(v==0)break;
                                    else if(v==1)continue;
                                }
                                break;
                        }
                        System.out.println("ENTER 1 TO REMAIN IN ADMIN PANEL AND 0 TO EXIT");
                        int a=sc.nextInt();
                        if(a==1)continue;
                        else if(a==0)break;
                    }
                }
                else
                {
                    System.out.println("INCORRECT PASSWORD.. TAKING YOU BACK TO MAIN MENU\n");
                }
            }
            else
            {
                System.out.println("INVALID CHOICE.... TRY AGAIN");
            }
        }
    }
}
class user
{
    String name="";
    int uid=0;
    int age=0;
    int nis=0;
    int issuedbookid[]={0,0,0,0};
    user(String name,int uid,int age,int issuedid,int[] ibid)
    {
        this.name=name;
        this.uid=uid;
        this.age=age;
        this.nis=issuedid;
        for(int i=0;i<this.nis;i++)
        {
            issuedbookid[i]=ibid[i];
        }
    }
}
class book 
{
    String isbn="";
    int bid=0;
    String name="";
    String author="";
    int qty=0;
    book(String ibn,int bd,String nm,String at,int qty)
    {
        this.isbn=ibn;
        this.bid=bd;
        this.name=nm;
        this.author=at;
        this.qty=qty;
    }
}
