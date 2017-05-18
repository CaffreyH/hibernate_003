//������
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
		//�������ö���
		Configuration config = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//�����Ự��������
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//�����Ự
		session = sessionFactory.openSession();
		//��������
		transaction = session.beginTransaction();
	}
	@After
	public void destory()
	{
		System.out.println("destoy");
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("����");
	}
	@Test
	public void testSaveStudents()
	{
		//����ѧ������

		Address address = new Address("123456","13008153775","�ɶ�");
		
		students s = new students();
		s.setSid(10);
		s.setBirthday(new Date());
		s.setSname("�");
		s.setAddress(address);
		System.out.println("����");
		session.save(s);//����������ݿ�
		System.out.println("���");
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
		s.setSname("����");
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
