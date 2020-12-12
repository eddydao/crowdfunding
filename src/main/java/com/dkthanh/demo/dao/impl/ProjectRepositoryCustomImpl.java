package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.ProjectRepositoryCustom;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Optional<List<ProjectFullInfoEntity>> getPopularProject() {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT\n" +
                    "TOP(8)\n" +
                    "  A.project_id,\n" +
                    "  A.project_name,\n" +
                    "  A.project_team_id,\n" +
                    "  A.user_id,\n" +
                    "  A.project_short_des,\n" +
                    "  A.start_date,\n" +
                    "  A.end_date,\n" +
                    "  A.goal,\n" +
                    "  A.pledged,\n" +
                    "  A.investor_count,\n" +
                    "  A.project_status_id,\n" +
                    "  A.recommended,\n" +
                    "  B.material_id,\n" +
                    "  B.description,\n" +
                    "  B.path,\n" +
                    "  C.category_id,\n" +
                    "  C.category_name\n" +
                "FROM\n" +
                    "  project A,\n" +
                    "  material B,\n" +
                    "  category C,\n" +
                    "  status D\n" +
                "WHERE\n" +
                    "  A.project_id = B.project_id\n" +
                    "  AND A.category_id = C.category_id\n" +
                    "  AND A.project_status_id = D.project_status_id\n" +
                    "  AND A.recommended = 1\n" +
                    "  AND D.project_status_id = 3\n" +
                "GROUP BY project_investor_count\n" +
                "ORDER BY project_investor_count ASC;"

        );
        return null;
    }

    @Override
    public Optional<ProjectFullInfoEntity> getProjectDetail(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<ProjectFullInfoEntity> getRecommendedProject() {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT\n" +
                    "  TOP(1)\n" +
                    "  A.project_id,\n" +
                    "  A.project_name,\n" +
                    "  A.project_team_id,\n" +
                    "  A.user_id,\n" +
                    "  A.project_short_des,\n" +
                    "  A.start_date,\n" +
                    "  A.end_date,\n" +
                    "  A.goal,\n" +
                    "  A.pledged,\n" +
                    "  A.investor_count,\n" +
                    "  A.project_status_id,\n" +
                    "  A.recommended,\n" +
                    "  B.material_id,\n" +
                    "  B.description,\n" +
                    "  B.path,\n" +
                    "  C.category_id,\n" +
                    "  C.category_name\n" +
                "FROM\n" +
                    "  project A,\n" +
                    "  material B,\n" +
                    "  category C,\n" +
                    "  status D\n" +
                "WHERE\n" +
                    "  A.project_id = B.project_id\n" +
                    "  AND A.category_id = C.category_id\n" +
                    "  AND A.project_status_id = D.project_status_id\n" +
                    "  AND D.project_status_id = 3\n" +
                "GROUP BY A.investor_count\n" +
                "ORDER BY A.investor_count;"
        );
        Query sqlQuery = em.createNativeQuery(sql.toString(), ProjectFullInfoEntity.PROJECT_FULL_INFOR_MAP);

        return sqlQuery.getResultList();
    }
}
