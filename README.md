# 共通家計簿アプリ
## Android Studioの使用方法
### 設定
File→settings(ctrl+alt+s)

### Android端末の操作
#### 接続方法
https://developer.android.com/studio/run/device?hl=ja
1. スマホを開発者モードにする  
   デバイス情報→ビルド番号を5回クリック  
2. Android StudioのデバイスマネージャーからPhysicalを選択しPair using Wi-Fiを選択
3. スマホの開発者向けオプション→ワイヤレスデバッグをONにし、「QRコードによるデバイスのペア設定」をクリックし、QRコードを読み込む

#### ファイル
・DB
data/data/com.example.commonaccountsystem/databases

#### アプリのデプロイ
スマホを指定して実行すると同じ名前のアプリがあるためエラーになる。  
その後、自動的にアプリをアンインストールし、インストールしてくれる。  