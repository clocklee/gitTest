package com.mis.infosys.persist.repositories.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.mis.infosys.persist.repositories.Dao.IHibernateBaseDao;
import com.mis.infosys.utils.PageFinder;
import com.mis.infosys.utils.ReflectionUtils;

//@Repository
public class RepositoryBase<T> implements IHibernateBaseDao<T> {

	private final Class<T> entityClass;
	
	private static Session session;
	
	private static Transaction transaction;
	
    @Autowired
    protected SessionFactory sessionFactory;  
    
    
    @SuppressWarnings("unchecked")
    public RepositoryBase() {
        this.entityClass = ReflectionUtils.getActualReflectArgumentClass(this.getClass(), 0);
    }
    
    @Override
	public void beginTransaction() {
//		 session = this.getSession();
		 transaction = session.beginTransaction();	
		 transaction.begin();
	}

	@Override
	public void endTransaction() {
		transaction.commit();
		session.close();
		
	}
    

    public T load(Serializable id) {
        Assert.notNull(id, "id is required");
       T entity =  (T) this.getSession().load(this.entityClass, id);
         return entity;
    }
    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    protected Session getSession() {    	
    	
    	
    		session = (Session)this.sessionFactory.openSession();
    	
    		
    	return session;
    }

    public T get(Serializable id) {
        Assert.notNull(id, "id is required");
        Session session = this.getSession();        
        T entity = session.get(this.entityClass, id);
        session.close();
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> get(Serializable[] ids) {
    	Session session = this.getSession();
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + this.entityClass.getName() + " as model where model.id in(:ids)";
        List<T> entities = session.createQuery(hql).setParameterList("ids", ids).list();
        session.close();
        return entities;
    }

    @SuppressWarnings("unchecked")
    public T get(String propertyName, Object value) {
        // Assert.hasText(propertyName, "propertyName must not be empty");
        // Assert.notNull(value, "value is required");
    	
        if (value == null) {
            return null;
        }
        Session session = this.getSession();
        
        String hql = "from " + this.entityClass.getName() + " as model where model." + propertyName + " = ?";
      
       T entity = (T) session.createQuery(hql).setParameter(0, value).uniqueResult();
       
       return entity;
       
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + this.entityClass.getName() + " as model where model." + propertyName + " = ?";
        return this.getSession().createQuery(hql).setParameter(0, value).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(String propertyName, Object[] values) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(values, "values is required");
        String hql = "from " + this.entityClass.getName() + " as model where model." + propertyName + " in(:values)";
        return this.getSession().createQuery(hql).setParameterList("values", values).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {    	
    	
		Session session1 = this.getSession();		
		String hql = "from " + this.entityClass.getName(); 
		return session1.createQuery(hql).list();
		//session.close();
		
    	
    }

    
    public Long getTotalCount() {
        String hql = "select count(*) from " + this.entityClass.getName();
        return (Long) this.getSession().createQuery(hql).uniqueResult();
    }

    
    public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(newValue, "newValue is required");
        if (newValue == oldValue || newValue.equals(oldValue)) {
            return true;
        }
        if (newValue instanceof String) {
            if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
                return true;
            }
        }
        T object = this.get(propertyName, newValue);
        return object == null;
    }

    
    public boolean isExist(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        T object = this.get(propertyName, value);
        return object != null;
    }

    
    public Serializable save(T entity) {
    	
        Assert.notNull(entity, "entity is required");
        
        //return this.getSession().save(entity);
        
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        Serializable obj = session.save(entity);
        session.flush();
        transaction.commit();
        session.close();        
        return obj;    	
    }

    /***
     * 注意:
     * 对象在一般情况下使用uodate更新相关数据
     * 如果有相互关联情况如外键的情况下需要使用merge方法合并session
     * @param entity 
     */
    public void update(T entity) {
       
    Assert.notNull(entity, "entity is required");
       Session session = this.getSession();
       Transaction transaction = session.beginTransaction();
       transaction.begin();
       session.update(entity);
       transaction.commit();
       session.close();
        
        //this.getSession().update(entity);
    }
    
