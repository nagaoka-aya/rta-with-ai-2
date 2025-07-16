package com.example.web.project.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import nablarch.core.validation.ee.Domain;
import nablarch.core.validation.ee.Required;
import com.example.web.project.dto.ProjectDto;

import jakarta.validation.constraints.AssertTrue;

/**
 * プロジェクト作成フォーム
 */
public class ProjectCreateForm {
    
    /** 事業部ID */
    @Required
    @Domain("organizationId")
    private Integer divisionId;
    
    /** 組織ID */
    @Required
    @Domain("organizationId")
    private Integer organizationId;
    
    /** プロジェクト名 */
    @Required
    @Domain("projectName")
    private String projectName;
    
    /** プロジェクト種別 */
    @Required
    @Domain("projectType")
    private String projectType;
    
    /** プロジェクト分類 */
    @Required
    @Domain("projectClass")
    private String projectClass;
    
    /** 売上高 */
    @Domain("amountOfMoney")
    private Integer sales;
    
    /** 顧客ID */
    @Required
    @Domain("clientId")
    private Integer clientId;
    
    /** プロジェクトマネージャー */
    @Required
    @Domain("userName")
    private String projectManager;
    
    /** プロジェクトリーダー */
    @Required
    @Domain("userName")
    private String projectLeader;
    
    /** プロジェクト開始日付 */
    @Required
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectStartDate;
    
    /** プロジェクト終了日付 */
    @Required
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate projectEndDate;
    
    /** 備考 */
    @Domain("note")
    private String note;
    
    /**
     * プロジェクト期間の整合性チェック
     * @return 開始日≦終了日の場合はtrue
     */
    @AssertTrue(message = "{validator.periodConsistencyCheck.message.ProjectCreateForm}")
    public boolean isValidProjectPeriod() {
        if (projectStartDate == null || projectEndDate == null) {
            return true;
        }
        return projectStartDate.isEqual(projectEndDate) || projectStartDate.isBefore(projectEndDate);
    }
    
    /**
     * ProjectDtoに変換する
     * @return ProjectDto
     */
    public ProjectDto toProjectDto() {
        ProjectDto dto = new ProjectDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    // Getters and Setters
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