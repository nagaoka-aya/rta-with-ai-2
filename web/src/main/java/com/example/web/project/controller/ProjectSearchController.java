package com.example.web.project.controller;

import com.example.web.project.form.ProjectSearchForm;
import com.example.web.project.service.ProjectService;
import com.example.web.project.dto.OrganizationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * プロジェクト検索画面を担当するコントローラー。
 */
@Controller
@RequestMapping("/project/search")
public class ProjectSearchController {

    @Autowired
    private ProjectService projectService;

    /**
     * 事業部一覧を取得してModelに設定する
     *
     * @return 事業部一覧
     */
    @ModelAttribute("divisions")
    public List<OrganizationDto> getDivisions() {
        return projectService.searchOrganizations(null);
    }

    /**
     * プロジェクト検索画面の初期表示。
     *
     * @param model モデル
     * @return テンプレートパス
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("projectSearchForm", new ProjectSearchForm());
        return "project/search/index";
    }
}
