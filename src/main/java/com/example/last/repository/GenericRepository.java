package com.example.last.repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.snc.common.persistence.PaginatedList;
import es.snc.common.persistence.filter.FilterWithDateTime;
import es.snc.dp.entity.model.AbstractRequest;
import es.snc.dp.entity.model.AbstractTimeRequest;
import es.snc.dp.entity.util.ModelUtil;
import es.snc.dp.filter.RequestFilter;
import es.snc.dp.repository.dao.ITimeRequestDao;
import es.snc.dp.type.vf.ValidationStatus;
import es.snc.common.persistence.filter.Filter;

@Repository //al instanciar se especifica el tipo que luego puedes usar en los metodos.
public class GenericRepository<T> {

@Autowired
protected EntityManager entityManager;

    private static final String PROPERTY_STATUS = "validationStatus";
    private static final String PROPERTY_CLOSE_DATE = "closeDate";
    private static final String PROPERTY_UNTIL_DATE = "untilDate";
    private static final String PROPERTY_CREATED_BY = "createdBy";
    private static final String PROPERTY_FROM_DATE = "fromDate";
    private static final String PROPERTY_CLIENT_ID = "clientId";
    private static final String PROPERTY_EMPLOYEE = "employee";
    private static final String PROPERTY_COMPANY = "company";
    private static final String PROPERTY_YEAR = "year";
    private static final String PROPERTY_ID = "id";

    private final ITimeRequestDao<AbstractTimeRequest> timeRequestDao;

    public TimeRequestDaoImpl(ITimeRequestDao<AbstractTimeRequest> timeRequestDao) {
        this.timeRequestDao = timeRequestDao;
    }

	public PaginatedList<AbstractTimeRequest> findAllByFilter(FilterWithDateTime<RequestFilter> filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AbstractTimeRequest> criteriaQuery = criteriaBuilder.createQuery(AbstractTimeRequest.class);
		Root<AbstractTimeRequest> timeRequest = criteriaQuery.from(AbstractTimeRequest.class);

        List<Predicate> preds = timeRequestDao.buildPredicates(filter, AbstractTimeRequest.class, Boolean.TRUE);

        if (StringUtils.hasText(filter.getFromDateTime())
                || StringUtils.hasText(filter.getUntilDateTime())) {

            preds.add(timeRequest.get(PROPERTY_ID).in(
                    filterByDates(
                    filter.getFromDateTimeFormatted(),
                    filter.getUntilDateTimeFormatted())));
        }

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(timeRequest.get(PROPERTY_ID)));

