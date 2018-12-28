package com.cbb.Controller;

import com.cbb.bean.Employee;
import com.cbb.dao.DepartmentDao;
import com.cbb.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sun.invoke.util.VerifyAccess;

import javax.naming.ldap.PagedResultsControl;
import javax.validation.Valid;
import java.util.Map;

/**
 * @ClassName EmployeeController
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/6/28 18:16
 * @Version:
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

   /* //注解@InitBinder
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("lastName"); //不给lastName 赋值
    }*/
   /* 数据得到数据*/
    @ModelAttribute
    public void getEmployee(@RequestParam(value="id",required=false) Integer id,
                            Map<String, Object> map){
        if(id != null){
            map.put("employee", employeeDao.get(id));
        }
    }
    /* 修改数据 */
    @RequestMapping(value="/emp", method=RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /**/
    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map){
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }
    /*删除操作*/
    @RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
    /*保存操作
    * BindingResult 哪个字段异常 异常详情*/
    @RequestMapping(value ="/emp", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result,Map<String,Object> map){
        System.out.println("save:"+employee);
        if (result.getErrorCount()>0){
            System.out.println("出错了");
            for (FieldError error:result.getFieldErrors()){
                System.out.println(error.getField()+":"+error.getDefaultMessage());
            }
            map.put("departments",departmentDao.getDepartments());
            return "input";
        }
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /*插入数据*/
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map){
        map.put("departments", departmentDao.getDepartments());
        map.put("employee",new Employee());
        return "input";
    }

    /* 查询所有员工*/
    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
        map.put("employees",employeeDao.getAll());
        return "list";
    }
}
