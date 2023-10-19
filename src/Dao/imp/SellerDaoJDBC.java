package Dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Dao.SellerDao;
import db.DbException;
import entites.Department;
import entites.Seller;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;
    public SellerDaoJDBC (Connection conn) {
        this.conn = conn;
    }
    @Override
    public void nsert(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nsert'");
    }

    @Override
    public void update(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Seller findbyId(Integer id) {
       PreparedStatement st = null;
       ResultSet rs = null;
       try {
        st = conn.prepareStatement( 
            "SELECT seller.*,department.Name as DepName "
            + "FROM seller INNER JOIN department "
            + "ON seller.DepartmentId = department.Id "
            + "WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) { // true se existir um seller com esse id 
                Department dep = instantiateDepartment(rs);
                Seller obj = instantiateSeller(rs, dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
        throw new DbException(e.getMessage());
       }

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = null;
        obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }
    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    private Department instantiateDepartment(ResultSet rs) throws SQLException { 
        Department dep = null;
        dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
}
