package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import Model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("bookService")
@Transactional
public class BookService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Book> getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Book");
		return query.list();
	}
	
	public List<Book> sortAll(){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.addOrder(Order.asc("bookName"));
		return criteria.list();
	}
	
	public List<Book> Des(){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.addOrder(Order.desc("bookName"));
		return criteria.list();
	}
	
	public Book get(Integer bookId){
		Session session = sessionFactory.getCurrentSession();
		return (Book)session.get(Book.class,bookId);
	}
	
	public void add(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
	}
	
	public void delete(Integer bookId){
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book)session.get(Book.class,bookId);
		session.delete(book);
	}

	public void edit(Book book){
		Session session = sessionFactory.getCurrentSession();
		Book book1 = (Book)session.get(Book.class,book.getBookId());
		
		book1.setBookName(book.getBookName());
		session.save(book1);
	}
	
	public List<Book> searchBook(String bookName){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like("bookName",bookName+"%") );
		return criteria.list();
	}
	
	public List<Book> search(String bookName,String chapterName){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like("bookName",bookName+"%") );
		criteria.createAlias("chapter","chapter",Criteria.LEFT_JOIN);
		criteria.add(Restrictions.like("chapter.chapterName",chapterName+"%"));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Book> books = criteria.list();
		
		return books;		
	}
	
}
