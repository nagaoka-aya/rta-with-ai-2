package com.example.web.project.service;

import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 組織情報を取得するサービスクラス
 */
@Service
@Transactional(readOnly = true)
public class OrganizationService {

    /**
     * 組織マッパー
     */
    private final OrganizationMapper organizationMapper;

    /**
     * コンストラクタ
     *
     * @param organizationMapper 組織マッパー
     */
    @Autowired
    public OrganizationService(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    /**
     * 事業部一覧を取得する
     *
     * @return 事業部DTOのリスト
     */
    public List<OrganizationDto> getDivisions() {
        return organizationMapper.selectDivisions();
    }

    /**
     * 部門一覧を指定された事業部IDに基づいて取得する
     *
     * @param divisionId 事業部ID
     * @return 部門DTOのリスト
     */
    public List<OrganizationDto> getDepartments(String divisionId) {
        return organizationMapper.selectDepartmentsByDivision(divisionId);
    }
}
