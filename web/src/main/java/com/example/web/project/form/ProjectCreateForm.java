package com.example.web.project.form;

import com.example.web.project.dto.ProjectDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.BeanUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * プロジェクト登録画面のフォームクラス。
 */
public class ProjectCreateForm {

    /**
     * 事業部ID
     */
    @NotEmpty(message = "{validator.required.message}")
    private String divisionId;

    /**
     * 部門ID
     */
    @NotEmpty(message = "{validator.required.message}")
    private String organizationId;

    /**
     * プロジェクト名
     */
    @NotEmpty(message = "{validator.required.message}")
    @Size(max = 128, message = "{validator.length.message}")
    private String projectName;

    /**
     * プロジェクト種別
     */
    @NotEmpty(message = "{validator.required.message}")
    private String projectType;

    /**
     * プロジェクト分類
     */
    @NotEmpty(message = "{validator.required.message}")
    private String projectClass;

    /**
     * 売上高(千円)
     */
    @Digits(integer = 9, fraction = 0, message = "{validator.digits.message}")
    private BigDecimal sales;

    /**
     * 顧客ID
     */
    @NotEmpty(message = "{validator.required.message}")
    private String clientId;

    /**
     * プロジェクトマネージャー
     */
    @NotEmpty(message = "{validator.required.message}")
    @Size(max = 128, message = "{validator.length.message}")
    private String projectManager;

    /**
     * プロジェクトリーダー
     */
    @NotEmpty(message = "{validator.required.message}")
    @Size(max = 128, message = "{validator.length.message}")
    private String projectLeader;

    /**
     * プロジェクト開始日付
     */
    @NotNull(message = "{validator.required.message}")
    @DateTimeFormat(pattern = "uuuu/MM/dd")
    private LocalDate projectStartDate;

    /**
     * プロジェクト終了日付
     */
    @NotNull(message = "{validator.required.message}")
    @DateTimeFormat(pattern = "uuuu/MM/dd")
    private LocalDate projectEndDate;

    /**
     * 備考
     */
    @Size(max = 512, message = "{validator.length.message}")
    private String note;

    /**
     * プロジェクト開始日付とプロジェクト終了日付の大小関係をチェックする。
     * 開始日が終了日よりも後の日付になっていないかをチェックする。
     * 
     * @return 開始日が終了日以前であればtrue、そうでなければfalse
     */
    @AssertTrue(message = "{validator.periodConsistencyCheck.message}")
    public boolean isStartDateBeforeEndDate() {
        if (projectStartDate == null || projectEndDate == null) {
            return true;
        }
        return !projectStartDate.isAfter(projectEndDate);
    }

    /**
     * FormオブジェクトからDTOオブジェクトに変換する。
     * 
     * @return 変換後のProjectDtoオブジェクト
     */
    public ProjectDto toProjectDto() {
        ProjectDto dto = new ProjectDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    // getter/setter メソッド
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
