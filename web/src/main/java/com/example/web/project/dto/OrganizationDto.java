package com.example.web.project.dto;

/**
 * 組織情報を保持するデータ転送オブジェクト
 */
public class OrganizationDto {

    /** 組織ID */
    private Integer organizationId;

    /** 組織名 */
    private String organizationName;

    /**
     * 組織IDを取得する
     * @return 組織ID
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * 組織IDを設定する
     * @param organizationId 組織ID
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 組織名を取得する
     * @return 組織名
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * 組織名を設定する
     * @param organizationName 組織名
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
