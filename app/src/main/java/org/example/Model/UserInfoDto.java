package org.example.Model;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.example.entites.UserInfo;

/*

Just to use info within code not for databases or other servies

 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {

    private String userName;

    private String lastName;

    private Long phoneNumber;

    private String email;
}
