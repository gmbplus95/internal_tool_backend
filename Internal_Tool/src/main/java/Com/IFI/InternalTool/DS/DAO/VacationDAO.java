package Com.IFI.InternalTool.DS.DAO;

import java.util.List;

import Com.IFI.InternalTool.DS.Model.Vacation;
import Com.IFI.InternalTool.DS.Model.Vacation_approved;

public interface VacationDAO {
		List<Vacation> getAllVacationByEmp(long employee_id);
		boolean saveVacation(Vacation vacation);
		boolean deleteVacation(long vacation_id);
		Vacation getVacationById(long vacation_id);
		void saveVacationApproved(Vacation_approved vacation_approved);
		
}
