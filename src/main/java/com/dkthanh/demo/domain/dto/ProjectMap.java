package com.dkthanh.demo.domain.dto;

import com.dkthanh.demo.domain.ProjectEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = ProjectFullInfoEntity.PROJECT_FULL_INFOR_MAP,
        classes = {
            @ConstructorResult(
                targetClass = ProjectEntity.class,
                columns = {
                        @ColumnResult(name = "project_id", type = Integer.class),
                        @ColumnResult(name = "project_name", type = String.class),
                        @ColumnResult(name = "project_team_id", type = Integer.class),
                        @ColumnResult(name = "user_id", type = Integer.class),
                        @ColumnResult(name = "project_short_des", type = String.class),
                        @ColumnResult(name = "start_date", type = Timestamp.class),
                        @ColumnResult(name = "end_date", type = Timestamp.class),
                        @ColumnResult(name = "goal", type = Double.class),
                        @ColumnResult(name = "pledged", type = Double.class),
                        @ColumnResult(name = "investor_count", type = String.class),
                        @ColumnResult(name = "project_status_id", type = Integer.class),
                        @ColumnResult(name = "recommended", type = Integer.class),
                        @ColumnResult(name = "day_left", type = Integer.class),
                        @ColumnResult(name = "material_id", type = Integer.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "path", type = String.class),
                        @ColumnResult(name = "category_id", type = Integer.class),
                        @ColumnResult(name = "category_name", type = String.class),
                        @ColumnResult(name = "percent_pledged", type = Double.class)
            })
        }
    )
})

@Entity
public class ProjectMap {
    @Id
    Integer id;
}
