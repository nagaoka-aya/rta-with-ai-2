package com.example.web.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.web.project.form.ProjectCreateForm;

import jp.fintan.keel.spring.web.token.transaction.TransactionTokenCheck;
import jp.fintan.keel.spring.web.token.transaction.TransactionTokenType;

/**
 * プロジェクト登録機能のコントローラー。
 * 
 * @author sample
 */
@Controller
@RequestMapping("project/create")
@TransactionTokenCheck("project/create")
public class ProjectCreateController {

    /**
     * プロジェクト登録入力画面を表示する。
     * 
     * @param model Model
     * @return 画面テンプレート
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("projectCreateForm", new ProjectCreateForm());
        return "project/create/index";
    }

    /**
     * プロジェクト登録確認画面を表示する。
     * 
     * @param form プロジェクト登録フォーム
     * @return 画面テンプレート
     */
    @PostMapping("confirm")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    public String confirm(ProjectCreateForm form) {
        return "project/create/confirm";
    }

    /**
     * プロジェクト登録実行処理。
     * 
     * @param form プロジェクト登録フォーム
     * @return リダイレクト先
     */
    @PostMapping(path = "execute", params = "submit")
    public String execute(ProjectCreateForm form) {
        return "redirect:/project/create/complete";
    }

    /**
     * 入力画面に戻る処理。
     * 
     * @param form プロジェクト登録フォーム
     * @return 画面テンプレート
     */
    @PostMapping(path = "execute", params = "back")
    public String back(ProjectCreateForm form) {
        return "project/create/index";
    }

    /**
     * プロジェクト登録完了画面を表示する。
     * 
     * @return 画面テンプレート
     */
    @GetMapping("complete")
    public String complete() {
        return "project/create/complete";
    }
}
