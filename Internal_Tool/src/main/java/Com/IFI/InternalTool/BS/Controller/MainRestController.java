package Com.IFI.InternalTool.BS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Com.IFI.InternalTool.BS.Service.EmployeeService;
import Com.IFI.InternalTool.BS.Service.ProjectService;
import Com.IFI.InternalTool.DS.Model.Employee;
import Com.IFI.InternalTool.DS.Model.Group_ifi;
import Com.IFI.InternalTool.DS.Model.Project;
import Com.IFI.InternalTool.DS.Model.Project_manager;

@RestController
public class MainRestController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ProjectService projectService;
	/*-----------Begin Employee MainRestController--------*/
	
	//get all employee data
	@RequestMapping("/getAllEmployee")
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	//get all Group
	@RequestMapping("/getAllGroup")
	public List<Group_ifi> getAllGroup(){
		return employeeService.getAllGroup();
	}
	//get employee by id
	@RequestMapping("/getEmployeeById")
	public @ResponseBody Payload getEmployeeById(@RequestParam long employee_id){
		 Payload message=new Payload();
		 employeeService.getEmployeeById(employee_id);
			 message.setDescription("Get employee successfully");
			 message.setCode("CODE OK!");
			 message.setStatus("OK!");
			 message.setData(employeeService.getEmployeeById(employee_id));
		 return message;
	}
	//save or update employee
	
	@RequestMapping("/saveEmployee")
	public @ResponseBody Payload saveEmployee(@RequestBody Employee employee){
		 Payload message=new Payload();
		 if(employeeService.saveEmployee(employee)) {
			 message.setDescription("Save or Update employee successfully");
			 message.setCode("CODE OK!");
			 message.setStatus("OK!");
			 message.setData(employee);
		 }
		 else {
			 message.setStatus("Error!");
		 };
		 return message;
	}
	
	//delete employee by id
	
	@RequestMapping("/deleteEmployee")
	public @ResponseBody Payload deleteEmployee(@RequestParam long employee_id){
		 Payload message=new Payload();
		 if(employeeService.deleteEmployee(employee_id)) {
			 message.setDescription("Delete employee successfully");
			 message.setCode("CODE OK!");
			 message.setStatus("OK!");
			 message.setData("");
		 }
		 else {
			 message.setStatus("Error!");
		 };
		 return message;
	}
	/*-----------End Employee MainRestController--------*/
	
	
	/*-----------Begin Project MainRestController--------*/
	
	//get all project data
		@RequestMapping("/getAllProject")
		public List<Project> getAllProject(){
			return projectService.getAllProject();
		}
		//get project by id
		@RequestMapping("/getProjectById")
		public @ResponseBody Payload getProjectById(@RequestParam long project_id){
			 Payload message=new Payload();
			 projectService.getProjectById(project_id);
				 message.setDescription("Get project successfully");
				 message.setCode("CODE OK!");
				 message.setStatus("OK!");
				 message.setData( projectService.getProjectById(project_id));
			 return message;
		}
		
		//save or update project
		
		@RequestMapping("/saveProject")
		public @ResponseBody Payload saveProject(@RequestBody Project project){
			 Payload message=new Payload();
			 if(projectService.saveProject(project)) {
				 message.setDescription("Save or Update project successfully");
				 message.setCode("CODE OK!");
				 message.setStatus("OK!");
				 message.setData(project);
			 }
			 else {
				 message.setStatus("Error!");
			 };
			 return message;
		}
		
		//delete project by id
		
		@RequestMapping("/deleteProject")
		public @ResponseBody Payload deleteProject(@RequestParam long project_id){
			 Payload message=new Payload();
			 if(projectService.deleteProject(project_id)) {
				 message.setDescription("Delete project successfully");
				 message.setCode("CODE OK!");
				 message.setStatus("OK!");
				 message.setData("");
			 }
			 else {
				 message.setStatus("Error!");
			 };
			 return message;
		}
		//get project id from project_manager table by employee_id
		@RequestMapping("/getProjectIdByEmp")
		public List<Project_manager> getProjectIdByEmp(@RequestParam long employee_id){
			return projectService.getProjectIdByEmp(employee_id);
		}
		
		/*-----------End Project MainRestController--------*/	
}
