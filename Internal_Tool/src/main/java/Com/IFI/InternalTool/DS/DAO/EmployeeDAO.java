package Com.IFI.InternalTool.DS.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import Com.IFI.InternalTool.DS.Model.Employee;
import Com.IFI.InternalTool.DS.Model.Group_ifi;
@Repository
public interface EmployeeDAO{
	List<Employee> getAllEmployee(int page,int pageSize,String sortedColumn,Boolean desc);
	Long saveEmployee(Employee employee);
	Long deleteEmployee(long employee_id);
	Employee getEmployeeById(long employee_id);
	List<Group_ifi> getAllGroup();
	List<Long> getEmployeeByManager(long manager_id);
}
