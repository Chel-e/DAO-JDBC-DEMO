
import Dao.DaoFactory;
import Dao.SellerDao;
import entites.Seller;

public class App {
    public static void main(String[] args) throws Exception {
        SellerDao sellerDao = DaoFactory.createSellerDao(); // dependency injection
        System.out.println("=== FindById test ===");
        Seller seller = sellerDao.findbyId(3);
        System.out.println(seller);
    }
}
