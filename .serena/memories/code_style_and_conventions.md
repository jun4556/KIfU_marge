# コードスタイルと規約

## 命名規則

### Javaクラス・インターフェース
- **クラス名**: PascalCase（例: `DrawerPanel`, `AnalysisService`, `UmlClass`）
- **インターフェース名**: PascalCase、'I'プレフィックスを使用する場合あり（例: `IElements`, `ICheckState`, `IDifference`）

### メソッド・変数
- **メソッド名**: camelCase（例: `getSymbolsOverview`, `findReferencing Symbols`）
- **変数名**: camelCase（例: `studentId`, `exerciseId`, `editEvent`）
- **定数**: UPPER_SNAKE_CASE（推定）

### パッケージ構造
- `com.objetdirect.gwt.umldrawer.client.*` - クライアント側コード
  - `.beans` - データビーン
  - `.analyzer` - 分析機能
  - `.canvas` - キャンバス機能
  - `.drawer` - 描画機能
  - `.viewer` - ビューア機能
  - `.helpers` - ヘルパークラス
- `com.objetdirect.gwt.umldrawer.server.*` - サーバー側コード
  - `.dao` - データアクセスオブジェクト
  - `.evaluater` - 評価機能
  - `.yamazaki.*` - 山崎氏による拡張機能

### サービスクラス命名
- RPC同期インターフェース: `*Service`（例: `ExerciseService`, `LoginService`）
- RPC非同期インターフェース: `*ServiceAsync`（例: `ExerciseServiceAsync`）
- RPC実装クラス: `*ServiceImpl`（例: `ExerciseServiceImpl`）

## GWT固有の規約

### Entry Point
- エントリーポイントクラス: `GWTUMLDrawer.java`
- `EntryPoint`インターフェースを実装
- `onModuleLoad()`メソッドでアプリケーション初期化

### GWTモジュール設定
- モジュール記述子: `GWTUMLDrawer.gwt.xml`
- モジュール名: 'kifu'にリネーム

## コーディングスタイル

### エンコーディング
- **ソースファイル**: UTF-8
- **コンパイルエンコーディング**: UTF-8明示指定（build.xml）

### Java バージョン
- **ソース互換性**: Java 8
- **ターゲット互換性**: Java 8

### ドキュメンテーション
- JavaDocコメントを使用（特にAPIモジュール）
- ライセンス情報をファイルヘッダーに記載（LGPL v3）

## デザインパターン

### Service Pattern
- GWT RPCサービスインターフェースパターン
- 同期・非同期インターフェースの分離

### DAO Pattern
- データアクセス層の分離（`Dao.java`, `Dao_kifu6.java`, `Dao_umlds.java`）
- データベースアクセスの抽象化

### MVC/MVP
- Panelクラスでビューを構成
- Serviceクラスでビジネスロジック
- Beanクラスでデータモデル

## プロジェクト固有の規約

### 日本語コメント
- 日本語でのコメントが許容されている
- ドキュメントファイル（.txt, .docx）も日本語

### データベース命名
- テーブル名: スネークケース（例: `to_reflect`, `target_part`, `modification_event`）
- カラム名: スネークケース（例: `student_id`, `exercise_id`, `edit_event_id`）
