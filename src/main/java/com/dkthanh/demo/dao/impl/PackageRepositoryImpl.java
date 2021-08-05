package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.PackageRepositoryCustom;
import com.dkthanh.demo.domain.PledgeReportEntity;
import com.dkthanh.demo.domain.dto.PackageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PackageRepositoryImpl implements PackageRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<PledgeReportEntity> getPledgeByWeeks(Integer projectId) {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "SELECT YEAR(timestamp) AS year"
                + ", WEEK(timestamp) as week_number"
                + ", SUM(pledged) as pledge"
                + " FROM package"
                + " WHERE project_id = :id"
                + " GROUP BY year, week_number"
                + " ORDER BY year, week_number ASC"
        );

        Query sqlQuery = em.createNativeQuery(sb.toString(), PledgeReportEntity.PLEDGE_REPORT_MAP);
        sqlQuery.setParameter("id", projectId);
        return sqlQuery.getResultList();
    }

    @Override
    public List<PledgeReportEntity> getOptionPercentage(Integer projectId) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "SELECT"
                + " B.option_id"
                + " , B.option_name"
                + " , FORMAT (B.option_choosed_qty / A.investor_count * 100, 0) AS option_percent"
                + " FROM"
                + " project A"
                + " JOIN"
                + " ( SELECT"
                + " A.project_id, A.option_id, B.option_name"
                + " , COUNT(user_id) AS option_choosed_qty"
                + " FROM"
                + " package A JOIN option B on A.project_id = B.project_id AND A.option_id = B.option_id "
                + " WHERE A.project_id = :id"
                + " GROUP BY A.option_id"
                + " ) B"
                + " ON A.project_id = B.project_id"
        );
        Query sqlQuery = em.createNativeQuery(sb.toString(), PledgeReportEntity.OPTION_PERCENT_REPORT_MAP);
        sqlQuery.setParameter("id", projectId);
        return sqlQuery.getResultList();
    }

    @Override
    public List<PledgeReportEntity> getPackageInfoByProjectId(Integer projectId) {
        StringBuilder sb = new StringBuilder();
        sb.append(  "SELECT A.timestamp, B.username, D.email, C.option_name, A.pledged\n" +
                    "FROM package A\n" +
                    "JOIN user B ON A.user_id = B.id\n" +
                    "JOIN user_detail D ON B.id = D.user_id\n" +
                    "JOIN option C on A.option_id = C.option_id\n" +
                    "WHERE \n" +
                    "A.project_id = :id\n" +
                    "ORDER BY A.timestamp ASC");
        Query sqlQuery = em.createNativeQuery(sb.toString(), PledgeReportEntity.TABLE_REPORT);
        sqlQuery.setParameter("id", projectId);
        return sqlQuery.getResultList();
    }

    @Override
    public int customSavePackage(PackageDto dto) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "INSERT INTO package(user_id, project_id, option_id, pledged, timestamp)" +
                "VALUES (:userId, :projectId, :optionId, :pledged, :timestamp)"
        );

        Query sql = em.createNativeQuery(sb.toString());
        sql.setParameter("userId", dto.getUserId());
        sql.setParameter("projectId", dto.getProjectId());
        sql.setParameter("optionId", dto.getOptionId());
        sql.setParameter("pledged", dto.getPledge());
        sql.setParameter("timestamp", dto.getTimestamp());
        return sql.executeUpdate();
    }
}
