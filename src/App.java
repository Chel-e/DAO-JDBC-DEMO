
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.DaoFactory;
import Dao.SellerDao;
import entites.Department;
import entites.Seller;

public class App {
    public static void main(String[] args) throws Exception {
        SellerDao sellerDao = DaoFactory.createSellerDao(); // dependency injection
        System.out.println("=== FindById test ===");
        Seller seller = sellerDao.findbyId(3);
        System.out.println(seller);
        System.out.println("\n=== FindByDepartment test ===");
        List<Seller> list = new ArrayList<>();
        Department dep = new Department(2, "Electronics");
        list = sellerDao.findByDepartment(dep);
        for (Seller obj : list) {
            System.out.println(obj);
        }
        System.out.println("\n=== Insert test ===");
        Seller seller2 = new Seller(11, "Emme", "Emme@gmail.com", new Date(), 1200.00, dep);
        sellerDao.insert(seller2);
        System.out.println("\n=== FindAll test ===");
        List<Seller> listAll = new ArrayList<>();
        listAll = sellerDao.findAll();
        for (Seller obj : listAll) {
            System.out.println(obj);
        }
    }
}
