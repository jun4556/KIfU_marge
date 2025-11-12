# KIfU_marge-2 プロジェクト概要

## プロジェクトの目的
KIfU (Knowledge in Formation by UML) は、学生がUML図を描きながら学習するための教育用Webアプリケーションです。このプロジェクトは、UMLクラス図の描画、学習履歴の記録、振り返り（リフレクション）機能を提供します。

## プロジェクト構成
プロジェクトは2つの主要なモジュールで構成されています：

1. **api/** - GWTUMLAPI
   - UML描画のコアAPIライブラリ
   - ビルド成果物: `gwt-umlapi.jar`
   
2. **drawer/** - GWTUMLDrawer
   - 実際の描画アプリケーション
   - apiモジュールに依存
   - ビルド成果物: `KIfU4.war`

## 技術スタック
- **フレームワーク**: Google Web Toolkit (GWT) 2.8.2
- **言語**: Java 8
- **ビルドツール**: Apache Ant
- **データベース**: MySQL (gwtumldrawer)
- **フロントエンド**: GWT + Dojo Toolkit
- **サーバー**: Java Servlet (WAR形式でデプロイ)

## 主要機能
- UMLクラス図の描画
- 学習課題（Exercise）の管理
- 学生の編集イベント記録
- 振り返り（Reflection）機能
- デザインパターンの保存と参照
- 学習進捗の可視化
- コメント機能
- ユーザー管理（学生、教員、管理者）

## データベース
- **メインDB**: kifu6.sql
- 学生の操作ログ、編集イベント、振り返り情報などを記録
- テーブル例: to_reflect, target_part, modification_event, reflection, operation_log

## ライセンス
- MITライセンス（日本語版あり: MITライセンス.txt, MITライセンス齊藤國分.txt）
- GWTUML部分: LGPL v3（元のGWTUMLプロジェクトベース）
