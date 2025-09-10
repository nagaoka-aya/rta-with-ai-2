---
mode: 'agent'
model: Claude Sonnet 4
description: '実装を依頼するプロンプト'
---

# タスク
実装計画の${input:goal}のみを忠実に実装してください。
実装結果はChatに表示するのではなく、ファイルを直接編集してください。
HTMLファイルは既存のレイアウトは変更せず、Thymeleafの構文だけを追加してください。

# 指示
1. まず、実装計画の${input:goal}をよく読み込み、必要な情報を理解してください。
2. DB操作が含まれる場合はDBスキーマファイルを読み込んでください
3. 更新ファイルがある場合は更新ファイルを読み込んでください
4. 実装してください
5. 動作確認に記載されている内容を必ず実行し、動作確認を行ってください。
   動作確認の結果がわかる画面を画像ファイルとして保存してください。ファイル名は「機能名_確認内容.png」としてください。
6. 動作確認に失敗したら実装を修正し、再度動作確認を行ってください。動作確認に成功するまで繰り返してください。

# 重要（忘れないこと）
動作確認手順
1. `Stop-Process -Name "java" -Force`コマンドで起動済みのアプリを停止
2. `mvn spring-boot:run -f web\pom.xml`コマンドでWebアプリを起動
3. playwright mcpでテストを実行
4. ログイン画面が表示された場合はユーザID：10000001、パスワード：pass123-でログイン

# リファレンス 
- [DBスキーマ](../../web/docs/schema.sql)
    DBの操作を実装する際はDBスキーマを参考にすること
- [HTMLの実装ガイド](../../.github/instructions/html.instructions.md)
    HTMLの実装の参考にすること
- [Controllerクラスの実装ガイド](../../.github\instructions\controller-class.instructions.md)
    Controllerクラスの実装の参考にすること
- [Formクラスの実装ガイド](../../.github\instructions\form-class.instructions.md)
    Formクラスの実装の参考にすること

