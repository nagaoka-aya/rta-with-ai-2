package com.example.web.project.service;

import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.dto.ProjectDto;
import com.example.web.project.mapper.OrganizationMapper;
import com.example.web.project.mapper.ProjectMapper;
import com.example.common.generated.model.Organization;
import com.example.common.generated.model.Project;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * プロジェクトサービス
 */
@Service
public class ProjectService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 組織を検索する
     *
     * @param upperOrganizationId 上位組織ID
     * @return 組織一覧
     */
    public List<OrganizationDto> searchOrganizations(Integer upperOrganizationId) {
        List<Organization> organizations = organizationMapper.selectOrganizationsByUpperId(upperOrganizationId);
        return organizations.stream()
                .map(org -> {
                    OrganizationDto dto = new OrganizationDto();
                    dto.setOrganizationId(org.getOrganizationId());
                    dto.setOrganizationName(org.getOrganizationName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * プロジェクトを登録する
     *
     * @param projectDto プロジェクト情報
     */
    @Transactional
    public void createProject(ProjectDto projectDto) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDto, project);
        project.setVersionNo(1L);

        projectMapper.insert(project);
    }
}
