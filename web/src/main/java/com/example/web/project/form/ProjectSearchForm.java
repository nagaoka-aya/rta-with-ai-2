package com.example.web.project.form;

import jakarta.validation.constraints.AssertTrue;
import nablarch.core.validation.ee.Domain;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * プロジェクト検索フォーム
 */
public class ProjectSearchForm {

    /** 事業部ID */
    @Domain("organizationId")
    private Integer divisionId;

    /** 部門ID */
    @Domain("organizationId")
    private Integer organizationId;

    /** プロジェクト種別 */
    private List<String> projectType;

    /** プロジェクト分類 */
    private List<String> projectClass;

    /** 売上高FROM */
    @Domain("amountOfMoney")
    private Integer salesFrom;

    /** 売上高TO */
    @Domain("amountOfMoney")
    private Integer salesTo;

    /** プロジェクト開始日FROM */
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectStartDateFrom;

    /** プロジェクト開始日TO */
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectStartDateTo;

    /** プロジェクト終了日FROM */
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectEndDateFrom;

    /** プロジェクト終了日TO */
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectEndDateTo;

    /** プロジェクト名 */
    @Domain("projectName")
    private String projectName;

    /** ページ番号 */
    @Domain("pageNumber")
    private Integer pageNumber = 0;

    /**
     * 売上高範囲の妥当性をチェックする
     *
     * @return FROMがTO以下の場合true、それ以外の場合false
     */
    @AssertTrue(message = "{validator.priceRange.message}")
    public boolean isValidSalesRange() {
        if (salesFrom == null || salesTo == null) {
            return true;
        }
        return salesFrom <= salesTo;
    }

    /**
     * プロジェクト開始日範囲の妥当性をチェックする
     *
     * @return FROMがTO以下の場合true、それ以外の場合false
     */
    @AssertTrue(message = "{validator.dateRange.message}")
    public boolean isValidProjectStartDateRange() {
        if (projectStartDateFrom == null || projectStartDateTo == null) {
            return true;
        }
        return projectStartDateFrom.isEqual(projectStartDateTo) || projectStartDateFrom.isBefore(projectStartDateTo);
    }

    /**
     * プロジェクト終了日範囲の妥当性をチェックする
     *
     * @return FROMがTO以下の場合true、それ以外の場合false
     */
    @AssertTrue(message = "{validator.dateRange.message}")
    public boolean isValidProjectEndDateRange() {
        if (projectEndDateFrom == null || projectEndDateTo == null) {
            return true;
        }
        return projectEndDateFrom.isEqual(projectEndDateTo) || projectEndDateFrom.isBefore(projectEndDateTo);
    }

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

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
