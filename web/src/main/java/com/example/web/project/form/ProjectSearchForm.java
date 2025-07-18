package com.example.web.project.form;

import java.time.LocalDate;
import java.util.List;

/**
 * プロジェクト検索条件のフォームクラス。
 */
public class ProjectSearchForm {

    /** 事業部ID */
    private Integer divisionId;

    /** 組織ID */
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

    // Getter and Setter methods
    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getProjectType() {
        return projectType;
    }

    public void setProjectType(List<String> projectType) {
        this.projectType = projectType;
    }

    public List<String> getProjectClass() {
        return projectClass;
    }

    public void setProjectClass(List<String> projectClass) {
        this.projectClass = projectClass;
    }

    public Integer getSalesFrom() {
        return salesFrom;
    }

    public void setSalesFrom(Integer salesFrom) {
        this.salesFrom = salesFrom;
    }

    public Integer getSalesTo() {
        return salesTo;
    }

    public void setSalesTo(Integer salesTo) {
        this.salesTo = salesTo;
    }

    public LocalDate getProjectStartDateFrom() {
        return projectStartDateFrom;
    }

    public void setProjectStartDateFrom(LocalDate projectStartDateFrom) {
        this.projectStartDateFrom = projectStartDateFrom;
    }

    public LocalDate getProjectStartDateTo() {
        return projectStartDateTo;
    }

    public void setProjectStartDateTo(LocalDate projectStartDateTo) {
        this.projectStartDateTo = projectStartDateTo;
    }

    public LocalDate getProjectEndDateFrom() {
        return projectEndDateFrom;
    }

    public void setProjectEndDateFrom(LocalDate projectEndDateFrom) {
        this.projectEndDateFrom = projectEndDateFrom;
    }

    public LocalDate getProjectEndDateTo() {
        return projectEndDateTo;
    }

    public void setProjectEndDateTo(LocalDate projectEndDateTo) {
        this.projectEndDateTo = projectEndDateTo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
