package com.cbb.dao;

import com.cbb.bean.Department;
import com.cbb.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EmployeeDao
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/6/28 17:48
 * @Version:
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<>();
        employees.put(1001,new Employee(1001,"E-AA","1@qq.com",1,new Department(101,"D-AA")));
        employees.put(1002,new Employee(1002,"E-BB","2@qq.com",0,new Department(102,"D-BB")));
        employees.put(1003,new Employee(1003,"E-CC","3@qq.com",1,new Department(103,"D-CC")));
        employees.put(1004,new Employee(1004,"E-DD","4@qq.com",1,new Department(104,"D-DD")));
        employees.put(1005,new Employee(1005,"E-EE","5@qq.com",0,new Department(105,"D-EE")));
        employees.put(1006,new Employee(1006,"E-FF","6@qq.com",0,new Department(106,"D-FF")));
    }
    private static Integer initId = 1007;
    /*新增或修改*/
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
