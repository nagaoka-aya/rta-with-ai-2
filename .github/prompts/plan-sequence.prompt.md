---
mode: 'ask'
model: Claude Sonnet 4
tools: ['codebase']
description: '実装計画のクラス図、シーケンスを作成するためのプロンプトテンプレート'
---

渡した設計書の実装計画を作成してもらいます。

### **条件** 
1. **既存のクラス・メソッドの活用:**  
   - プロジェクト内にすでに存在するクラスやメソッドを積極的に活用してください。

2. **フォーマット:**  
   - **1. 概要**: 機能の簡単な説明  
   - **2. クラス図**: 機能に関連するクラスとメソッドを含めたクラス図をmarmaid形式で記載。namespaceを含めること。
   - **3. シーケンス図**: イベントごとにイベントのシーケンス図をmarmaid形式で記載。処理番号を付けること。

3. **参照するファイル**:  
   - [アーキテクチャ](../../web/docs/architecture.md)
     クラス設計はこのアーキテクチャに記載された内容に従うこと
   - [DBスキーマ](../../web/docs/schema.sql)
     FormクラスやDtoクラスのフィールド、型の決定にはDBスキーマを参考にすること
   - [日英辞書](../../web/docs/translation-table.md)
     クラス名やメソッド名の命名には日英辞書を参考にすること
   - [HTMLの実装ガイド](../../.github/instructions/html.instructions.md)
     使用するメソッドを決定する際には、HTMLの実装ガイドを参考にすること

3. **出力例:**  
   - **1. 概要**  
     - このシステムは、ユーザーがログインし、データを登録・取得できるAPIを提供する。 
   - **2. クラス図**  
     ``` marmaid
      classDiagram
      namespace my.pj.package {
          class AuthController {
              +login(): ResponseEntity<String>
          }

          class UserService {
              +findByEmail(email: String): User
          }

          class AuthService {
              +generateJwtToken(user: User): String
          }

          class PasswordService {
              +verify(password: String, hash: String): boolean
          }

          class User {
              +String email
              +String passwordHash
          }
      }

      namespace org.springframework.web.context.request {
          class RequestContextHolder {
              +static RequestAttributes currentRequestAttributes()
          }

          class RequestAttributes {
              +Object getAttribute(String name, int scope)
          }
      }

      AuthController --> UserService : uses
      AuthController --> AuthService : uses
      AuthController --> PasswordService : uses
      AuthController --> RequestContextHolder : uses
      RequestContextHolder --> RequestAttributes : returns
      RequestAttributes --> User : returns email
      UserService --> User : returns
      PasswordService --> User : verifies password
      AuthService --> AuthController : returns JWT token
     ```
   - **3. シーケンス図**  
     ログインイベント
      ``` marmaid
      sequenceDiagram
     participant Client
     participant AuthController
     participant RequestContextHolder
     participant RequestAttributes
     participant UserService
     participant PasswordService
     participant AuthService
     participant User
     
     %% ① クライアントがログインリクエストを送信
     Client ->> AuthController: (1) POST /login (email, password)

     %% ② リクエスト情報を取得
     AuthController ->> RequestContextHolder: (2) currentRequestAttributes()
     RequestContextHolder ->> AuthController: (3) RequestAttributes

     %% ③ リクエスト属性から email を取得
     AuthController ->> RequestAttributes: (4) getAttribute("E-mail", SCOPE_REQUEST)
     RequestAttributes ->> AuthController: (5) email

     %% ④ ユーザー情報を取得
     AuthController ->> UserService: (6) findByEmail(email)
     UserService ->> User: (7) Retrieve user
     UserService ->> AuthController: (8) User instance

     %% ⑤ パスワード検証
     AuthController ->> PasswordService: (9) verify(password, user.passwordHash)
     PasswordService -->> AuthController: (10) boolean (true/false)

     alt (10) Password incorrect
         %% ⑥ 不正なパスワードの場合、エラーレスポンスを返却
         AuthController ->> Client: (11) 401 Unauthorized
     else (10) Password correct
         %% ⑦ JWTトークンを生成
         AuthController ->> AuthService: (12) generateJwtToken(user)
         AuthService -->> AuthController: (13) JWT Token
         
         %% ⑧ 成功レスポンスを返却
         AuthController ->> Client: (14) 200 OK (JWT Token)
     end
     ```