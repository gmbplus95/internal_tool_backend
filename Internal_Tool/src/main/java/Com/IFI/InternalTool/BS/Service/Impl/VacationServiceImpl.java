package Com.IFI.InternalTool.BS.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.IFI.InternalTool.BS.Service.VacationService;
import Com.IFI.InternalTool.DS.DAO.VacationDAO;
import Com.IFI.InternalTool.DS.Model.Vacation;
import Com.IFI.InternalTool.DS.Model.Vacation_approved;
@Service("VacationService")
public class VacationServiceImpl implements VacationService{
	@Autowired
	VacationDAO vacationDAO;
	@Override
	public List<Vacation> getAllVacationByEmp(long employee_id) {
		return vacationDAO.getAllVacationByEmp(employee_id);
	}
	@Override
	public boolean saveVacation(Vacation vacation) {
		return vacationDAO.saveVacation(vacation);
	}
	@Override
	public boolean deleteVacation(long vacation_id) {
		return vacationDAO.deleteVacation(vacation_id);
	}
	@Override
	public Vacation getVacationById(long vacation_id) {
		return vacationDAO.getVacationById(vacation_id);
	}
	@Override
	public void saveVacationApproved(Vacation_approved vacation_approved) {
		vacationDAO.saveVacationApproved(vacation_approved);
	}

}
