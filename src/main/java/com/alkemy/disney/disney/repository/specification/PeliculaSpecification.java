
package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFilterDTO filtersDto) {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDto.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtersDto.getName().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDto.getGenre())) {
                Long generoId = Long.parseLong(filtersDto.getGenre());
                predicates.add(
                        criteriaBuilder.equal(root.get("generoId"), generoId)
                );
            }

            //remove duplicates
            query.distinct(true);

            String orderByField = "titulo";
            query.orderBy(
                    filtersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
