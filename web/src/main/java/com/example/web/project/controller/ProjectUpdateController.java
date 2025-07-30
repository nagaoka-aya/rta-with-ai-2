package com.example.web.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * プロジェクト更新機能を担当するコントローラー。
 * 
 * @author sample
 */
@Controller
@RequestMapping("/project/update")
public class ProjectUpdateController {

    /**
     * プロジェクト更新画面を表示する。
     * 
     * @param projectId プロジェクトID
     * @return プロジェクト更新画面のテンプレートパス
     */
    @GetMapping("/{projectId}")
    public String update(@PathVariable Integer projectId) {
        return "project/update/index";
    }

    /**
     * プロジェクト更新確認画面を表示する。
     * 
     * @return プロジェクト更新確認画面のテンプレートパス
     */
    @PostMapping("/confirm")
    public String updateConfirm() {
        return "project/update/confirm";
    }

    /**
     * プロジェクト更新処理を実行し、完了画面にリダイレクトする。
     * 
     * @return 完了画面へのリダイレクトパス
     */
    @PostMapping(path = "/complete", params = "execute")
    public String updateComplete() {
        return "redirect:/project/update/complete";
    }

    /**
     * 確認画面から更新画面に戻る。
     * 
     * @return プロジェクト更新画面のテンプレートパス
     */
    @PostMapping(path = "/complete", params = "back")
    public String updateBack() {
        return "project/update/index";
    }

    /**
     * プロジェクト更新完了画面を表示する。
     * 
     * @return プロジェクト更新完了画面のテンプレートパス
     */
    @GetMapping("/complete")
    public String complete() {
        return "project/update/complete";
    }
}
