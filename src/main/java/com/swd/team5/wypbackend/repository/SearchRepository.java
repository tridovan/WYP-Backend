package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Brand;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.repository.criteria.SearchCriteria;
import com.swd.team5.wypbackend.repository.criteria.SearchCriteriaConsumer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> advanceSearchProduct(int pageNo, int pageSize, String brand, String sort, List<SearchCriteria> criteriaList){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        Predicate predicate = criteriaBuilder.conjunction();
        SearchCriteriaConsumer queryConsumer = new SearchCriteriaConsumer(criteriaBuilder, predicate, root);

        if(Strings.isNotBlank(brand)){
            //luôn luôn phải set qua root
            Join<Brand, Product> brandProductJoin = root.join("brand");
            Predicate brandPredicate = criteriaBuilder.like(brandProductJoin.get("name"), "%" + brand + "%");
            query.where(predicate, brandPredicate);
        }else{
            //queryConsumer.accept(criteria);
            criteriaList.forEach(queryConsumer);
            predicate = queryConsumer.getPredicate();
            //jsonignore in entity to solve infinite loop
            query.where(predicate);
        }

            if (Strings.isNotBlank(sort)) {
                // field:acs|desc
                Pattern pattern = Pattern.compile("(\\w+?)(:)(asc|desc)");
                Matcher matcher = pattern.matcher(sort);
                if (matcher.find()) {
                    String columnName = matcher.group(1);
                    //luôn phải có field tham chiếu
                    if (matcher.group(3).equalsIgnoreCase("desc")) {
                        query.orderBy(criteriaBuilder.desc(root.get(columnName)));
                    }else {
                        query.orderBy(criteriaBuilder.asc(root.get(columnName)));
                    }
                }
            }

        return entityManager.createQuery(query).setFirstResult(pageNo).setMaxResults(pageSize).getResultList();
    }
}
