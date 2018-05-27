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

}
