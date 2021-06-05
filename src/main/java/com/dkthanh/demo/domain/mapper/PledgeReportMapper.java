package com.dkthanh.demo.domain.mapper;

import com.dkthanh.demo.domain.PledgeReportEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@SqlResultSetMappings({
        @SqlResultSetMapping(
            name = PledgeReportEntity.PLEDGE_REPORT_MAP,
            classes = {
                    @ConstructorResult(
                            targetClass = PledgeReportEntity.class,
                            columns = {
                                    @ColumnResult(name = "year", type = Integer.class),
                                    @ColumnResult(name = "week_number", type = Integer.class),
                                    @ColumnResult(name = "pledge", type = Long.class)
                            })
        }),

        @SqlResultSetMapping(
                name = PledgeReportEntity.OPTION_PERCENT_REPORT_MAP,
                classes = {
                        @ConstructorResult(
                                targetClass = PledgeReportEntity.class,
                                columns = {
                                        @ColumnResult(name = "option_id", type = Integer.class),
                                        @ColumnResult(name = "option_name", type = String.class),
                                        @ColumnResult(name = "option_percent", type = Double.class)
                                }
                        )
                }
        ),

        @SqlResultSetMapping(
                name = PledgeReportEntity.TABLE_REPORT,
                classes = {
                        @ConstructorResult(
                                targetClass = PledgeReportEntity.class,
                                columns = {
                                        @ColumnResult(name = "timestamp", type = OffsetDateTime.class),
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "option_name", type = String.class),
                                        @ColumnResult(name = "pledged", type = Long.class)
                                }
                        )
                }
        )
})

@Entity
public class PledgeReportMapper {
    @Id
    Integer id;
}
