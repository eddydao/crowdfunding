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
        Integer teamId = (Integer) map.get(Constant.PROJECT_KEY.TEAM_ID);


        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT\n" +
                        "  A.project_id,\n" +
                        "  A.project_name,\n" +
                        "  A.team_id,\n" +
                        "  E.team_name,\n"+
                        "  A.project_short_des,\n" +
                        "  A.start_date,\n" +
                        "  A.end_date,\n" +
                        "  A.goal,\n" +
                        "  A.pledged,\n" +
                        "  A.investor_count,\n" +
                        "  A.project_status_id,\n" +
                        "  A.recommended,\n" +
                        "  0 as day_left,\n"+
                        "  B.material_id,\n" +
                        "  B.description,\n" +
                        "  B.path,\n" +
                        "  C.category_id,\n" +
                        "  C.category_name,\n" +
                        "  0.0 as percent_pledged\n"+
                        "FROM\n" +
                        "  project A,\n" +
                        "  material B,\n" +
                        "  category C,\n" +
                        "  status D,\n" +
                        "  team E\n"+
                        "WHERE\n" +
                        "  A.project_id = B.project_id\n" +
                        "  AND A.category_id = C.category_id\n" +
                        "  AND A.team_id = E.team_id\n"+
                        "  AND A.project_status_id = D.project_status_id\n");

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
            sql.append("  AND D.project_status_id = :status\n" );
        }
        //get project list with team id
        if(teamId != null){
            sql.append("  AND E.team_id = :team_id\n" );
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
        if(teamId != null){
            sqlQuery.setParameter("team_id", teamId);
        }

        return sqlQuery.getResultList();
    }
}
