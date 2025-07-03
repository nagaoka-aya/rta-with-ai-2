package com.example.web.project.form;

import org.springframework.format.annotation.DateTimeFormat;
import nablarch.core.validation.ee.Domain;
import nablarch.core.validation.ee.Required;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * プロジェクト登録フォーム
 */
public class ProjectCreateForm {

    /**
     * 事業部ID
     */
    @Required
    @Domain("organizationId")
    private String divisionId;

    /**
     * 組織ID
     */
    @Required
    @Domain("organizationId")
    private String organizationId;

    /**
     * プロジェクト名
     */
    @Required
    @Domain("projectName")
    private String projectName;

    /**
     * プロジェクト種別
     */
    @Required
    @Domain("projectType")
    private String projectType;

    /**
     * プロジェクト分類
     */
    @Required
    @Domain("projectClass")
    private String projectClass;

    /**
     * 売上高
     */
    @Domain("amountOfMoney")
    private Integer sales;

    /**
     * 顧客ID
     */
    @Required
    @Domain("clientId")
    private String clientId;

    /**
     * プロジェクトマネージャー
     */
    @Required
    @Domain("userName")
    private String projectManager;

    /**
     * プロジェクトリーダー
     */
    @Required
    @Domain("userName")
    private String projectLeader;

    /**
     * プロジェクト開始日付
     */
    @Required
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectStartDate;

    /**
     * プロジェクト終了日付
     */
    @Required
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectEndDate;

    /**
     * 備考
     */
    @Domain("note")
    private String note;

    // Getters and Setters
    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
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
