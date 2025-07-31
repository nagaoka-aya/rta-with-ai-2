---
mode: 'edit'
model: Claude Sonnet 4
description: '実装を依頼するプロンプト'
---

# 指示
実装計画の${input:goal}のみを忠実に実装してください。
実装結果はChatに表示するのではなく、ファイルを直接編集してください。
HTMLファイルは既存のレイアウトは変更せず、Thymeleafの構文だけを追加してください。

# リファレンス 
- [DBスキーマ](../../web/docs/schema.sql)
    DBの操作を実装する際はDBスキーマを参考にすること
- [HTMLの実装ガイド](../../.github/instructions/html.instructions.md)
    HTMLの実装の参考にすること
- [Controllerクラスの実装ガイド](../../.github\instructions\controller-class.instructions.md)
    Controllerクラスの実装の参考にすること
- [Formクラスの実装ガイド](../../.github\instructions\form-class.instructions.md)
    Formクラスの実装の参考にすること