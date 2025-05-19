package com.example.web.project.helper;

import com.example.web.common.helper.CodeHelper;
import com.example.web.project.model.Organization;
import com.example.web.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * プロジェクト登録画面の表示用ヘルパークラス
 */
@Component
public class ProjectCreateViewHelper {

    private final CodeHelper codeHelper;
    private final ProjectService projectService;

    @Autowired
    public ProjectCreateViewHelper(CodeHelper codeHelper, ProjectService projectService) {
        this.codeHelper = codeHelper;
        this.projectService = projectService;
    }

    /**
     * 事業部選択肢リストを取得する
     * 
     * @return 事業部選択肢リスト
     */
    public List<Map<String, String>> getDivisionOptions() {
        List<Organization> divisions = projectService.getDivisions();
        return divisions.stream()
                .map(org -> Map.of(
                        "value", org.getOrganizationId(),
                        "text", org.getOrganizationName()))
                .collect(Collectors.toList());
    }

    /**
     * プロジェクト種別選択肢リストを取得する
     * 
     * @return プロジェクト種別選択肢リスト
     */
    public List<Map<String, String>> getProjectTypeOptions() {
        // コード「C0200001」のパターン「1」からプロジェクト種別選択肢を取得
        return codeHelper.getSelectOptions("C0200001", "1");
    }

    /**
     * プロジェクト分類選択肢リストを取得する
     * 
     * @return プロジェクト分類選択肢リスト
     */
    public List<Map<String, String>> getProjectClassOptions() {
        // コード「C0300001」のパターン「1」からプロジェクト分類選択肢を取得
        return codeHelper.getRadioOptions("C0300001", "1");
    }

    /**
     * 組織名を取得する
     * 
     * @param organizationId 組織ID
     * @return 組織名
     */
    public String getOrganizationName(String organizationId) {
        if (organizationId == null || organizationId.isEmpty()) {
            return "";
        }
        Organization organization = projectService.getOrganizationById(organizationId);
        return organization != null ? organization.getOrganizationName() : "";
    }

    /**
     * 顧客名を取得する
     * 
     * @param clientId 顧客ID
     * @return 顧客名
     */
    public String getClientName(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            return "";
        }
        return projectService.getClientName(clientId);
    }
}
