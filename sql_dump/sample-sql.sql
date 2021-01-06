// SELECT recommended project detail
SELECT
TOP(8)
  A.project_id,
  A.project_name,
  A.project_team_id,
  A.user_id,
  A.project_short_des,
  A.start_date,
  A.end_date,
  A.goal,
  A.pledged,
  A.investor_count,
  A.project_status_id,
  A.recommended,
  B.material_id,
  B.description,
  B.path,
  C.category_id,
  C.category_name
FROM
  project A,
  material B,
  category C,
  status D
WHERE
  A.project_id = B.project_id
  AND A.category_id = C.category_id
  AND A.project_status_id = D.project_status_id
  AND A.recommended = 1
  AND D.project_status_id = 3
  GROUP BY project_investor_count
  ORDER BY project_investor_count ASC;
// ------------------------------------
// select popular project
SELECT
  TOP(1)
  A.project_id,
  A.project_name,
  A.project_team_id,
  A.user_id,
  A.project_short_des,
  A.start_date,
  A.end_date,
  A.goal,
  A.pledged,
  A.investor_count,
  A.project_status_id,
  A.recommended,
  B.material_id,
  B.description,
  B.path,
  C.category_id,
  C.category_name
FROM
  project A,
  material B,
  category C
WHERE
  A.project_id = B.project_id
  AND A.category_id = C.category_id
GROUP BY A.investor_count
ORDER BY A.investor_count;

//------------------------------------
// select project invesment option
SELECT A.project_id,
  B.option_id,
  B.option_name,
  B.option_description,
  B.fund_max,
  B.fund_min
FROM 
  project A,
  invesment_option B,
WHERE 
  A.project_id = B.project_id















