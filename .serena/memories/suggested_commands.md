# コマンド一覧

## ビルドコマンド

### apiモジュールのビルド
```cmd
cd api
ant dist
```
- `javac`: Javaソースをコンパイル
- `jar`: gwt-umlapi.jarを生成
- `javadoc`: APIドキュメントを生成
- 成果物: `api/build/dist/gwt-umlapi.jar`

### drawerモジュールのビルド
```cmd
cd drawer
ant build
```
- `javac`: Javaソースをコンパイル
- `gwtc`: GWTコンパイル（JavaScriptへの変換）
- `war`: WARファイルを生成
- 成果物: `drawer/war/` (デプロイ可能なWARディレクトリ)

### クリーンビルド
```cmd
cd api
ant clean

cd drawer
ant clean
```

## GWTコンパイル（開発用）
```cmd
cd drawer
ant gwtc
```
- GWTモジュールをJavaScriptにコンパイル
- 時間がかかる処理（-Xmx1024M メモリ設定）

## システムコマンド（Windows）

### ディレクトリ操作
- `dir` - ファイル一覧表示
- `cd <path>` - ディレクトリ移動
- `mkdir <name>` - ディレクトリ作成
- `rmdir /s <name>` - ディレクトリ削除（再帰）

### ファイル操作
- `type <file>` - ファイル内容表示
- `copy <src> <dest>` - ファイルコピー
- `del <file>` - ファイル削除
- `move <src> <dest>` - ファイル移動/リネーム

### 検索
- `findstr /s /i "pattern" *.java` - ファイル内文字列検索（再帰、大文字小文字無視）
- `dir /s /b *.xml` - ファイル検索

### Git操作
- `git status` - 状態確認
- `git add .` - ステージング
- `git commit -m "message"` - コミット
- `git log` - ログ確認

## データベース関連
### MySQLインポート
```cmd
mysql -u root -p gwtumldrawer < drawer/kifu6.sql
```

## 実行
アプリケーションはWARファイルをTomcatやJettyなどのサーブレットコンテナにデプロイして実行します。
