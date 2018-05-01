package com.dao.implement;

import com.bean.Employee;
import com.dao.IGenericDAO;
import com.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class HiderDao<T> implements IGenericDAO {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Class <T> clazz;

    @Override
    public T get(Integer id) throws SQLException {
        //return  sessionFactory.openSession().get(clazz,id);
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;


    }

    @Override
    public void saveOrUpdate(Object o) throws SQLException {

    }

    @Override
    public List getAll() throws SQLException {
        return null;
    }

    @Override
    public List<T> getByParam(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        T bean = get(id);


    }

    @Override
    public boolean checkUnique(Object o) throws SQLException {
        return false;
    }

    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    /*public abstract class AbstractHibernateDao< T extends Serializable > {

   private Class< T > clazz;

   @Autowired
   SessionFactory sessionFactory;



   public T findOne( long id ){
      return (T) getCurrentSession().get( clazz, id );
   }
   public List< T > findAll(){
      return getCurrentSession().createQuery( "from " + clazz.getName() ).list();
   }

   public void create( T entity ){
      getCurrentSession().persist( entity );
   }

   public void update( T entity ){
      getCurrentSession().merge( entity );
   }

   public void delete( T entity ){
      getCurrentSession().delete( entity );
   }
   public void deleteById( long entityId ) {
      T entity = findOne( entityId );
      delete( entity );
   }

   protected final Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }
}*/

}
