package com.example.web.project.service;

import com.example.web.project.dto.ProjectSearchCondition;
import com.example.web.project.dto.ProjectSearchDto;
import com.example.web.project.dto.ProjectDetailDto;
import com.example.web.project.form.ProjectSearchForm;
import com.example.web.project.mapper.ProjectSearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
     * プロジェクト検索を実行する
     *
     * @param form 検索フォーム
     * @return 検索結果のページ
     */
    public Page<ProjectSearchDto> searchProjects(ProjectSearchForm form) {
        // フォームから検索条件に変換
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

        // ページング情報作成
        Pageable pageable = PageRequest.of(form.getPageNumber(), 20);

        // 検索実行
        List<ProjectSearchDto> projects = projectSearchMapper.searchProjects(condition, pageable);
        long totalCount = projectSearchMapper.countSearchProjects(condition);

        return new PageImpl<>(projects, pageable, totalCount);
    }

    /**
     * プロジェクト詳細を取得する
     *
     * @param projectId プロジェクトID
     * @return プロジェクト詳細情報
     */
    public ProjectDetailDto getProjectDetail(Integer projectId) {
        return projectSearchMapper.getProjectDetail(projectId);
    }
}
