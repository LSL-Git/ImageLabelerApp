# APP 说明文档

## APP[演示视频链接](http://pan.baidu.com/s/1i5iPrDz) 密码:vcqc
```
链接：http://pan.baidu.com/s/1i5iPrDz 密码：vcqc
```

## APP[可运行程序链接](http://42.123.127.93:10080/LSL/Document/blob/master/apk/ImageLabel.apk)
```
下图为APP logo：
```

[![LOGO](https://github.com/LSL-Git/ImageLabelerApp/blob/master/app/src/main/res/mipmap-mdpi/appicon.png?raw=true)](http://42.123.127.93:10080/LSL/Document/blob/master/apk/ImageLabel.apk)

### 其他说明 ###
```
1.应赛题要求，我们开发了一款Android应用程序和服务端，这里主要介绍的是客户端，即APP的相关功能。
2.赛题要求客户端包含 ’志愿者使用‘部分和’志愿者使用‘部分，考虑到如今人们都不愿在自己手机上多装APP的现象，
所以我们将普通用户端和管理员端的功能整合到一个程序中，只有是管理员登录时才能操作管理员相关功能，这样管
理员拥有管理员权限的同时也有普通用户的操作功能。
```
# 各功能介绍 #

## 普通用户部分 ##

### 1.用户注册 ###

注册页包括：
```
注册信息:
    1.新用户名
    2.用户联系方式
    3.新用户密码
    4.确认密码
    
注册结果：
    1.注册成功：用户信息会保存到服务器端，用户名是唯一的。
    2.注册失败：已存在相同用户名或信息未填写完整等。
```

![register](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/register.gif?raw=true)

## 2.用户登录 ###
登录页包括：
```
登录信息:
    1.用户名
    2.用户密码

登录结果:
    1.登录成功:进入主页面
    2.登录失败:密码错误或信息未填写完整。

其他可选:
    1.记住密码：将记住当前登录密码。
    2.自动登录：下次点击应用图标可直接登录进入主页面（只在记住密码的前提下有效）
```
![register](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/login.gif?raw=true)

### 3.程序主页面和菜单页 ###
#### 主页 ####
```
进入方式:登录成功
```
![主页面](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/main.png?raw=true "主页面")

#### 菜单页 ####
```
进入方式:
    1.点击主页左上角的三杠图标
    2.右滑屏幕可进入显示菜单页
显示:
    1.用户头像
    2.用户权限(’MANAGER'表示当前用户为管理员/'VOLUNTEER'代表当前用户为普通志愿者用户)
    3.用户名
    4.积分
主菜单选项:
    1.今日任务(查看今日任务图片)
    2.我的积分(查看积分)
    3.标签历史(查看标签历史)
    4.帮 助(查看帮助文档)
    5.搜 图(进入搜索已完成标签化图片页面)
    6.设 置(进入设置页)
    7.退 出(退出程序)
标签辅助菜单：(方便用户切换图片类型,改善用户标签体验)
    1.今日任务
    2.猜你喜欢
    3.其他
```
![菜单页](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/menu.gif?raw=true "菜单页")

### 4.图片标签 ###
```
进入方式：点击主页面任意一张图片或查看历史标签选择为今日标签的选项
标签操作：输入最少一个最多十个标签，然后点击提交即可，要求同一张图片不可出现相同标签，否则提交失败。
```
![标签页](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/label.gif?raw=true "标签页")

### 5.设置页 ###
```
进入方式: 点击菜单"设置"
选项:
    1.用户信息(查看用户信息)
    2.修改密码(进入修改密码页)
    3.管理员(只有管理员登录时才会显示)
    4.问题反馈(进入反馈页)
    5.帮 助(查看帮助文档)
    6.关 于(查看关于APP信息)
    7.退出账户(退出当前账户)
下图为普通用户设置页面
```
![设置](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/uset.png?raw=true "设置")

### 6.查看或修改个人信息 ###
```
个人信息包含:
    1.用户头像
    2.用户昵称
    3.用户联系方式(可修改)
    4.用户邮箱(可修改)
    5.用户积分
    6.今日任务进度
    7.修改密码(可修改)
```
![个人信息页](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/uinfo.gif?raw=true "个人信息页")
![修改个人信息](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/auinfo.gif?raw=true "修改个人信息")

### 7.修改密码 ###
```
进入方式:个人信息页点击修改密码或设置页点击修改密码
输入:
    1.原密码
    2.新密码
    3.确认密码
```
![修改密码](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/apsw.gif?raw=true "修改密码")

### 8.查看和修改标签历史 ###
```
* 标签历史可从主菜单'标签历史'进入,可显示所有标签历史;
* 右下角菜单进入查看的当天标签历史,同时可显示今日任务完成进度 x/y;
* 只有今天时间内标签的图片才可以重修修改.
* 点击今日标签列表项,可进入标签修改页面
* 历史信息包括:
    1.标签
    2.标签时间
    3.图片状态
    4.图片
```

![查看标签历史](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/history.gif?raw=true "查看标签历史")
![修改标签历史](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/alabelh.gif?raw=true "修改标签历史")

### 9.搜 图 ###
```
* 进入方式:点击主菜单'搜图'
* 选中搜索框可显示以往搜索历史选项
* 输入字符可自动匹配数据库内已存在的相关字符串
* 选定字符可显示所有与该字符相同其他所有字符列表
* 显示包括(已标签好的图片,该类图片标签,和该种类图片数量)
* 选中搜索项,可显示所有该类图片
* 任意选中图片可显示该图片的标签信息
* 标签信息包括(图片名称\完成标签化时间\所有标签)
```
![搜图](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/st.gif?raw=true "搜图")

### 10.查看积分 ###
```
* 进入方式:主菜单'我的积分'
* 页面包括: 当前积分 , 积分说明 , 积分兑换 . 积分记录
```

![查看积分](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/jf.png?raw=true "查看积分")

### 11.退出账户 ###
```
* 进入方式:主菜单'设置'
* 退出当前登录用户,以便切换用户
* 如果用户登录时选择了自动登录,那么只有退出当前用户才能切换用户
```

![退出账户](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/eacc.gif?raw=true "退出账户")

### 12.用户反馈 ###
```
* 进入方式:设置页'问题反馈'
* 反馈信息包括: 问题描述. 反馈人联系方式
```
![用户反馈](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/feedback.gif?raw=true "用户反馈")

### 13.帮 助 ###
```
* 进入方式:主菜单'帮助' 右上角弹出菜单'帮助/help' 设置页'帮助'
* 内容包括:标签规则(说明), 标签方法(程序操作方式)
```
![帮 助](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/help.gif?raw=true "帮 助")

### 14.关 于 ###
```
* 进入方式:设置页'关于'
* 内容包括:1.当前版本号,2.联系方式, 3.邮箱, 4.QQ
```
![关 于](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/about.png?raw=true "关 于")

## 管理员部分 ##

### 1.查看和修改普通用户信息 ###
```
* 进入方式:管理员页'查看用户信息'
* 可查询所有有非管理员用户名列表
* 点击列表任意用户可进入查看用户详细信息
* 查询信息包括:
    1.用户名
    2.联系方式(可修改)
    3.邮箱(可修改)
    4.所获积分
    5.今日任务完成情况
    6.历史总标签数目
    7.历史标签成功总数
    8.授权管理员(可授权可撤销)
* 管理员可以授权普通用户管理员权限,也可以收回权限
```

![查看和修改普通用户信息](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/aauinfo.gif?raw=true "查看和修改普通用户信息")

### 2.上传图片资料 ###
```
* 进入方式:管理员页:点击'上传图片资料'
* 支持批量上传,一次最大上传数量为10
* 可预览选择图片,包括放大缩小,拖动
* 可切换选择不同目录下的图片
* 选择,取消选择,上传
```
![上传图片资料](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/upload.gif?raw=true "上传图片资料")
### 3.设置标签相关 ###
```
* 进入方式:管理员页'标签设置'
* 内容包括:
    1.图片标签依赖数(可修改)
    2.每日任务数(可修改)
    3.上次修改时间
    4.修改人
* 图片标签依赖数(因为我们对图片的标签化是根据用户标签反馈,我们收集用户的标签再判断标签的正确性,而图片标签依赖数
* 是指对一张图片收集指定份标签作为最后判断依据,如改数值设置为'5'时,则需要一张图片收集5个人的标签)
* 每日任务数(即一张图片真正需要派送给用户的数量,为保证能收集够图片标签依赖数,所以每日任务数要大于或等于图片标签依赖数)
```
![设置标签相关](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/ls.gif?raw=true "设置标签相关")
### 4.查看用户反馈 ###
```
* 进入方式:管理员页'查看用户反馈'
* 页面信息:
    1.问题状态
    2.反馈内容
    3.反馈人名称
    4.反馈提交时间
```
![查看用户反馈](https://github.com/LSL-Git/ImageLabelerApp/blob/master/OtherFile/qf.gif?raw=true "查看用户反馈")

```
另外，管理员拥有普通用户的所有功能
```
## [服务端说明文档](http://42.123.127.93:10080/LSL/Document/blob/master/README-SERVER.md)
