package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.ProjectRepositoryCustom;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<ProjectFullInfoEntity> getProjectListWithDetail(Map<String, Object> map) {

        Integer projectId = (Integer) map.get(Constant.PROJECT_KEY.PROJECT_ID);
        Integer isRecommended = (Integer) map.get(Constant.PROJECT_KEY.IS_RECOMMENDED);
        Integer projectStatus = (Integer) map.get(Constant.PROJECT_KEY.PROJECT_STATUS);
        Integer userId = (Integer) map.get(Constant.PROJECT_KEY.USER_ID);


        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT\n" +
                        "  A.project_id,\n" +
                        "  A.project_name,\n" +
                        "  A.user_id,\n" +
                        "  CONCAT(F.last_name, F.first_name) as user_full_name,\n"+
                        "  A.project_short_des,\n" +
                        "  A.start_date,\n" +
                        "  A.end_date,\n" +
                        "  A.goal,\n" +
                        "  A.pledged,\n" +
                        "  A.investor_count,\n" +
                        "  A.recommended,\n" +
                        "  A.country_id,\n" +
                        "  B.material_id,\n" +
                        "  B.description,\n" +
                        "  B.path,\n" +
                        "  A.status_id as status_id,\n"+
                        "  D.name as status_name,\n"+
                        "  C.id as category_id,\n" +
                        "  C.name as category_name,\n" +
                        "  0.0 as percent_pledged,\n"+
                        "  0 as day_left,\n"+
                        "  story_id as story_id\n"+
                        "FROM\n" +
                        "  project A left join material B on A.project_id = B.project_id\n" +
                        "  JOIN category C ON A.category_id = C.id\n" +
                        "  JOIN status D ON A.status_id = D.status_id\n" +
                        "  JOIN story G ON A.project_id = G.project_id\n" +
                        "  JOIN user E ON A.user_id = E.id\n" +
                        "  JOIN user_detail F ON E.id = F.user_id\n"+
                        "WHERE 1 = 1\n" );

        // get project detail with id
        if(projectId != null ){
            sql.append("  AND A.project_id = :id\n");
        }
        //get project detail with recommended status
        if(isRecommended != null){
            sql.append("  AND A.recommended = :recommended");
        }
        //get project list with specific status
        if(projectStatus != null){
            sql.append("  AND D.status_id = :status\n" );
        }
        //get project list with team id
        if(userId != null){
            sql.append("  AND E.id = :user_id\n" );
        }



        // group by
        sql.append("GROUP BY A.investor_count\n" +
                        "ORDER BY A.investor_count;"
        );
        Query sqlQuery = em.createNativeQuery(sql.toString(), ProjectFullInfoEntity.PROJECT_FULL_INFOR_MAP);
        if(projectId != null ){
            sqlQuery.setParameter("id", projectId);
        }
        if(isRecommended != null){
            sqlQuery.setParameter("recommended", isRecommended);
        }
        if(projectStatus != null){
            sqlQuery.setParameter("status", projectStatus);
        }
        if(userId != null){
            sqlQuery.setParameter("user_id", userId);
        }

        return sqlQuery.getResultList();
    }
}
