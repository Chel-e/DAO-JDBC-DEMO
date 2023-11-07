
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.DaoFactory;
import Dao.DepartmentDao;
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
        Seller seller2 = new Seller(null, "Emme", "Emme@gmail.com", new Date(), 1200.00, dep);
        sellerDao.insert(seller2);
        System.out.println("\n=== Update test ===");
        Seller seller3 = new Seller(3, "Eve", "Eve@gmail.com", new Date(), 6200.00, dep);
        sellerDao.update(seller3);
        System.out.println("\n=== Delete test ===");
        sellerDao.deleteById(seller3.getId());
        System.out.println("\n=== FindAll test ===");
        List<Seller> listAll = new ArrayList<>();
        listAll = sellerDao.findAll();
        for (Seller obj : listAll) {
            System.out.println(obj);
        }
        System.out.println("======= Department Test =======");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("\n=== TEST 1: insert =======");
		Department newDepartment = new Department(null, "Music");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New id: " + newDepartment.getId());
    
        System.out.println("=== TEST 2: findById =======");
		Department Dep = departmentDao.findById(1);
		System.out.println(Dep);

        System.out.println("\n=== TEST 3: update =======");
		Department dep2 = departmentDao.findById(1);
		dep2.setName("Food");
		departmentDao.update(dep2);
		System.out.println("Update completed");
        
        System.out.println("\n=== TEST 4: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = 7;
		departmentDao.deleteById(id);
		System.out.println("Delete completed");

		System.out.println("\n=== TEST 5: findAll =======");
		List<Department> List = departmentDao.findAll();
		for (Department d : List) {
			System.out.println(d);
		}

    }
}
