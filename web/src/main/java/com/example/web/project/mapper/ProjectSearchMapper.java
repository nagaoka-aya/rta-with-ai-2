package com.example.web.project.mapper;

import com.example.web.project.dto.ProjectSearchCondition;
import com.example.web.project.dto.ProjectSearchDto;
import com.example.web.project.dto.ProjectDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * プロジェクト検索マッパー
 */
@Mapper
public interface ProjectSearchMapper {

    /**
     * プロジェクトを検索する
     *
     * @param condition 検索条件
     * @param pageable ページング情報
     * @return 検索結果のプロジェクト一覧
     */
    List<ProjectSearchDto> searchProjects(@Param("condition") ProjectSearchCondition condition, @Param("pageable") Pageable pageable);

    /**
     * プロジェクト検索の件数を取得する
     *
     * @param condition 検索条件
     * @return 検索結果の件数
     */
    long countSearchProjects(@Param("condition") ProjectSearchCondition condition);

    /**
     * プロジェクト詳細を取得する
     *
     * @param projectId プロジェクトID
     * @return プロジェクト詳細情報
     */
    ProjectDetailDto getProjectDetail(@Param("projectId") Integer projectId);
}
