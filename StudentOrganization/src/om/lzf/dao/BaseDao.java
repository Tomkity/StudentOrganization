package om.lzf.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 公共的DAO类(不会具体操作哪一张表的)
 * 
 * @author lujun
 * 
 */
public class BaseDao extends HibernateDaoSupport {
	// 实体类的完整类名(包名.类名)
	private String poName;

	public void setPoName(String poName) {
		this.poName = poName;
	}

	/**
	 * 查询所有实体对象(所有属性都查)
	 * 
	 * @return 实体对象的集合
	 */
	public List findAll() {
		String hql = "from " + poName;
		HibernateTemplate template = getHibernateTemplate();
		List list = template.find(hql);
		return list;
	}

	/**
	 * 根据HQL语句和参数值数组查询所有对象或数组集合
	 * 
	 * @param hql语句
	 * @param hql语句中参数值的数组
	 * @return 实体对象或数组的集合
	 */
	public List findObjects(String hql, Object... params) {
		HibernateTemplate template = getHibernateTemplate();
	//	org.springframework.orm.hibernate3.support.OpenSessionInViewFilter
		List list = template.find(hql, params);
		return list;
	}

	/**
	 * 根据实体类类名和主键ID查询实体对象
	 * 
	 * @param 主键id
	 * @return 实体对象
	 */
	public Object findById(Serializable id) {
		HibernateTemplate template = getHibernateTemplate();
		Object object = template.get(poName, id);
		return object;
	}

	/**
	 * 添加实体对象
	 * 
	 * @param 实体对象
	 * @return true表示成功，false表示失败
	 */
	public boolean save(Object entity) {
		HibernateTemplate template = getHibernateTemplate();
		try {
			template.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据主键id删除实体对象
	 * 
	 * @param 主键id
	 * @return true表示成功，false表示失败
	 */
	public boolean deleteById(Serializable id) {
		HibernateTemplate template = getHibernateTemplate();
		// 查出要删除的对象
		Object obj = this.findById(id);
		try {
			template.delete(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改实体对象
	 * 
	 * @param 实体对象
	 * @return true表示成功，false表示失败
	 */
	public boolean update(Object entity) {
		HibernateTemplate template = getHibernateTemplate();
		try {
			template.update(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
   /**
    * 如果是修改部分属性(除主键以外的)或者是不根据主键来删除，就调用这个方法
    * @param hql
    * @param params
    * @return
    */
	public boolean update(final String hql, final Object... params) {
		boolean flag = false;
		HibernateTemplate template = getHibernateTemplate();
		flag = template.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				// 得到Query对象
				Query query = session.createQuery(hql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}

				int m = query.executeUpdate();
				if (m > 0) {
					return true;
				} else {
					return false;
				}

			}

		});
		return flag;

	}

	/**
	 * 根据hql语句查询返回唯一结果的方法(统计函数Max,SUM,Count用得到)
	 * 
	 * @param hql
	 * @return
	 */
	public Object getUnqueResult(String hql, Object... params) {
		Object obj = this.findObjects(hql, params).get(0);
		return obj;

	}

	/**
	 * 根据查询语句和当前页页号，每页条数返回当前页的数据集合
	 * 
	 * @param hql
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List getPageData(final String hql, final int pageSize,
			final int pageNo) {
		HibernateTemplate template = getHibernateTemplate();
		List list = template.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				// 得到Query对象
				Query query = session.createQuery(hql);
				// 设置当前页第1条记录的索引
				query.setFirstResult((pageNo - 1) * pageSize);
				// 设置每页查出几条记录
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return list;
	}
}
