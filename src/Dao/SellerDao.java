package Dao;

import java.util.List;
import entites.Seller;

public interface SellerDao {
    void nsert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findbyId(Integer id);
    List<Seller> findAll();
}