        return timeRequestDao.doPaginatedList(preds, filter, orderList, AbstractTimeRequest.class);
	}

    private List<Long> filterByDates(ZonedDateTime fromDate, ZonedDateTime untilDate){

        StringBuilder qlString = new StringBuilder();
		qlString.append("SELECT sdtr.id ");
		qlString.append("FROM AbstractTimeRequest sdtr ");
		qlString.append("WHERE 1=1 ");

		if (fromDate != null) {

			qlString.append("AND ( ");
			qlString.append("sdtr.untilDate >= :fromDateTime ");
			qlString.append(") ");
		}

		if (untilDate != null) {

			qlString.append("AND ( ");
			qlString.append("sdtr.fromDate < :untilDateTime ");
			qlString.append(") ");
		}

		TypedQuery<Long> query = entityManager.createQuery(qlString.toString(), Long.class);

		if (fromDate != null)
			query.setParameter("fromDateTime", fromDate);

		if (untilDate != null)
			query.setParameter("untilDateTime", untilDate);

        return query.getResultList();
    }

    @Override
    public List<Predicate> buildPredicates(FilterWithDateTime<RequestFilter> filter, Class<T> clazz, Boolean idAndStatus){

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> request = criteriaQuery.from(clazz);

        List<Predicate> preds = new ArrayList<>();

        RequestFilter requestFilter = filter.getContent();

        if (requestFilter.getId() != null) {

			preds.add(criteriaBuilder.equal(request.<Long>get(PROPERTY_ID), requestFilter.getId()));
		}

        if (requestFilter.getClientId() != null) {

            preds.add(criteriaBuilder.equal(request.get(PROPERTY_CLIENT_ID), requestFilter.getClientId()));
        }

        if (requestFilter.getEmployeeId() != null) {

            preds.add(criteriaBuilder.equal(request.get(PROPERTY_EMPLOYEE).get(PROPERTY_ID), requestFilter.getEmployeeId()));
        }

        if (requestFilter.getCompanyId() != null) {

            preds.add(criteriaBuilder.equal(request.get(PROPERTY_COMPANY).get(PROPERTY_ID), requestFilter.getCompanyId()));
        }

        if (requestFilter.getYear() != null) {

            preds.add(criteriaBuilder.equal(request.get(PROPERTY_YEAR), requestFilter.getYear()));
        }
 
        if(idAndStatus){

            if (requestFilter.getIds() != null) {

                preds.add(request.get(PROPERTY_ID).in(requestFilter.getIds()));
            }

            if(requestFilter.getStatus() != null
                    && !requestFilter.getStatus().isEmpty()) {
                
                preds.add(request.<ValidationStatus>get(PROPERTY_STATUS).in(requestFilter.getStatus()));
            }
        }
        
        if(clazz.getClass().equals(AbstractRequest.class)){

            preds.addAll(getRequestPreds(requestFilter, request));

        } else {

            preds.addAll(getByDates(filter, request));
        }

        return preds;

    }

    private List<Predicate> getRequestPreds(RequestFilter requestFilter, Root<T> request){
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        List<Predicate> preds = new ArrayList<>();

        if (requestFilter.getFromCloseDateTime() != null) {

			preds.add(criteriaBuilder.and(
					criteriaBuilder.isNotNull(request.get(PROPERTY_CLOSE_DATE)),
					criteriaBuilder.greaterThanOrEqualTo(
							request.get(PROPERTY_CLOSE_DATE), requestFilter.getFromCloseDateTimeFormatted())
			));
		}

		if (requestFilter.getUntilCloseDateTime() != null) {

			preds.add(criteriaBuilder.and(
					criteriaBuilder.isNotNull(request.get(PROPERTY_CLOSE_DATE)),
					criteriaBuilder.lessThanOrEqualTo(
							request.get(PROPERTY_CLOSE_DATE), requestFilter.getUntilCloseDateTimeFormatted())
			));
		}
        
        if (requestFilter.getType() != null) {
            
            preds.add(criteriaBuilder.equal(request.type(), ModelUtil.getRequestTypeFromTypes(Arrays.asList(requestFilter.getType()))));
		}
        
        if (requestFilter.getTypes() != null && !requestFilter.getTypes().isEmpty()) {

            preds.add(request.type().in(ModelUtil.getRequestTypeFromTypes(requestFilter.getTypes())));
        }
        
        if (requestFilter.getCreatedBy() != null) {

            preds.add(criteriaBuilder.equal(request.get(PROPERTY_CREATED_BY), requestFilter.getCreatedBy()));
        }

        return preds;
    }

    private List<Predicate> getByDates(FilterWithDateTime<RequestFilter> filter, Root<T> root){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        List<Predicate> preds = new ArrayList<>();

        if (StringUtils.hasText(filter.getFromDateTime())) {
        
            ZonedDateTime fromDateTime = filter.getFromDateTimeFormatted();
            
            preds.add(
                    criteriaBuilder.greaterThan(
                            root.<ZonedDateTime>get(PROPERTY_UNTIL_DATE), 
                            fromDateTime));
        }
        
        if (StringUtils.hasText(filter.getUntilDateTime())) {
        
            ZonedDateTime untilDateTime = filter.getUntilDateTimeFormatted();
            
            preds.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.<ZonedDateTime>get(PROPERTY_FROM_DATE), 
                            untilDateTime));
        }

        return preds;
    }

    @Override
    public PaginatedList<T> doPaginatedList(List<Predicate> preds, Filter<RequestFilter> filter, List<Order> orderList, Class<T> clazz){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> request = criteriaQuery.from(clazz);

		criteriaQuery.select(request);
		criteriaQuery.where(preds.toArray(new Predicate[0]));
		criteriaQuery.orderBy(orderList);

		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

		if (filter.getPage() != null && filter.getSize() != null) {

			query.setFirstResult(filter.getOffset());
			query.setMaxResults(filter.getSize());
		}

		CriteriaQuery<Long> criteriaQueryCount = criteriaBuilder.createQuery(Long.class);
		criteriaQueryCount.select(criteriaBuilder.count(criteriaQueryCount.from(clazz)));
		criteriaQueryCount.where(preds.toArray(new Predicate[0]));

		long rownum = entityManager.createQuery(criteriaQueryCount).getSingleResult();

		PaginatedList<T> paginatedList = new PaginatedList<>();
		paginatedList.setResults(query.getResultList());
		paginatedList.setRownum(rownum);

		if (filter.getPage() != null && filter.getSize() != null) {

			paginatedList.setPage(filter.getPage());
			paginatedList.setSize(filter.getSize());
		}

		return paginatedList;
    }
    
}
