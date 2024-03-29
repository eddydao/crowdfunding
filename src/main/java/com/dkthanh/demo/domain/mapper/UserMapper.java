package com.dkthanh.demo.domain.mapper;

import com.dkthanh.demo.domain.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = UserDTO.USER_DTO_MAP,
                classes = {
                        @ConstructorResult(
                                targetClass = UserDTO.class,
                                columns = {
                                        @ColumnResult(name = "user_id", type = Integer.class),
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "first_name", type = String.class),
                                        @ColumnResult(name = "last_name", type = String.class),
                                        @ColumnResult(name = "email", type = String.class),
                                        @ColumnResult(name = "address", type = String.class),
                                        @ColumnResult(name = "phone_num", type = String.class)
                                })
                }
        ),
        @SqlResultSetMapping(
                name = UserDTO.USER_FULL_DTO_MAP,
                classes = {
                        @ConstructorResult(
                                targetClass = UserDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Integer.class),
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "email", type = String.class),
                                        @ColumnResult(name = "address", type = String.class),
                                        @ColumnResult(name = "phone_num", type = String.class),
                                        @ColumnResult(name = "role_name", type = String.class),
                                        @ColumnResult(name = "role_id", type = Integer.class)
                                })
                }
        )
})
@Entity
public class UserMapper {
    @Id
    Integer id;
}
