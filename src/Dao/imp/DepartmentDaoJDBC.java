package Dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.DepartmentDao;
import db.DbException;
import db.DbIntegrityException;
import entites.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
    
    private Connection conn;
    
    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO department " 
                + "(Name) "
                + "VALUES "
                + "(?)", java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getName());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                else { 
                    throw new DbException("Eroor, no rows affected");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    @Override
    public void update(Department obj) {
       PreparedStatement ps = null;
       try {
            ps = conn.prepareStatement("UPDATE department "
                + "SET Name = ?"
                + "WHERE Id = ?", java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
       } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
       }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM department WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
    }

    @Override
    public List<Department> findAll() {
       PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department ORDER BY Name");
			rs = st.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
    }
    
}