    /***
     * 注意:
     * 对象在一般情况下使用uodate更新相关数据
     * 如果有相互关联情况如外键的情况下需要使用merge方法合并session
     * @param entity
     */
    public void merge(T entity) {
        
        Assert.notNull(entity, "entity is required");
           Session session = this.getSession();
           Transaction transaction = session.beginTransaction();
           transaction.begin();
           session.merge(entity);
           transaction.commit();
           session.close();
            
            //this.getSession().update(entity);
        }

    
    public T saveOrUpdate(T entity) {
    	 Assert.notNull(entity, "entity is required");
         Session session = this.getSession();        
         Transaction transaction = session.beginTransaction();
         transaction.begin();      
        this.getSession().saveOrUpdate(entity);
        session.flush();
        transaction.commit();       
        @SuppressWarnings("unused")
		FlushMode fm =  session.getFlushMode();
     
        session.close();
        return (T)entity;
    }

    
    public void delete(T entity) {
        Assert.notNull(entity, "entity is required");
        Session session = this.getSession();        
        Transaction transaction = session.beginTransaction();
        transaction.begin();      
        session.flush();
        session.delete(entity);
        transaction.commit(); 
        session.close();
    }

    
    public void delete(Serializable id) {
    	try
    	{
        Assert.notNull(id, "id is required");
        Session session = this.getSession();        
        
        Transaction transaction = session.beginTransaction();
        transaction.begin();        
        T entity = session.load(this.entityClass, id);
        Assert.notNull(entity,"error:when delete the entity is null");
        session.delete(entity);
        transaction.commit();       
       session.close();
    	}
    	catch(Exception ex)
    	{
    		System.err.println(ex.getMessage());
    	}
    }

    
    public void delete(Serializable[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        Session session = this.getSession();        
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        for (Serializable id : ids) {
        	 T entity = session.load(this.entityClass, id);
             session.delete(entity);
        }
        transaction.commit();       
        session.close();
    }

    
    public void delete(String propertyName, Object value) {
        Assert.notNull(propertyName, "propertyName is required");
        Assert.notNull(value, "value is required");
        String hql = "delete from " + this.entityClass.getName() + " as model where model." + propertyName + " = ?";
        this.getSession().createQuery(hql).setParameter(0, value).executeUpdate();
    }

    
    public int delete(Map<String, Object> conditions) throws Exception {
        if (null == conditions || conditions.isEmpty()) {
            throw new Exception("No conditions!");
        }

        StringBuffer hql = new StringBuffer("delete from " + this.entityClass.getName() + " as model ");
        if (null != conditions && conditions.size() > 0) {
            hql.append(" where ");

            int i = 1;
            Set<String> keySet = conditions.keySet();
            for (String key : keySet) {
                Object value = conditions.get(key);
                if (i > 1) {
                    hql.append(" AND ");
                }
                if (value instanceof Collection<?> || value instanceof Object[]) {
                    hql.append(" model." + key + " IN(:" + key + ") ");
                } else {
                    hql.append(" model." + key + " = :" + key + " ");
                }
                ++i;
            }
        }

        Query createQuery = this.getSession().createQuery(hql.toString());
        createQuery = this.setParameter(createQuery, conditions);
        return createQuery.executeUpdate();
    }

    
    public void evict(Object object) {
        Assert.notNull(object, "object is required");
        this.getSession().evict(object);
    }

    
    public void flush() {
        this.getSession().flush();
    }

    
    public void clear() {
        this.getSession().clear();
    }

    
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        Criteria criteria = this.createCriteria(criterions);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        } else {
            criteria.addOrder(Order.desc(orderBy));
        }

