package com.example.web.project.mapper;

import com.example.web.project.dto.OrganizationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 組織情報に関するデータアクセスを行うマッパーインターフェース
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 事業部一覧を取得する
     * 上位組織IDがNULLの組織を事業部として取得
     * 
     * @return 事業部一覧
     */
    List<OrganizationDto> selectDivisions();

    /**
     * 指定された事業部に属する部門一覧を取得する
     * 上位組織IDが指定された事業部IDの組織を部門として取得
     * 
     * @param divisionId 事業部ID
     * @return 部門一覧
     */
    List<OrganizationDto> selectDepartmentsByDivision(@Param("divisionId") String divisionId);

    /**
     * 指定された組織IDに対応する組織名を取得する
     * 
     * @param organizationId 組織ID
     * @return 組織名
     */
    String selectOrganizationName(@Param("organizationId") String organizationId);
}
