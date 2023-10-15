package Dao;

import java.util.List;

import entites.Department;

public interface DepartmentDao {
    void nsert(Department obj);
    void update(Department obj);
    void deleteById(Integer id);
    Department findbyId(Integer id);
    List<Department> findAll();
}
