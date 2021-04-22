sql.append(
                "SELECT\n" +
                        "  A.project_id,\n" +//
                        "  A.project_name,\n" +//
                        "  A.user_id,\n" +//
                        "  CONCAT(F.last_name, F.first_name) as user_full_name,\n"+//
                        "  A.project_short_des,\n" +//
                        "  A.start_date,\n" +//
                        "  A.end_date,\n" +//
                        "  A.goal,\n" +//
                        "  A.pledged,\n" +//
                        "  A.investor_count,\n" +//
                        "  A.recommended,\n" +//
                        "  A.country_id,\n" +//
                        "  B.material_id,\n" +
                        "  B.description,\n" +
                        "  B.path,\n" +
                        "  A.project_status_id as status_id,\n"+
                        "  D.name as status_name\n"+
                        "  C.id as category_id,\n" +
                        "  C.name as category_name,\n" +
                        "  0.0 as percent_pledged,\n"+
                        "  0 as day_left,\n"+
                        "FROM\n" +
                        "  project A left join material B on A.project_id = B.project_id\n" +
                        "  JOIN category C ON A.category_id = C.id\n" +
                        "  JOIN status D ON A.project_status_id = D.id\n" +
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
            sql.append("  AND D.id = :status\n" );
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