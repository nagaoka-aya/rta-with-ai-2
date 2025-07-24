package com.example.web.project.service;

import com.example.web.project.dto.ProjectSearchCondition;
import com.example.web.project.dto.ProjectSearchDto;
import com.example.web.project.form.ProjectSearchForm;
import com.example.web.project.mapper.ProjectSearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * プロジェクト検索サービス
 */
@Service
public class ProjectSearchService {

    @Autowired
    private ProjectSearchMapper projectSearchMapper;

    /**
     * プロジェクトを検索する
     *
     * @param form 検索フォーム
     * @return 検索結果のプロジェクト一覧
     */
    public List<ProjectSearchDto> searchProjects(ProjectSearchForm form) {
        ProjectSearchCondition condition = new ProjectSearchCondition();
        condition.setDivisionId(form.getDivisionId());
        condition.setOrganizationId(form.getOrganizationId());
        condition.setProjectType(form.getProjectType());
        condition.setProjectClass(form.getProjectClass());
        condition.setSalesFrom(form.getSalesFrom());
        condition.setSalesTo(form.getSalesTo());
        condition.setProjectStartDateFrom(form.getProjectStartDateFrom());
        condition.setProjectStartDateTo(form.getProjectStartDateTo());
        condition.setProjectEndDateFrom(form.getProjectEndDateFrom());
        condition.setProjectEndDateTo(form.getProjectEndDateTo());
        condition.setProjectName(form.getProjectName());

        return projectSearchMapper.searchProjects(condition);
    }
}
