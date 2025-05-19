package com.example.web.project.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import lombok.Data;

/**
 * プロジェクト登録画面のフォームクラス
 */
@Data
@Validated
public class ProjectCreateForm {

    /**
     * 事業部ID
     */
    @NotBlank(message = "事業部を選択してください")
    private String divisionId;

    /**
     * 組織ID（部門ID）
     */
    @NotBlank(message = "部門を選択してください")
    private String organizationId;

    /**
     * プロジェクト名
     */
    @NotBlank(message = "プロジェクト名を入力してください")
    @Size(max = 100, message = "プロジェクト名は100文字以内で入力してください")
    private String projectName;

    /**
     * プロジェクト種別
     */
    @NotBlank(message = "プロジェクト種別を選択してください")
    private String projectType;

    /**
     * プロジェクト分類
     */
    @NotBlank(message = "プロジェクト分類を選択してください")
    private String projectClass;

    /**
     * 売上高
     */
    @NumberFormat(pattern = "#,###")
    @Digits(integer = 10, fraction = 0, message = "売上高は10桁以内の整数で入力してください")
    private BigDecimal sales;

    /**
     * 顧客ID
     */
    @NotBlank(message = "顧客を選択してください")
    private String clientId;

    /**
     * プロジェクトマネージャー
     */
    @NotBlank(message = "プロジェクトマネージャーを入力してください")
    @Size(max = 50, message = "プロジェクトマネージャーは50文字以内で入力してください")
    private String projectManager;

    /**
     * プロジェクトリーダー
     */
    @Size(max = 50, message = "プロジェクトリーダーは50文字以内で入力してください")
    private String projectLeader;

    /**
     * プロジェクト開始日付
     */
    @NotNull(message = "プロジェクト開始日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate;

    /**
     * プロジェクト終了日付
     */
    @NotNull(message = "プロジェクト終了日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectEndDate;

    /**
     * 備考
     */
    @Size(max = 1000, message = "備考は1000文字以内で入力してください")
    private String note;

    /**
     * 相関チェックなどの独自バリデーションを行うメソッド
     * 
     * @param result バインディング結果
     */
    public void validate(BindingResult result) {
        // 日付の前後関係チェック
        if (projectStartDate != null && projectEndDate != null) {
            if (projectStartDate.isAfter(projectEndDate)) {
                result.rejectValue("projectEndDate", "error.date.invalid.range",
                        "プロジェクト終了日付はプロジェクト開始日付以降の日付を入力してください");
            }
        }

        // プロジェクト終了日は開始日から3年以内の日付であることをチェック
        if (projectStartDate != null && projectEndDate != null) {
            LocalDate maxEndDate = projectStartDate.plusYears(3);
            if (projectEndDate.isAfter(maxEndDate)) {
                result.rejectValue("projectEndDate", "error.date.invalid.period",
                        "プロジェクト終了日付はプロジェクト開始日付から3年以内の日付を入力してください");
            }
        }
    }
}
