package com.example.web.project.controller;

import com.example.web.project.form.ProjectSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * プロジェクト検索画面を担当するコントローラー。
 */
@Controller
@RequestMapping("/project/search")
public class ProjectSearchController {

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
