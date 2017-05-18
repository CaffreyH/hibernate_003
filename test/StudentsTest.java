//测试类
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Before
	public void init()
	{
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建会话
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}
	@After
	public void destory()
	{
		System.out.println("destoy");
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("销毁");
	}
	@Test
	public void testSaveStudents()
	{
		//生成学生对象

		Address address = new Address("123456","13008153775","成都");
		
		students s = new students();
		s.setSid(10);
		s.setBirthday(new Date());
		s.setSname("李华");
		s.setAddress(address);
		System.out.println("建表");
		session.save(s);//保存对象到数据库
		System.out.println("完成");
	}
	@Test
	public void testgetStudent()
	{
		students s = (students)session.get(students.class, 10);
		System.out.println(s);
	}
	@Test
	public void testUpdateStudeents()
	{
		students s = (students)session.get(students.class, 10);
		s.setSname("花花");
		session.update(s);
		System.out.println(s);
	}
	@Test
	public void testDeletStudents()
	{
		students s = (students)session.get(students.class, 10);
		session.delete(s);
	}
}
