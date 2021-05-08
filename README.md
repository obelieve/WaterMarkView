# WaterMarkView
页面水印背景图

![image](https://github.com/obelieve/WaterMarkView/blob/master/screenshots/screenshot.png)


### Step 1. Add the JitPack repository to your build file
```
...
allprojects {
    repositories {
        ...
        maven(){url 'https://jitpack.io'}
    }
}
...
```
### Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.obelieve:WaterMarkView:1.0.0'
	}
```

### Step 3. Use
```xml
    <com.obelieve.watermarkview.WaterMarkView
        android:id="@+id/vmv_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```
### 具体参数
```xml
app:imageResource="@mipmap/ic_launcher" //水印图
app:imageRotate="30" //角度
```
