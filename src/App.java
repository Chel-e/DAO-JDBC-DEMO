
import java.sql.Date;

import Dao.DaoFactory;
import Dao.SellerDao;
import entites.Department;
import entites.Seller;

public class App {
    public static void main(String[] args) throws Exception {
        Department obj = new Department(1, "Books");
        Seller seller = new Seller(21, "Bob", "Bob@gmail.com", new Date(0), 1200.0, obj);
       SellerDao sellerDao = DaoFactory.createSellerDao(); // dependency injection
        System.out.println(seller);
    }
}
