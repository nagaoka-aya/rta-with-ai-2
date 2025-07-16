package com.example.web.project.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * プロジェクト登録用データ転送オブジェクト
 */
public class ProjectDto {
    
    /** プロジェクトID */
    private Integer projectId;
    
    /** プロジェクト名 */
    private String projectName;
    
    /** プロジェクト種別 */
    private String projectType;
    
    /** プロジェクト分類 */
    private String projectClass;
    
    /** プロジェクト開始日付 */
    private LocalDate projectStartDate;
    
    /** プロジェクト終了日付 */
    private LocalDate projectEndDate;
    
    /** 組織ID */
    private Integer organizationId;
    
    /** 事業部ID */
    private Integer divisionId;
    
    /** 顧客ID */
    private Integer clientId;
    
    /** プロジェクトマネージャー */
    private String projectManager;
    
    /** プロジェクトリーダー */
    private String projectLeader;
    
    /** 備考 */
    private String note;
    
    /** 売上高 */
    private BigDecimal sales;
    
    /** バージョン番号 */
    private Integer version;

    // Getters and Setters
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectClass() {
        return projectClass;
    }

    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
