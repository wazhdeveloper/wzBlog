package com.wz.blogcommon.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-13:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
        private Long id;
        private String userName;
        private String nickName;
        private String password;
        private String phonenumber;
        private String email;
        private String sex;
        private String status;
        private List<Long> roleIds;
}
