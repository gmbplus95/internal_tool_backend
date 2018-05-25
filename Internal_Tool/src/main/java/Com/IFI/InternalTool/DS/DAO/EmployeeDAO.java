package Com.IFI.InternalTool.DS.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import Com.IFI.InternalTool.DS.Model.Employee;
@Repository
public interface EmployeeDAO{
	List<Employee> getAllEmployee();

}
