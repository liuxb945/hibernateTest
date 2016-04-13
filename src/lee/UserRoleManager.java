package lee;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.crazyit.app.domain.Role;
import org.crazyit.app.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class UserRoleManager {

	@Test
    public void insert(){
        Session session=HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        try {
            transaction.begin();
            User user=new User();
            user.setName("张三");
            
            Role role=new Role();
            role.setName("管理员");
            Set<Role> roles=new HashSet<Role>();
            roles.add(role);
            user.setRoles(roles);
            
            session.persist(user);
            
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
    
    
    @Test
    public void select(){
        Session session=HibernateUtil.currentSession();
        User user=(User) session.get(User.class, 1);
        System.out.println(user.getName());
        Set<Role> roles=user.getRoles();
        for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
            Role role = (Role) iterator.next();
            System.out.println(role.getName());
        }
    }
}
