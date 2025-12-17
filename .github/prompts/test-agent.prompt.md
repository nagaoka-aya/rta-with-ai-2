---
mode: 'agent'
model: Claude Sonnet 4
tools: ['edit/createFile', 'edit/editFiles', 'DatabaseServer/*', 'playwright/browser_click', 'playwright/browser_close', 'playwright/browser_console_messages', 'playwright/browser_drag', 'playwright/browser_fill_form', 'playwright/browser_handle_dialog', 'playwright/browser_hover', 'playwright/browser_navigate', 'playwright/browser_navigate_back', 'playwright/browser_network_requests', 'playwright/browser_press_key', 'playwright/browser_resize', 'playwright/browser_select_option', 'playwright/browser_snapshot', 'playwright/browser_tabs', 'playwright/browser_take_screenshot', 'playwright/browser_type', 'playwright/browser_wait_for']
description: '実装を依頼するプロンプト'
---

# タスク
テストケース${input:test_case}だけを実施してください。

# 指示
アプリは起動済みです。

画面の操作にはplaywrightを用いてください。
DBの操作にはDatabaseServerを用いてください。

ログイン画面が表示された場合はユーザID：10000001、パスワード：pass123-でログインしてください。
各項目の入力値は設計書とドメイン定義書から読み取ってください。
各画面のスクリーンショットを取得してください。
最後にブラウザのコンソールのログを取得してください

最長文字数のテストの場合はプルダウンやラジオボタンから最もラベルが長い選択肢を Select Optionを使って指定してください。
JavaScriptの使用はさけてください

想定と異なる挙動になった時点でテストを完了してください。
最後にレポートをweb/docs/test_evidence/{test_case}.mdとして作成してください。

# レポートの内容
- 実施したテスト内容（手順、入力値の概要）
- テスト結果（合格か不合格か）
- 取得したスクリーンショット
- 取得したDBの内容
- 取得したブラウザのコンソールログ

# レポートのフォーマット
- web/docs/test_evidence/sample.mdを参考にしてください。
