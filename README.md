**前言**

项目越做越大，每次编译的时间越来越长，体验特别不好，在加上协同开发的时候，项目管理问题，因此开始了我的Android项目的模块化开发之旅。

**1.模块化开发的优势**

* 单独模块开发，编译迅速，调试方便
* 模块之间相互解耦，协同开发不容易出错
* 实际使用后，项目打包后的体积也小了很多

**2.遇到的问题和解决方案**

* 模块之间的数据传递
	*  模块之间使用[ARouter](https://github.com/alibaba/ARouter)进行跳转
	* 采用事件总线的方式，自定义[RxBus](https://github.com/StruggleInHangzhou/ModularSample/blob/master/core/src/main/java/com/gavel/core/component/RxBus.java)进行数据传递
* 模块管理
	* Android Studio中gradle.properties文件中进行配置，是否加载模块进入项目工程，不需要的时候不加载进来可以加快工程的编译速度
	* 版本问题使用nexus进行控制，模块采取arr或者本地Modle进行加载进主工程。同样配置在gradle.properties中
	* 架包问题采用配置公共版本号，配置在整体项目的build.gradle文件中，与项目工程配置区分开
* 资源冲突
	* 资源文件严格按照命名规则，添加模块名字的前缀，可用gradle进行强制检测,未实践。

**3.项目工程需要的配置**

* gradle.properties 文件
	* 	nexus私服配置
	*  本地环境配置
	*  模块化开关
	*  模块版本配置
* 工程的build.gradle文件 
	* 项目架包版本统一管理
* 模块的buile.gradle文件
	*  nexus私服上传配置
	* 模块内部代码资源目录配置

**4.项目工程结构及配置**

* 能转化成app的modle（上层模块）
 ![权限模块](http://opgzhirw4.bkt.clouddn.com/auth.png)
 权限模块主要做整个app的欢迎和登录注册等功能，不管生产还是开发的时候都拥有Launcher界面，所以在src/main/java/debug中没有一个DebugLoginActivity作为模块的Launcher界面，其他的modle需要一个DebugLoginActivity作为他的启动界面，里面一般做自动登录的功能，然后直接跳转到目标界面，空出一个回调方法用来跳转到下一个界面，开发的时候直接跳转到正在调试的界面。打包的时候debug目录不会打进去。模块内部代码资源目录配置：
 

```
sourceSets {
        main {
            java.srcDirs = ['src/main/java', 'src/main/java-gen']
            jniLibs.srcDirs = ['lib']
            if (isModule.toBoolean()) {
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/release/AndroidManifest.xml'
                //release模式下排除debug文件夹中的所有Java文件
                java {
                    exclude 'debug/**'
                }
            }
        }
    }
```
模块application和library转换：

```
if (isModule.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```
控制开关配置在gradle.properties中：

```
# isModule = false  是否模块开发
isModule=true
```
发布及版本控制配置在gradle.properties文件中：

```
authRelease=false
AUTH_VERSION=1
AUTH_VERSION_NAME=1.0.170807
```


 
* 不能转化成的app的modle（组件）
	* 防止循环依赖项目需要一个核心组件，上层模块统一只依赖核心组件core
	*  核心组件依赖其他基本组件，不如联网组件，数据库组件等。。。
	* 核心组件中添加整个项目所需要的架包
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    testCompile 'junit:junit:4.12'

    debugCompile "com.squareup.leakcanary:leakcanary-android:$rootProject.leakcanaryVersion"
    releaseCompile "com.squareup.leakcanary:leakcanary-android-no-op:$rootProject.leakcanaryVersion"
    testCompile "com.squareup.leakcanary:leakcanary-android-no-op:$rootProject.leakcanaryVersion"
    compile "com.android.support:appcompat-v7:${SUPPORT_LIB_VERSION}"
    compile "com.android.support:recyclerview-v7:${SUPPORT_LIB_VERSION}"
    compile "com.android.support:cardview-v7:${SUPPORT_LIB_VERSION}"
    compile "com.android.support:design:${SUPPORT_LIB_VERSION}"
    compile "com.android.support.constraint:constraint-layout:${CONSTRAINT_LAYOUT}"
    compile "com.android.support:multidex:$rootProject.multidexVersion"

    compile "com.alibaba:arouter-api:$rootProject.arouterApiVersion"
    annotationProcessor "com.alibaba:arouter-compiler:$rootProject.arouterCompilerVersion"
    annotationProcessor "com.google.dagger:dagger-compiler:$rootProject.daggerCompilerVersion"
    annotationProcessor "com.google.dagger:dagger-android-processor:$rootProject.daggerAndroidProcessorVersion"

    compile "com.orhanobut:logger:$rootProject.loggerVersion"
    compile "com.google.dagger:dagger:$rootProject.daggerVersion"
    annotationProcessor "com.google.dagger:dagger-compiler:$rootProject.daggerCompilerVersion"
    compile "com.google.dagger:dagger-android:$rootProject.daggerAndroidVersion"
    compile "com.google.dagger:dagger-android-support:$rootProject.daggerAndroidSupportVersion"
    annotationProcessor "com.google.dagger:dagger-android-processor:$rootProject.daggerAndroidProcessorVersion"

    compile "io.reactivex.rxjava2:rxjava:$rootProject.rxjava2Version"
    compile "io.reactivex.rxjava2:rxandroid:$rootProject.rxandroid2Version"
    compile "com.jakewharton.rxbinding2:rxbinding:$rootProject.rxbinding2Version"
    compile "com.jakewharton.rxbinding2:rxbinding-support-v4:$rootProject.rxbinding2SupportV4Version"
    compile "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:$rootProject.rxbinding2AppcompatV7Version"
    compile "com.jakewharton.rxbinding2:rxbinding-design:$rootProject.rxbinding2DesignVersion"
    compile "com.jakewharton.rxbinding2:rxbinding-recyclerview-v7:$rootProject.rxbinding2recyclerviewV7Version"
    compile "com.jakewharton.rxrelay2:rxrelay:$rootProject.rxrelay2Version"
    compile "com.trello.rxlifecycle2:rxlifecycle:$rootProject.rxlifecycle2Version"
    compile "com.trello.rxlifecycle2:rxlifecycle-android:$rootProject.rxlifecycle2AndroidVersion"
    compile "com.trello.rxlifecycle2:rxlifecycle-components:$rootProject.rxlifecycle2ComponentsVersion"
    compile "com.tbruyelle.rxpermissions2:rxpermissions:$rootProject.rxpermissions2Version"

    compile "com.blankj:utilcode:$rootProject.utilcodeVersion"

    compile "me.yokeyword:fragmentation:$rootProject.fragmentationVersion"
    compile "me.yokeyword:fragmentation-swipeback:$rootProject.fragmentationSwipebackVersion"


    if (httpRelease.toBoolean()) {
        compile "com.gavel:http:$HTTP_VERSION_NAME"
    } else {
        compile project(':http')
    }
    if (dbRelease.toBoolean()) {
        compile "com.gavel:db:$DB_VERSION_NAME"
    } else {
        compile project(':db')
    }
    if (basicRelease.toBoolean()) {
        compile "com.gavel:basic:$BASIC_VERSION_NAME"
    } else {
        compile project(':basic')
    }
}
```
架包版本配置在整个项目的build.gradle中。

```
// 项目架包版本控制
ext {
    javaVersion = JavaVersion.VERSION_1_8

    arouterApiVersion = "1.1.0"
    arouterCompilerVersion = "1.1.1"
    multidexVersion = "1.0.1"
    leakcanaryVersion = "1.5.1"
    jsr305Version = "1.3.9"

    zXingVersion = "3.2.1"
    retrofitVersion = "2.1.0"
    retrofitConverterScalarVersion = "2.3.0"
    retrofitConverterGsonVersion = "2.3.0"
    retrofitAdapterRxjava2Version = "2.3.0"
    hjhVersion = "1.1.0"
    glideVersion = "3.7.0"
    loggerVersion = "2.1.1"
    daggerVersion = "2.11"
    daggerCompilerVersion = "2.11"
    daggerAndroidVersion = "2.11"
    daggerAndroidSupportVersion = "2.11"
    daggerAndroidProcessorVersion = "2.11"
    rxjava2Version = "2.1.0"
    rxandroid2Version = "2.0.1"
    rxbinding2Version = "2.0.0"
    rxbinding2SupportV4Version = "2.0.0"
    rxbinding2AppcompatV7Version = "2.0.0"
    rxbinding2DesignVersion = "2.0.0"
    rxbinding2recyclerviewV7Version = "2.0.0"
    rxrelay2Version = "2.0.0"
    rxlifecycle2Version = "2.1.0"
    rxlifecycle2AndroidVersion = "2.1.0"
    rxlifecycle2ComponentsVersion = "2.1.0"
    rxpermissions2Version = "0.9.4@aar"
    lombokVersion = "1.16.18"
    fragmentationVersion = "1.1.0"
    fragmentationSwipebackVersion = "1.1.0"
    utilcodeVersion = "1.8.1"

    /**
     * greendao目前只支持rxjava1  后期greendao适配rxjava2后需要升级
     */
    greendaoVersion = "3.2.0"
}

```
发布及版本控制配置在gradle.properties文件中：

```
# 底层lib模块
coreRelease=false
CORE_VERSION=1
CORE_VERSION_NAME=1.0.170807
```

* 打包发布主体app模块依赖

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    testCompile 'junit:junit:4.12'
    if (coreRelease.toBoolean()) {
        compile "com.gavel:core:$CORE_VERSION_NAME"
    } else {
        compile project(':core')
    }

    if (authRelease.toBoolean()) {
        compile "com.gavel:auth:$AUTH_VERSION_NAME"
    } else {
        compile project(':auth')
    }

    if (mainRelease.toBoolean()) {
        compile "com.gavel:main:$MAIN_VERSION_NAME"
    } else {
        compile project(':main')
    }

}

```
* 项目工程settings.gradle配置

```
if (!isModule.toBoolean()) {
    include ':app'
}
if (!mainRelease.toBoolean()) {
    include ':main'
}
if (!authRelease.toBoolean()) {
    include ':auth'
}
if (!coreRelease.toBoolean()) {
    include ':core'
}
if (!dbRelease.toBoolean()) {
    include ':db'
}
if (!httpRelease.toBoolean()) {
    include ':http'
}
if (!basicRelease.toBoolean()) {
    include ':basic'
}
```
app模块是主体模块，模块化开发的时候不需要加载进项目工程，其他基本模块发布出去后，项目只需要依赖arr文件，进行远程私服依赖，所以也不需要加载进工程以加快编译速度。

**5.总结**

*  修改gradle.properties中的isModule要能正常切换application和library。基本就算完事了，配置还是比较简单的。
*  模块化难的是对于业务的解耦。一个功能是作为一个基本组件还是作为一个上层模块。功能细分到什么程度都是很讲究的。
* [项目github地址](https://github.com/StruggleInHangzhou/ModularSample)
