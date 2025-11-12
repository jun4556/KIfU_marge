# プロジェクト構造詳細

## ディレクトリ構造

```
KIfU_marge-2/
├── api/                      # GWTUMLAPI - UML描画APIライブラリ
│   ├── src/                  # Javaソースコード
│   │   └── com/
│   │       ├── google/       # GWT関連
│   │       └── objetdirect/  # カスタムAPI
│   ├── war/                  # Webアプリケーションリソース
│   │   └── WEB-INF/
│   │       ├── classes/      # コンパイル済みクラス
│   │       └── lib/          # ライブラリ
│   ├── build/                # ビルド成果物
│   │   └── dist/
│   │       └── gwt-umlapi.jar
│   ├── doc/                  # JavaDoc生成先
│   ├── lib_provided/         # 提供ライブラリ
│   ├── build.xml             # Antビルドスクリプト
│   └── *.sql                 # データベーススクリプト
│
└── drawer/                   # GWTUMLDrawer - メインアプリケーション
    ├── src/                  # Javaソースコード
    │   └── com/
    │       └── objetdirect/
    │           └── gwt/
    │               └── umldrawer/
    │                   ├── client/         # クライアント側コード
    │                   │   ├── beans/       # データビーン
    │                   │   ├── analyzer/    # 分析機能
    │                   │   ├── canvas/      # キャンバス機能
    │                   │   ├── comment/     # コメント機能
    │                   │   ├── drawer/      # 描画機能
    │                   │   ├── exercise/    # 課題管理
    │                   │   ├── helpers/     # ヘルパークラス
    │                   │   ├── pattern/     # デザインパターン
    │                   │   ├── progress/    # 進捗管理
    │                   │   ├── reflection/  # 振り返り機能
    │                   │   ├── user/        # ユーザー管理
    │                   │   ├── viewer/      # ビューア機能
    │                   │   └── yamazaki/    # 山崎氏の拡張機能
    │                   ├── server/          # サーバー側コード
    │                   │   ├── dao/         # データアクセス
    │                   │   ├── evaluater/   # 評価機能
    │                   │   ├── analyzer/    # 分析機能
    │                   │   ├── saito/       # 斉藤氏の実装
    │                   │   └── yamazaki/    # 山崎氏の実装
    │                   └── yamnazaki/       # 追加機能
    │                       └── main/
    ├── war/                  # Webアプリケーションリソース
    │   ├── WEB-INF/
    │   │   ├── web.xml       # サーブレット設定
    │   │   ├── appengine-web.xml  # App Engine設定
    │   │   ├── classes/      # コンパイル済みクラス
    │   │   └── lib/          # ライブラリ
    │   ├── *.html            # HTMLファイル
    │   └── *.css             # スタイルシート
    ├── excel/                # Excel関連ファイル
    ├── build.xml             # Antビルドスクリプト
    ├── GWTUMLDrawer.gwt.xml  # GWTモジュール記述子
    ├── KIfU4.war             # デプロイ可能なWARファイル
    ├── *.sql                 # データベーススクリプト
    ├── *.txt                 # ドキュメント・メモ
    └── KIfUマニュアル.docx    # ユーザーマニュアル
```

## 主要ファイル

### ビルド関連
- `api/build.xml` - APIモジュールのAntビルドスクリプト
- `drawer/build.xml` - Drawerモジュールのントビルドスクリプト
- `drawer/GWTUMLDrawer.gwt.xml` - GWTモジュール設定

### アプリケーション
- `drawer/src/com/objetdirect/gwt/umldrawer/client/GWTUMLDrawer.java` - エントリーポイント
- `drawer/war/GWTUMLDrawer.html` - メインHTMLページ
- `drawer/war/WEB-INF/web.xml` - サーブレット設定

### データベース
- `drawer/kifu6.sql` - メインデータベーススキーマ
- `drawer/kifu6_akagidp.sql` - 派生スキーマ
- `api/kifu3.sql` - API用スキーマ

### ドキュメント
- `drawer/KIfUマニュアル.docx` - ユーザーマニュアル
- `drawer/NextAction.txt` - 次のアクション
- `drawer/DBMemo.txt` - データベースメモ
- `drawer/reflection.txt` - リフレクション機能のメモ
- `drawer/MITライセンス.txt` - ライセンス情報

## 依存関係

### 外部ライブラリ
- GWT SDK 2.8.2（カスタムビルド: `C:\gwt-2.8.2-custom`）
- Dojo Toolkit（UIコンポーネント）
- HikariCP（データベース接続プール）

### モジュール依存
- `drawer` → `api` （gwt-umlapi.jar）
- アプリケーションはAPIライブラリを参照して動作
