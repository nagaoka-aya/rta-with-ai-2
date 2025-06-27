package com.example.web.project.mapper;

import com.example.common.generated.model.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 組織マスタデータアクセスマッパー
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 上位組織IDを条件に組織一覧を取得する
     * 
     * @param upperOrganizationId 上位組織ID（nullの場合は上位組織IDがNULLの組織を取得）
     * @return 組織一覧
     */
    List<Organization> selectOrganizationsByUpperId(String upperOrganizationId);
}
