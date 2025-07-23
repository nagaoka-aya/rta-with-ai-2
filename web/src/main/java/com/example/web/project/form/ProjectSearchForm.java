package com.example.web.project.form;

import java.time.LocalDate;
import java.util.List;

/**
 * プロジェクト検索フォーム
 */
public class ProjectSearchForm {

    /** 事業部ID */
    private Integer divisionId;

    /** 部門ID */
    private Integer organizationId;

    /** プロジェクト種別 */
    private List<String> projectType;

    /** プロジェクト分類 */
    private List<String> projectClass;

    /** 売上高FROM */
    private Integer salesFrom;

    /** 売上高TO */
    private Integer salesTo;

    /** プロジェクト開始日FROM */
    private LocalDate projectStartDateFrom;

    /** プロジェクト開始日TO */
    private LocalDate projectStartDateTo;

    /** プロジェクト終了日FROM */
    private LocalDate projectEndDateFrom;

    /** プロジェクト終了日TO */
    private LocalDate projectEndDateTo;

    /** プロジェクト名 */
    private String projectName;

    /**
     * 事業部IDを取得する
     *
     * @return 事業部ID
     */
    public Integer getDivisionId() {
        return divisionId;
    }

    /**
     * 事業部IDを設定する
     *
     * @param divisionId 事業部ID
     */
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * 部門IDを取得する
     *
     * @return 部門ID
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * 部門IDを設定する
     *
     * @param organizationId 部門ID
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * プロジェクト種別を取得する
     *
     * @return プロジェクト種別
     */
    public List<String> getProjectType() {
        return projectType;
    }

    /**
     * プロジェクト種別を設定する
     *
     * @param projectType プロジェクト種別
     */
    public void setProjectType(List<String> projectType) {
        this.projectType = projectType;
    }

    /**
     * プロジェクト分類を取得する
     *
     * @return プロジェクト分類
     */
    public List<String> getProjectClass() {
        return projectClass;
    }

    /**
     * プロジェクト分類を設定する
     *
     * @param projectClass プロジェクト分類
     */
    public void setProjectClass(List<String> projectClass) {
        this.projectClass = projectClass;
    }

    /**
     * 売上高FROMを取得する
     *
     * @return 売上高FROM
     */
    public Integer getSalesFrom() {
        return salesFrom;
    }

    /**
     * 売上高FROMを設定する
     *
     * @param salesFrom 売上高FROM
     */
    public void setSalesFrom(Integer salesFrom) {
        this.salesFrom = salesFrom;
    }

    /**
     * 売上高TOを取得する
     *
     * @return 売上高TO
     */
    public Integer getSalesTo() {
        return salesTo;
    }

    /**
     * 売上高TOを設定する
     *
     * @param salesTo 売上高TO
     */
    public void setSalesTo(Integer salesTo) {
        this.salesTo = salesTo;
    }

    /**
     * プロジェクト開始日FROMを取得する
     *
     * @return プロジェクト開始日FROM
     */
    public LocalDate getProjectStartDateFrom() {
        return projectStartDateFrom;
    }

    /**
     * プロジェクト開始日FROMを設定する
     *
     * @param projectStartDateFrom プロジェクト開始日FROM
     */
    public void setProjectStartDateFrom(LocalDate projectStartDateFrom) {
        this.projectStartDateFrom = projectStartDateFrom;
    }

    /**
     * プロジェクト開始日TOを取得する
     *
     * @return プロジェクト開始日TO
     */
    public LocalDate getProjectStartDateTo() {
        return projectStartDateTo;
    }

    /**
     * プロジェクト開始日TOを設定する
     *
     * @param projectStartDateTo プロジェクト開始日TO
     */
    public void setProjectStartDateTo(LocalDate projectStartDateTo) {
        this.projectStartDateTo = projectStartDateTo;
    }

    /**
     * プロジェクト終了日FROMを取得する
     *
     * @return プロジェクト終了日FROM
     */
    public LocalDate getProjectEndDateFrom() {
        return projectEndDateFrom;
    }

    /**
     * プロジェクト終了日FROMを設定する
     *
     * @param projectEndDateFrom プロジェクト終了日FROM
     */
    public void setProjectEndDateFrom(LocalDate projectEndDateFrom) {
        this.projectEndDateFrom = projectEndDateFrom;
    }

    /**
     * プロジェクト終了日TOを取得する
     *
     * @return プロジェクト終了日TO
     */
    public LocalDate getProjectEndDateTo() {
        return projectEndDateTo;
    }

    /**
     * プロジェクト終了日TOを設定する
     *
     * @param projectEndDateTo プロジェクト終了日TO
     */
    public void setProjectEndDateTo(LocalDate projectEndDateTo) {
        this.projectEndDateTo = projectEndDateTo;
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
}
