package com.example.web.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.web.client.api.ClientApiClient;
import com.example.web.project.dto.ProjectDto;
import com.example.web.project.mapper.OrganizationMapper;
import com.example.web.project.mapper.ProjectMapper;

/**
 * プロジェクト登録サービス
 */
@Service
@Transactional
public class ProjectCreateService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private ClientApiClient clientApiClient;

    /**
     * 組織名を取得する
     * 
     * @param organizationId 組織ID
     * @return 組織名
     */
    public String getOrganizationName(String organizationId) {
        return organizationMapper.selectOrganizationName(organizationId);
    }

    /**
     * 顧客名を取得する
     * 
     * @param clientId 顧客ID
     * @return 顧客名
     */
    public String getClientName(String clientId) {
        return clientApiClient.getClientName(clientId);
    }

    /**
     * プロジェクトを登録する
     * 
     * @param projectDto プロジェクトDTO
     */
    public void createProject(ProjectDto projectDto) {
        projectMapper.insertProject(projectDto);
    }
}
