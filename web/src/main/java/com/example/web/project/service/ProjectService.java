package com.example.web.project.service;

import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.mapper.OrganizationMapper;
import com.example.common.generated.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * プロジェクト関連の業務ロジックを提供するサービス
 */
@Service
public class ProjectService {

    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * 上位組織IDを条件に組織一覧を取得する
     * 
     * @param upperOrganizationId 上位組織ID（nullの場合は事業部一覧を取得）
     * @return 組織一覧
     */
    public List<OrganizationDto> searchOrganizations(String upperOrganizationId) {
        List<Organization> organizations = organizationMapper.selectOrganizationsByUpperId(upperOrganizationId);
        
        return organizations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * OrganizationモデルをOrganizationDtoに変換する
     * 
     * @param organization 組織モデル
     * @return 組織DTO
     */
    private OrganizationDto convertToDto(Organization organization) {
        OrganizationDto dto = new OrganizationDto();
        dto.setOrganizationId(String.valueOf(organization.getOrganizationId()));
        dto.setOrganizationName(organization.getOrganizationName());
        return dto;
    }
}
