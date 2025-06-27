package com.example.web.project.controller;

import com.example.web.project.form.ProjectCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * プロジェクト管理コントローラー
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    /**
     * プロジェクト登録画面表示
     *
     * @param form プロジェクト登録フォーム
     * @return プロジェクト登録画面
     */
    @GetMapping("/create")
    public String create(ProjectCreateForm form) {
        return "project/create/index";
    }

    /**
     * プロジェクト登録確認画面表示
     *
     * @param form プロジェクト登録フォーム
     * @param model モデル
     * @return プロジェクト登録確認画面
     */
    @PostMapping("/create/confirm")
    public String confirm(ProjectCreateForm form, Model model) {
        return "project/create/confirm";
    }

    /**
     * プロジェクト登録処理
     *
     * @param form プロジェクト登録フォーム
     * @return プロジェクト登録完了画面へリダイレクト
     */
    @PostMapping(path = "/create/register", params = "execute")
    public String register(ProjectCreateForm form) {
        // TODO: 登録処理を実装
        return "redirect:/project/create/complete";
    }

    /**
     * プロジェクト登録画面へ戻る
     *
     * @param form プロジェクト登録フォーム
     * @return プロジェクト登録画面
     */
    @PostMapping(path = "/create/register", params = "back")
    public String back(ProjectCreateForm form) {
        return "project/create/index";
    }

    /**
     * プロジェクト登録完了画面表示
     *
     * @return プロジェクト登録完了画面
     */
    @GetMapping("/create/complete")
    public String complete() {
        return "project/create/complete";
    }
}
