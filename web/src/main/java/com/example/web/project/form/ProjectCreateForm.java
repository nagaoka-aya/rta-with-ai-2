package com.example.web.project.form;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * プロジェクト登録画面のフォームクラス。
 * 
 * @author sample
 */
public class ProjectCreateForm {

    /** 事業部ID */
    private String divisionId;

    /** 組織ID */
    private String organizationId;

    /** プロジェクト名 */
    private String projectName;

    /** プロジェクト種別 */
    private String projectType;

    /** プロジェクト分類 */
    private String projectClass;

    /** 売上高 */
    private BigDecimal sales;

    /** 顧客ID */
    private String clientId;

    /** プロジェクトマネージャー */
    private String projectManager;

    /** プロジェクトリーダー */
    private String projectLeader;

    /** プロジェクト開始日付 */
    private LocalDate projectStartDate;

    /** プロジェクト終了日付 */
    private LocalDate projectEndDate;

    /** 備考 */
    private String note;

    /**
     * 事業部IDを取得する。
     * 
     * @return 事業部ID
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     * 事業部IDを設定する。
     * 
     * @param divisionId 事業部ID
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * 組織IDを取得する。
     * 
     * @return 組織ID
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 組織IDを設定する。
     * 
     * @param organizationId 組織ID
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * プロジェクト名を取得する。
     * 
     * @return プロジェクト名
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定する。
     * 
     * @param projectName プロジェクト名
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * プロジェクト種別を取得する。
     * 
     * @return プロジェクト種別
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * プロジェクト種別を設定する。
     * 
     * @param projectType プロジェクト種別
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * プロジェクト分類を取得する。
     * 
     * @return プロジェクト分類
     */
    public String getProjectClass() {
        return projectClass;
    }

    /**
     * プロジェクト分類を設定する。
     * 
     * @param projectClass プロジェクト分類
     */
    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    /**
     * 売上高を取得する。
     * 
     * @return 売上高
     */
    public BigDecimal getSales() {
        return sales;
    }

    /**
     * 売上高を設定する。
     * 
     * @param sales 売上高
     */
    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    /**
     * 顧客IDを取得する。
     * 
     * @return 顧客ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 顧客IDを設定する。
     * 
     * @param clientId 顧客ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * プロジェクトマネージャーを取得する。
     * 
     * @return プロジェクトマネージャー
     */
    public String getProjectManager() {
        return projectManager;
    }

    /**
     * プロジェクトマネージャーを設定する。
     * 
     * @param projectManager プロジェクトマネージャー
     */
    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * プロジェクトリーダーを取得する。
     * 
     * @return プロジェクトリーダー
     */
    public String getProjectLeader() {
        return projectLeader;
    }

    /**
     * プロジェクトリーダーを設定する。
     * 
     * @param projectLeader プロジェクトリーダー
     */
    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    /**
     * プロジェクト開始日付を取得する。
     * 
     * @return プロジェクト開始日付
     */
    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * プロジェクト開始日付を設定する。
     * 
     * @param projectStartDate プロジェクト開始日付
     */
    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    /**
     * プロジェクト終了日付を取得する。
     * 
     * @return プロジェクト終了日付
     */
    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * プロジェクト終了日付を設定する。
     * 
     * @param projectEndDate プロジェクト終了日付
     */
    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * 備考を取得する。
     * 
     * @return 備考
     */
    public String getNote() {
        return note;
    }

    /**
     * 備考を設定する。
     * 
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
}