        return criteria;
    }

    
    public List<T> getAllByOrder(String orderBy, boolean isAsc, boolean useCache) {
    	
    	
    	
        return this.getLimitByOrder(orderBy, isAsc, -1, useCache);
    }

    @SuppressWarnings("unchecked")
    public List<T> getLimitByOrder(String orderBy, boolean isAsc, int limit, boolean useCache) {
        Assert.hasText(orderBy);

        Order order = isAsc ? Order.asc(orderBy) : Order.desc(orderBy);
        Criteria criteria = this.createCriteria();
        if (limit > 0) {
            criteria.setMaxResults(limit);
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(order).setCacheable(useCache);
        return criteria.list();
    }

    
    public int getRowCount(Criteria criteria) {
        criteria.setProjection(Projections.rowCount());
        Long totalRows = (Long) criteria.uniqueResult();
        return totalRows.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<T> getListByCriteria(Criteria criteria) {
        criteria = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getListByCriteria(Criteria criteria, int fistRow, int rowNum, boolean useCache) {
        criteria = criteria.setFirstResult(fistRow).setMaxResults(rowNum).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setCacheable(useCache);
        return criteria.list();
    }

    
    public PageFinder<T> pagedByCriteria(Criteria criteria, int pageNo, int pageSize) {
        int totalRows = this.getRowCount(criteria);
        criteria.setProjection(null);
        if (totalRows < 1) {
            PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows);
            finder.setData(new ArrayList<T>());
            return finder;
        } else {
            PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows);
            List<T> list = this.getListByCriteria(criteria, finder.getStartOfPage(), finder.getPageSize(), false);
            finder.setData(list);
            return finder;
        }
    }

    
    public Query createQuery(String hql, Object... values) {
        Assert.hasText(hql, "sql 不能为空");
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    
    public Query createQuery(String hql, Map<String, ?> values) {
        Assert.hasText(hql, "sql 不能为空");
        Query query = this.createQuery(hql);
        if (values != null) {
            query = this.setParameter(query, values);
        }
        return query;
    }

    @SuppressWarnings("unchecked")
    public T getObjectByHql(String hql, Map<String, Object> values) {
        Query query = this.createQuery(hql, values);
        return (T) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> getListByHql(String hql, Map<String, Object> values) {
        Query query = this.createQuery(hql);
        query = this.setParameter(query, values);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getListByHql(String hql, int firstRow, int maxNum, Map<String, Object> values) {
        Query query = this.createQuery(hql);
        query = this.setParameter(query, values);
        query.setFirstResult(firstRow);
        query.setMaxResults(maxNum);
        return query.list();
    }

    
    public PageFinder<T> pagedByHQL(String hql, int toPage, int pageSize, Map<String, Object> values) {
        String countQueryString = " select count(*) " + this.removeSelect(this.removeOrders(hql));
        List<T> countlist = this.getListByHql(countQueryString, values);
        Long totalCount = (Long) countlist.get(0);

        if (totalCount.intValue() < 1) {
            return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
        } else {
            final PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
            List<T> list = this.getListByHql(hql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    @SuppressWarnings("rawtypes")
    public List getListByHQL(String datasql, Map<String, Object> values) {
        Query dataQuery = this.createQuery(datasql, values);
        return dataQuery.list();
    }

    @SuppressWarnings("rawtypes")
    public List getListByHQL(String datasql, int firstRow, int maxNum, Map<String, Object> values) {
        Query dataQuery = this.createQuery(datasql, values);
        dataQuery.setFirstResult(firstRow);
        dataQuery.setMaxResults(maxNum);
        return dataQuery.list();
    }

    
    @SuppressWarnings("unchecked")
    public PageFinder<Object> pagedObjectByHQL(String countHql, String hql, int toPage, int pageSize, Map<String, Object> values) {
        Query query = this.createQuery(countHql, values);
        Long totalCount = (Long) query.uniqueResult();
        if (totalCount.intValue() < 1) {
            return new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
        } else {
            PageFinder<Object> finder = new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
            List<Object> list = this.getListByHQL(hql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    
    @SuppressWarnings("unchecked")
    public T getObjectByHql(String hql, Object... values) {
        Query query = this.createQuery(hql, values);
        List<T> list = query.list();
        if (null != list && list.size() > 0) {
            T first = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) != first) {
                    throw new NonUniqueResultException(list.size());
                }
            }
            return first;
        }
        return null;
    }

    
    @SuppressWarnings("unchecked")
    public List<T> getListByHql(String hql, Object... values) {
        Query dataQuery = this.createQuery(hql, values);
        return dataQuery.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getListByHql(String hql, int firstRow, int maxNum, Object... values) {
        Query query = this.createQuery(hql, values);
        query.setFirstResult(firstRow);
        query.setMaxResults(maxNum);
        return query.list();
    }

    
    public PageFinder<T> pagedByHQL(String hql, int toPage, int pageSize, Object... values) {
        String countQueryString = " select count(*) " + this.removeSelect(this.removeOrders(hql));
        List<T> countlist = this.getListByHql(countQueryString, values);
        Long totalCount = (Long) countlist.get(0);

        if (totalCount.intValue() < 1) {
            return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
        } else {
            final PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
            List<T> list = this.getListByHql(hql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    
    public SQLQuery createSQLQuery(String sql, Object... values) {
        Assert.hasText(sql, "sql 不能为空");
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    
    public SQLQuery createSQLQuery(String sql, Map<String, ?> values) {
        Assert.hasText(sql, "sql 不能为空");
        Query query = this.createSQLQuery(sql);
        if (values != null) {
            query = this.setParameter(query, values);
        }
        return (SQLQuery) query;
    }

    @SuppressWarnings("unchecked")
    public List<Object> getListBySQL(String datasql, Map<String, Object> values) {
        SQLQuery dataQuery = this.createSQLQuery(datasql, values);
        return dataQuery.list();
    }

    @SuppressWarnings("unchecked")
    public List<Object> getListBySQL(String datasql, int firstRow, int maxNum, Map<String, Object> values) {
        SQLQuery dataQuery = this.createSQLQuery(datasql, values);
        dataQuery.setFirstResult(firstRow);
        dataQuery.setMaxResults(maxNum);
        return dataQuery.list();
    }

    
    public PageFinder<Object> pagedObjectBySQL(String countsql, String datasql, int toPage, int pageSize, Map<String, Object> values) {
        SQLQuery query = this.createSQLQuery(countsql, values);
        Long totalCount = Long.parseLong(query.uniqueResult().toString());
        if (totalCount.intValue() < 1) {
            return new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
        } else {
            PageFinder<Object> finder = new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
            List<Object> list = this.getListBySQL(datasql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    /**
     * 取得对象的主键值,辅助函数.
     */
    @SuppressWarnings("unused")
    private Serializable getId(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Assert.notNull(entity);
        return (Serializable) PropertyUtils.getProperty(entity, this.getIdName());
    }

    /**
     * 取得对象的主键名,辅助函数.
     */
    private String getIdName() {
        ClassMetadata meta = this.sessionFactory.getClassMetadata(this.entityClass);
        Assert.notNull(meta, "Class " + this.entityClass + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName();
        Assert.hasText(idName, this.entityClass.getSimpleName() + " has no identifier property define.");
        return idName;
    }

    /**
     * hql 设置参数
     * 
     * @Title: setParameter
     * @Description: TODO
     * @param query
     * @param map
     * @return
     * @throws
     * @author: yong
     * @date: 2012-12-17下午05:56:15
     */
    private Query setParameter(Query query, Map<String, ?> map) {
        if (map != null && !map.isEmpty()) {
            Set<String> keySet = map.keySet();
            for (String string : keySet) {
                Object obj = map.get(string);
                // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (obj instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    query.setParameterList(string, (Object[]) obj);
                } else {
                    query.setParameter(string, obj);
                }
            }
        }
        return query;
    }

    /**
     * 去除hql的select 子句，未考虑union的情况用于pagedQuery.
     * 
     * @param hql
     * @return
     */
    private String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     * 
     * @param hql
     * @return
     */
    private String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

	
}
