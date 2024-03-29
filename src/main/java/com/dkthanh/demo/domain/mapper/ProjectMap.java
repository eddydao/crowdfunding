package com.dkthanh.demo.domain.mapper;

import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = ProjectFullInfoEntity.PROJECT_FULL_INFOR_MAP,
        classes = {
            @ConstructorResult(
                targetClass = ProjectFullInfoEntity.class,
                columns = {
                        @ColumnResult(name = "project_id", type = Integer.class),
                        @ColumnResult(name = "project_name", type = String.class),
                        @ColumnResult(name = "user_id", type = Integer.class),
                        @ColumnResult(name = "user_full_name", type = String.class),
                        @ColumnResult(name = "project_short_des", type = String.class),
                        @ColumnResult(name = "start_date", type = OffsetDateTime.class),
                        @ColumnResult(name = "end_date", type = OffsetDateTime.class),
                        @ColumnResult(name = "goal", type = Long.class),
                        @ColumnResult(name = "pledged", type = Long.class),
                        @ColumnResult(name = "investor_count", type = Integer.class),
                        @ColumnResult(name = "recommended", type = Integer.class),
                        @ColumnResult(name = "thumbnail_path", type = String.class),
                        @ColumnResult(name = "country_id", type = Integer.class),
                        @ColumnResult(name = "country_name", type = String.class),
                        @ColumnResult(name = "status_id", type = Integer.class),
                        @ColumnResult(name = "status_name", type = String.class),
                        @ColumnResult(name = "category_id", type = Integer.class),
                        @ColumnResult(name = "category_name", type = String.class),
                        @ColumnResult(name = "percent_pledged", type = Double.class),
                        @ColumnResult(name = "day_left", type = Integer.class),
                        @ColumnResult(name = "submit_date", type = OffsetDateTime.class),
                        @ColumnResult(name = "is_editable", type = Integer.class),
                        @ColumnResult(name = "is_ready", type = Integer.class)
            })
        }
    ),
    @SqlResultSetMapping(
            name = ProjectFullInfoEntity.PROJECT_PLEDGED_BY_USER,
            classes = {
                    @ConstructorResult(
                            targetClass = ProjectFullInfoEntity.class,
                            columns = {
                                    @ColumnResult(name = "project_id", type = Integer.class),
                                    @ColumnResult(name = "project_name", type = String.class),
                                    @ColumnResult(name = "user_id", type = Integer.class),
                                    @ColumnResult(name = "user_full_name", type = String.class),
                                    @ColumnResult(name = "status_id", type = Integer.class),
                                    @ColumnResult(name = "status_name", type = String.class),
                                    @ColumnResult(name = "category_id", type = Integer.class),
                                    @ColumnResult(name = "category_name", type = String.class),
                                    @ColumnResult(name = "pledged_by_user", type = Long.class)
                            })
            }
    )
})

@Entity
public class ProjectMap {
    @Id
    Integer id;
}
