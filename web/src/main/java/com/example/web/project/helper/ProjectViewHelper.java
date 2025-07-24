package com.example.web.project.helper;

import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
     * 売上高を3桁区切りのカンマ形式で表示する
     *
     * @param sales 売上高
     * @return カンマ区切りされた売上高文字列
     */
    public String formatSales(Integer sales) {
        if (sales == null) {
            return "";
        }
        return NumberFormat.getNumberInstance(Locale.JAPAN).format(sales);
    }

    /**
     * 日付をyyyy/MM/dd形式で表示する
     *
     * @param date 日付
     * @return フォーマットされた日付文字列
     */
    public String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
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
