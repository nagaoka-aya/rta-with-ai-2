実行計画を作成してもらいます。

### **条件**  
1. **段階的な実装計画:**  
   - 呼び出し先から順番に実装を進め、最後に呼び出し元（エントリーポイント）を実装する形で計画を記載してください。  
   - 依存関係を考慮し、どの順番で実装するか明確にしてください。  

2. **既存のクラス・メソッドの活用:**  
   - プロジェクト内にすでに存在するクラスやメソッドを積極的に活用してください。  
   - **どのクラスのどのメソッドを呼び出すべきか、引数に何を渡すべきか具体的に指示してください。**  
   - もし適切なメソッドがない場合は、新しくメソッドを作成する指示を記載してください。  

3. **編集対象ファイルの明記:**  
   - どのファイルを編集するべきかを明記してください。  
   - 既存のファイルを編集する場合は、その場所（クラスやメソッド）を具体的に指示してください。  
   - 新規ファイルを作成する場合は、その理由と内容を記載してください。  

4. **分かりやすい記述:**  
   - 若手エンジニアでも理解しやすいように、専門用語をなるべく避けながら説明してください。  
   - 各工程で何を実装すべきかを、日本語の指示として詳細に記載してください（コードの記述は不要）。  

5. **フォーマット:**  
   - **1. 概要**: 機能の簡単な説明  
   - **2. クラス図**: 機能に関連するクラスとメソッドを含めたクラス図をmarmaid形式で記載。namespaceを含めること。
   - **3. シーケンス図**: イベントごとにイベントのシーケンス図をmarmaid形式で記載。処理番号を付けること。
   - **4. 実装計画**: 以下の形式でイベントごと、段階ごとに記載  
     - **～イベント: **
       - **ステップ X: [処理名]**  
         - **編集対象ファイル:** `src/controllers/UserController.java` など  
         - **編集対象のメソッド:** `AuthController#login()` など  
         - **目的:** シーケンス図のどの部分を実装しようとしているか。何を達成しようとしているか。 
         - **内容:** どのように実装を進めるか（具体的な指示）  
         - **活用するクラス・メソッド:** パッケージ名付きで  
           - `my.pj.package.UserService#getUserById(userId: String): User` を利用  
           - `my.pj.package.Logger#info(message: String)` でログを記録  
         - **ポイント:** 注意点や考慮すべき点  

6. **出力例:**  
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
   - **4. 実装計画**  
     - **ログインイベント: **
       - **ステップ 1: ユーザーログインAPIの作成**  
         - **編集対象ファイル:** `src/controllers/AuthController.java`  
         - **編集対象のメソッド:** `AuthController#login()`（新規）  
         - **目的:** ログインイベントのシーケンス図の(1)～(14)の実装。ユーザー認証を行い、JWTトークンを発行する  
         - **内容:**  
           - `AuthController#login()` メソッドを実装し、ログインリクエストを処理する  
           - `RequestContextHolder#currentRequestAttributes()`でリクエスト情報を取得する
           - `RequestAttributes#getAttribute`でユーザーのメール情報を取得する。第一引数に"E-mail"、第二引数にRequestAttributes.SCOPE_REQUESTを指定する。
           - 既存の `UserService#findByEmail(email: String): User` を呼び出して、ユーザー情報を取得する。第一引数には前処理で取得したメール情報を指定する。
           - `PasswordService#verify(password: String, hash: String): boolean` を用いてパスワードチェックの処理を行う。第一引数には入力されたパスワードを、第二引数には取得したユーザー情報のパスワードハッシュ値を指定する
           - `AuthService#generateJwtToken(user: User): String` を利用してトークンを生成する。第一引数には前処理で取得したUserインスタンスを指定する。
           - 生成したトークンをレスポンスとして返却する  
         - **活用するクラス・メソッド:**  
           - `my.pj.package.UserService#findByEmail(email: String): User`  
           - `my.pj.package.AuthService#generateJwtToken(user: User): String`  
         - **ポイント:**  
           - `UserService#findByEmail` の戻り値が `null` の場合、適切なエラーレスポンスを返す  
           - パスワードチェックの処理を `PasswordService#verify(password: String, hash: String): boolean` を用いて実装する 
         - **関連ファイル:** 実装時に実装を統一すべきファイル、例）html実装時にフィールド名を合わせるべきFormクラスなど   

このフォーマットでシステム開発の実装計画を出力してください。

以上の条件に従って、添付した設計書の実装計画を作成してください。
既存の実装については #folder:web/src から収集してください。