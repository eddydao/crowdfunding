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
                        "  A.project_status_id,\n" +
                        "  A.recommended,\n" +
                        "  0 as day_left,\n"+
                        "  B.material_id,\n" +
                        "  B.description,\n" +
                        "  B.path,\n" +
                        "  C.id as category_id,\n" +
                        "  C.name as category_name,\n" +
                        "  0.0 as percent_pledged,\n"+
                        "  D.id as status_id,\n"+
                        "  D.name as status_name\n"+
                        "FROM\n" +
                        "  project A left join material B on A.project_id = B.project_id\n" +
                        "  JOIN category C ON A.category_id = C.id\n" +
                        "  JOIN status D ON A.project_status_id = D.id\n" +
                        "  JOIN user E ON A.user_id = E.id\n" +
                        "  JOIN user_detail F ON E.id = F.user_id\n"+
                        "WHERE 1 = 1\n" 