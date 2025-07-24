package com.example.web.project.dto;

import java.time.LocalDate;

/**
 * プロジェクト検索結果DTO
 */
public class ProjectSearchDto {

    /** プロジェクトID */
    private Integer projectId;

    /** プロジェクト名 */
    private String projectName;

    /** プロジェクト種別 */
    private String projectType;

    /** プロジェクト分類 */
    private String projectClass;

    /** プロジェクトマネージャー */
    private String projectManager;

    /** 売上高 */
    private Integer sales;

    /** プロジェクト開始日 */
    private LocalDate projectStartDate;

    /** プロジェクト終了日 */
    private LocalDate projectEndDate;

    /** 事業部名 */
    private String divisionName;

    /** 部門名 */
    private String organizationName;

    /**
     * プロジェクトIDを取得する
     *
     * @return プロジェクトID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * プロジェクトIDを設定する
     *
     * @param projectId プロジェクトID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * プロジェクト名を取得する
     *
     * @return プロジェクト名
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定する
     *
     * @param projectName プロジェクト名
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * プロジェクト種別を取得する
     *
     * @return プロジェクト種別
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * プロジェクト種別を設定する
     *
     * @param projectType プロジェクト種別
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * プロジェクト分類を取得する
     *
     * @return プロジェクト分類
     */
    public String getProjectClass() {
        return projectClass;
    }

    /**
     * プロジェクト分類を設定する
     *
     * @param projectClass プロジェクト分類
     */
    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    /**
     * プロジェクトマネージャーを取得する
     *
     * @return プロジェクトマネージャー
     */
    public String getProjectManager() {
        return projectManager;
    }

    /**
     * プロジェクトマネージャーを設定する
     *
     * @param projectManager プロジェクトマネージャー
     */
    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * 売上高を取得する
     *
     * @return 売上高
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * 売上高を設定する
     *
     * @param sales 売上高
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * プロジェクト開始日を取得する
     *
     * @return プロジェクト開始日
     */
    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * プロジェクト開始日を設定する
     *
     * @param projectStartDate プロジェクト開始日
     */
    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    /**
     * プロジェクト終了日を取得する
     *
     * @return プロジェクト終了日
     */
    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * プロジェクト終了日を設定する
     *
     * @param projectEndDate プロジェクト終了日
     */
    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * 事業部名を取得する
     *
     * @return 事業部名
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * 事業部名を設定する
     *
     * @param divisionName 事業部名
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * 部門名を取得する
     *
     * @return 部門名
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * 部門名を設定する
     *
     * @param organizationName 部門名
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
