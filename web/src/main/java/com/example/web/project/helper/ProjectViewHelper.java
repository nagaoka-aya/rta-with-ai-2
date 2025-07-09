package com.example.web.project.helper;

import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * プロジェクト関連のViewHelper
 */
@Component
public class ProjectViewHelper {

    @Autowired
    private ProjectService projectService;

    /**
     * 改行コードをHTMLの<br>タグに変換する
     *
     * @param text 変換対象のテキスト
     * @return HTMLの<br>タグに変換されたテキスト
     */
    public String convertLineBreakToHtml(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return text.replace(System.lineSeparator(), "<br>");
    }

    /**
     * 組織IDから組織名を取得する
     * @param organizationId 組織ID
     * @return 組織名
     */
    public String getOrganizationName(Integer organizationId) {
        if (organizationId == null) {
            return "";
        }
        
        try {
            // 全組織を取得して該当するIDを検索
            List<OrganizationDto> organizations = projectService.searchOrganizations(null);
            for (OrganizationDto org : organizations) {
                if (organizationId.equals(org.getOrganizationId())) {
                    return org.getOrganizationName();
                }
            }
            
            // 事業部で見つからない場合は部門も検索
            organizations = projectService.searchOrganizations(organizationId);
            for (OrganizationDto org : organizations) {
                if (organizationId.equals(org.getOrganizationId())) {
                    return org.getOrganizationName();
                }
            }
            
            // 部門の親組織を検索
            organizations = projectService.searchOrganizations(null);
            for (OrganizationDto division : organizations) {
                List<OrganizationDto> departments = projectService.searchOrganizations(division.getOrganizationId());
                for (OrganizationDto dept : departments) {
                    if (organizationId.equals(dept.getOrganizationId())) {
                        return dept.getOrganizationName();
                    }
                }
            }
            
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 顧客IDから顧客名を取得する
     * @param clientId 顧客ID
     * @return 顧客名
     */
    public String getClientName(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            return "";
        }
        
        // モック実装
        return "TIS株式会社（仮）";
    }
}
