
import java.util.ArrayList;
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
    }
}
