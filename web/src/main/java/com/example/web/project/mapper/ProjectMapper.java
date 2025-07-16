package com.example.web.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import com.example.common.generated.model.Project;

/**
 * プロジェクトマッパー
 */
@Mapper
public interface ProjectMapper {
    
    /**
     * プロジェクトを登録する
     * @param project 登録するプロジェクト
     */
    @Options(useGeneratedKeys = true, keyProperty = "projectId")
    void insert(Project project);
}
