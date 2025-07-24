package com.example.web.project.mapper;

import com.example.web.project.dto.ProjectSearchCondition;
import com.example.web.project.dto.ProjectSearchDto;
import org.apache.ibatis.annotations.Mapper;

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
     * @return 検索結果のプロジェクト一覧
     */
    List<ProjectSearchDto> searchProjects(ProjectSearchCondition condition);
}
