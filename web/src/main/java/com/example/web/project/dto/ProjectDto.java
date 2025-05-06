package com.example.web.project.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * プロジェクト情報を保持するDTOクラス
 */
public class ProjectDto {

    /**
     * プロジェクトID
     */
    private String projectId;

    /**
     * 事業部ID
     */
    private String divisionId;

    /**
     * 事業部名
     */
    private String divisionName;

    /**
     * 部門ID
     */
    private String organizationId;

    /**
     * 部門名
     */
    private String organizationName;

    /**
     * プロジェクト名
     */
    private String projectName;

    /**
     * プロジェクト種別
     */
    private String projectType;

    /**
     * プロジェクト種別名称
     */
    private String projectTypeName;

    /**
     * プロジェクト分類
     */
    private String projectClass;

    /**
     * プロジェクト分類名称
     */
    private String projectClassName;

    /**
     * 売上高
     */
    private BigDecimal sales;

    /**
     * 顧客ID
     */
    private String clientId;

    /**
     * 顧客名
     */
    private String clientName;

    /**
     * プロジェクトマネージャー
     */
    private String projectManager;

    /**
     * プロジェクトリーダー
     */
    private String projectLeader;

    /**
     * プロジェクト開始日付
     */
    private LocalDate projectStartDate;

    /**
     * プロジェクト終了日付
     */
    private LocalDate projectEndDate;

    /**
     * 備考
     */
    private String note;

    // Getters and Setters
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getProjectClass() {
        return projectClass;
    }

    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    public String getProjectClassName() {
        return projectClassName;
    }

    public void setProjectClassName(String projectClassName) {
        this.projectClassName = projectClassName;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
