package com.yyf.controller;

import com.yyf.dao.DepartmentDao;
import com.yyf.dao.EmployeeDao;
import com.yyf.entities.Department;
import com.yyf.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class EmpController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    //查询所有员工
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees=employeeDao.getAll();
        model.addAttribute("emps",employees);
       return "emp/list";
    }
    //添加员工
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("员工添加"+employee);
        employeeDao.save(employee);
        return  "redirect:/emps";
    }
    // 来到修改页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Map<String,Object> map){
        Employee employee=employeeDao.get(id);
        map.put("emp",employee);
        Collection<Department> departments=departmentDao.getDepartments();
        map.put("depts",departments);
        //add页面是修改添加二合一页面
        return "emp/add";
    }
    //员工修改
    @PutMapping("/emp")
    public String update(Employee employee){
        System.out.println("修改"+employee);
        employeeDao.save(employee);
        return  "redirect:/emps";
    }
    //员工删除
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return  "redirect:/emps";
    }
    //来到员工添加页面
    @GetMapping("/add")
    public String add(Map<String,Object> map){
        Collection<Department> departments=departmentDao.getDepartments();
        map.put("depts",departments);
        return "emp/add";
    }


}
