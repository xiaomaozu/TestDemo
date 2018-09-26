package com.chang.facade.dto.response;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/3/22 11:12
 */
public class AuthorityUserResponseDTO {
    private Integer id;
    private String userName;
    private String passWord;
    private Boolean enabled;
    private Long createdAt;

    public AuthorityUserResponseDTO(Integer id, String userName, String passWord, Boolean enabled, Long createdAt) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public AuthorityUserResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}

