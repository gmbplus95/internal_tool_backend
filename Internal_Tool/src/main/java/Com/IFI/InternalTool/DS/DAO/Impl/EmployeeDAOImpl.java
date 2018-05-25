package Com.IFI.InternalTool.DS.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Com.IFI.InternalTool.DS.DAO.EmployeeDAO;
import Com.IFI.InternalTool.DS.Model.Employee;
@Repository("EmployeeDAO")
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Override
	public List<Employee> getAllEmployee() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		//query.setParameter("userId", userId);
		List<Employee> list = query.list();
		session.close();
		return list;
	}
	
	

}
