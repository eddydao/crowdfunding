package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.OptionItemRepositoryCustom;
import com.dkthanh.demo.domain.dto.ItemDtoEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class OptionItemRepositoryImpl implements OptionItemRepositoryCustom {
    @Autowired
    private EntityManager em;

    @Override
    public List<ItemDtoEntity> findListItem(Map<String, Object> map) {
        Integer projectId = (Integer) map.get(Constant.PROJECT_KEY.PROJECT_ID);
        Integer optionId = (Integer) map.get(Constant.PROJECT_KEY.OPTION_ID);
        Integer itemId = (Integer) map.get(Constant.PROJECT_KEY.ITEM_ID);

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT\n" +
                "A.item_id,\n" +
                "A.project_id,\n" +
                "B.option_id,\n" +
                "A.item_name,\n" +
                "B.quantity\n" +
                "FROM\n" +
                "item A JOIN option_item B\n" +
                "ON A.item_id = B.item_id\n" +
                "WHERE 1 = 1\n");

        if(projectId != null ){
            sb.append("  AND A.project_id = :project_id\n");
        }
        if(optionId != null ){
            sb.append("  AND B.option_id = :option_id\n");
        }
        if(itemId != null ){
            sb.append("  AND A.item_id = :item_id\n");
        }

        Query sqlQuery = em.createNativeQuery(sb.toString(), ItemDtoEntity.ITEM_DTO_ENTITY_MAP);

        if(projectId != null ){
            sqlQuery.setParameter("project_id", projectId);
        }
        if(optionId != null ){
            sqlQuery.setParameter("option_id", optionId);
        }
        if(itemId != null ){
            sqlQuery.setParameter("item_id", itemId);
        }
        return sqlQuery.getResultList();
    }
}
