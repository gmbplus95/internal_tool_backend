package Com.IFI.InternalTool.BS.Service;

import java.util.List;

import Com.IFI.InternalTool.DS.Model.Employee;
import Com.IFI.InternalTool.DS.Model.Group_ifi;

public interface EmployeeService {

	List<Employee>  getAllEmployee();
	Long saveEmployee(Employee employee);
	Long deleteEmployee(long employee_id);
	Employee getEmployeeById(long employee_id);
	List<Group_ifi>  getAllGroup();
	List<Long> getEmployeeByManager(long manager_id);

}
