package javaapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
@EnableWebMvc
public class ReqController {
	
	@RequestMapping(path = "/ncreate")
	@ResponseBody
	public String createnote(@RequestParam("note") String str , HttpServletRequest request , HttpServletResponse response) {
		
		Product product = new Product();
		product.setName(str);
		
		Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
		ServiceRegistry reg=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf=con.buildSessionFactory(reg);
		Session session=sf.openSession();
		
		Transaction tx=session.beginTransaction();
		session.save(product);
		tx.commit();
		session.close();
		
		return "Saved successfully";
	}

	@RequestMapping(path = "/nread")
	@ResponseBody
	public List<Product> getAll() {
		
		Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
		ServiceRegistry reg=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf=con.buildSessionFactory(reg);
		Session session=sf.openSession();
		
		Query q = session.createQuery("from Product");
		
		return q.list();
		
	}
	
	@RequestMapping(path = "/nupdate")
	@ResponseBody
	public String updatee(@RequestParam("noteid") int i , @RequestParam("note") String str , HttpServletRequest request , HttpServletResponse response) {
		
		Product product = new Product();
		
		Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
		ServiceRegistry reg=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf=con.buildSessionFactory(reg);
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		
		product =(Product) session.get(Product.class,i);
		product.setName(str);
		
		tx.commit();
		session.save(product);
		session.close();
		
		return "updated successfully";
	}
	
	@RequestMapping(path = "/ndelete")
	@ResponseBody
	public String deletee(@RequestParam("idd") int i, HttpServletRequest request , HttpServletResponse response) {
		
		Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
		ServiceRegistry reg=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf=con.buildSessionFactory(reg);
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		
		Query q = session.createQuery("delete Product where id = :i");
		q.setParameter("i" , i);
		
		q.executeUpdate();
		tx.commit();
		
		return "Selected note deleted successfully";
	}
}
