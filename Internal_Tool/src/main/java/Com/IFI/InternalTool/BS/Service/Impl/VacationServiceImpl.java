package Com.IFI.InternalTool.BS.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.IFI.InternalTool.BS.Service.VacationService;
import Com.IFI.InternalTool.DS.DAO.VacationDAO;
import Com.IFI.InternalTool.DS.Model.Vacation;
import Com.IFI.InternalTool.DS.Model.Vacation_approved;
import Com.IFI.InternalTool.DS.Model.Vacation_type;
import Com.IFI.InternalTool.DS.Model.SearchModel.VacationSearch;
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
	@Override
	public List<Vacation_type> getAllVacationType() {
		return vacationDAO.getAllVacationType();
	}
	@Override
	public List<Vacation> searchVacationP2(Long employee_id,VacationSearch vacationSearch) {
		return vacationDAO.searchVacationP2(employee_id,vacationSearch);
	}
	@Override
	public List<Vacation> searchVacation(Long manager_id,VacationSearch vacationSearch) {
		return vacationDAO.searchVacation(manager_id,vacationSearch);
	}
	@Override
	public int getMaxPriority(long vacation_id) {
		return vacationDAO.getMaxPriority(vacation_id);
	}
	@Override
	public int getPriority(long manager_id, long vacation_id) {
		return vacationDAO.getPriority(manager_id, vacation_id);
	}
	@Override
	public List<Vacation> getAllVacationByEmp2(long vacation_id,long manager_id) {
		return vacationDAO.getAllVacationByEmp2(vacation_id,manager_id);
	}

}
