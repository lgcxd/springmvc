package com.cbb.dao;

import com.cbb.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DepartmentDao
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/6/28 17:50
 * @Version:
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    static{
        departments = new HashMap<Integer, Department>();

        departments.put(101, new Department(101, "D-AA"));
        departments.put(102, new Department(102, "D-BB"));
        departments.put(103, new Department(103, "D-CC"));
        departments.put(104, new Department(104, "D-DD"));
        departments.put(105, new Department(105, "D-EE"));
        departments.put(106, new Department(106, "D-FF"));
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }
}
