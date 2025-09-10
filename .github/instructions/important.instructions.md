---
applyTo: "*"
---

# 重要（忘れないこと）
動作確認手順
1. `Stop-Process -Name "java" -Force`コマンドで起動済みのアプリを停止
2. `mvn spring-boot:run -f web\pom.xml`コマンドでWebアプリを起動
3. playwright mcpでテストを実行
4. ログイン画面が表示された場合はユーザID：10000001、パスワード：pass123-でログイン
5. 動作確認の結果がわかる画面を画像ファイルとして保存してください。ファイル名は「機能名_確認内容.png」としてください。