package Com.IFI.InternalTool.DS.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Com.IFI.InternalTool.DS.DAO.VacationDAO;
import Com.IFI.InternalTool.DS.Model.Vacation;
import Com.IFI.InternalTool.DS.Model.Vacation_approved;
import Com.IFI.InternalTool.DS.Model.Vacation_type;
import Com.IFI.InternalTool.DS.Model.SearchModel.VacationSearch;
@Repository("VacationDAO")
@Transactional
public class VacationDAOImpl implements VacationDAO{
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Override
	public List<Vacation> getAllVacationByEmp(long employee_id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "FROM Vacation where employee_id=:employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("employee_id", employee_id);
		List<Vacation> list=query.list();
		session.close();
		return list;
	}
	@Override
	public boolean saveVacation(Vacation vacation) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction tx = null;
		tx=session.beginTransaction();
		session.saveOrUpdate(vacation);
		session.close();
		tx.commit();
		return true;
	}
	@Override
	public boolean deleteVacation(long vacation_id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction tx = null;
		tx=session.beginTransaction();
		String hql = "Delete from Vacation where vacation_id=:vacation_id and is_updateable=true";
		Query query = session.createQuery(hql);
		query.setParameter("vacation_id", vacation_id);
		query.executeUpdate();
		tx.commit();
		session.close();
		return true;
	}
	@Override
	public Vacation getVacationById(long vacation_id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "FROM Vacation where vacation_id=:vacation_id";
		Query query = session.createQuery(hql);
		query.setParameter("vacation_id", vacation_id);
		Vacation v=(Vacation) query.uniqueResult();
		return v;
	}
	@Override
	public void saveVacationApproved(Vacation_approved vacation_approved) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction tx = null;
		tx=session.beginTransaction();
		session.saveOrUpdate(vacation_approved);
		session.close();
		tx.commit();
	}
	@Override
	public List<Vacation_type> getAllVacationType() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "from Vacation_type";
		Query query = session.createQuery(hql);
		List<Vacation_type> list=query.list();
		session.close();
		return list;
	}
	
	//search manager page
	@Override
	public List<Vacation> searchVacation(Long manager_id, VacationSearch vacationSearch) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "Select v from Vacation v INNER JOIN Employee AS e ON v.employee_id= e.employee_id INNER JOIN Project AS p ON v.project_id=p.project_id INNER JOIN Project_manager pm ON (pm.employee_id=v.employee_id and pm.project_id=v.project_id and pm.priority=v.status) ";
		hql+="WHERE (:emp_name IS NULL OR e.fullname LIKE CONCAT('%', :emp_name, '%')) ";
		hql+="AND (:status =0 or v.status=:status) ";
		hql+="AND (:pro_name IS NULL OR p.name LIKE CONCAT('%', :pro_name, '%')) ";
		hql+="AND ((:from_date IS NULL and ( :to_date IS NOT NULL and (:to_date> v.to_date) or (:to_date<v.to_date and :to_date>=v.from_date))) "; 
		hql+="or (:to_date IS NULL and (:from_date IS NOT NULL and :from_date <= v.to_date)) ";
		hql+="or (:from_date >= v.from_date and :from_date <= v.to_date and :to_date >= v.from_date and :to_date <= v.to_date and :from_date <= :to_date) ";
		hql+="or (:from_date <= v.from_date and :to_date >= v.from_date and :to_date <= v.to_date and :from_date <= :to_date) ";
		hql+="or (:from_date >= v.from_date and :from_date <= v.to_date and :to_date >= v.to_date and :from_date <= :to_date)";
		hql+="or (:from_date <= v.from_date and :to_date >= v.to_date and :from_date <= :to_date)";
		hql+="or (:from_date IS NULL and :to_date IS NULL)) ";
		hql+="AND (pm.manager_id=:manager_id)";
		Query query = session.createQuery(hql);
		query.setParameter("manager_id", manager_id);
		query.setParameter("emp_name", vacationSearch.getEmp_name());
		query.setParameter("pro_name", vacationSearch.getPro_name());
		query.setParameter("from_date", vacationSearch.getFrom_date());
		query.setParameter("to_date", vacationSearch.getTo_date());
		query.setParameter("status", vacationSearch.getStatus());
		List<Vacation> list=query.list();
		session.close();
		return list;
		
	}
	
	//search employee page
	@Override
	public List<Vacation> searchVacationP2(Long employee_id,VacationSearch vacationSearch) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "Select v from Vacation v INNER JOIN Employee AS e ON v.employee_id= e.employee_id INNER JOIN Project AS p ON v.project_id=p.project_id ";
		hql+="WHERE (:pro_name IS NULL OR p.name LIKE CONCAT('%', :pro_name, '%')) ";
		hql+="AND (:status =0 or v.status=:status) ";
		hql+="AND ((:from_date IS NULL and ( :to_date IS NOT NULL and (:to_date> v.to_date) or (:to_date<v.to_date and :to_date>=v.from_date))) "; 
		hql+="or (:to_date IS NULL and (:from_date IS NOT NULL and :from_date <= v.to_date)) ";
		hql+="or (:from_date >= v.from_date and :from_date <= v.to_date and :to_date >= v.from_date and :to_date <= v.to_date and :from_date <= :to_date) ";
		hql+="or (:from_date <= v.from_date and :to_date >= v.from_date and :to_date <= v.to_date and :from_date <= :to_date) ";
		hql+="or (:from_date >= v.from_date and :from_date <= v.to_date and :to_date >= v.to_date and :from_date <= :to_date)";
		hql+="or (:from_date <= v.from_date and :to_date >= v.to_date and :from_date <= :to_date)";
		hql+="or (:from_date IS NULL and :to_date IS NULL))";
		hql+="AND (v.employee_id=:employee_id)";
		Query query = session.createQuery(hql);
		query.setParameter("employee_id", employee_id);
		query.setParameter("pro_name", vacationSearch.getPro_name());
		query.setParameter("from_date", vacationSearch.getFrom_date());
		query.setParameter("to_date", vacationSearch.getTo_date());
		query.setParameter("status", vacationSearch.getStatus());
		List<Vacation> list=query.list();
		session.close();
		return list;
		
	}
	@Override
	public int getMaxPriority(long vacation_id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "Select MAX(t.priority) from Vacation_approved t where vacation_id=:vacation_id";
		Query query = session.createQuery(hql);
		query.setParameter("vacation_id", vacation_id);
		int max=(int) query.uniqueResult();
		return max;
	}
	@Override
	public int getPriority(long manager_id,long vacation_id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "Select t.priority from Vacation_approved t where manager_id=:manager_id and vacation_id=:vacation_id";
		Query query = session.createQuery(hql);
		query.setParameter("manager_id", manager_id);
		query.setParameter("vacation_id", vacation_id);
		int p=(int) query.uniqueResult();
		return p;
	}
	@Override
	public List<Vacation> getAllVacationByEmp2(long employee_id,long manager_id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "Select t From Vacation t INNER JOIN Project_manager AS pm ON (t.employee_id=pm.employee_id and t.project_id=pm.project_id)  where  (t.employee_id=:employee_id and pm.manager_id=:manager_id and t.status=pm.priority)";
		Query query = session.createQuery(hql);
		query.setParameter("employee_id", employee_id);
		query.setParameter("manager_id", manager_id);
		List<Vacation> list=query.list();
		session.close();
		return list;
	}

}

