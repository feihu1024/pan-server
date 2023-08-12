# pan-server

## 基本信息
    Swagger访问地址:    http://localhost:3000/doc.html#/home
    ftp登录地址:        open localhost 3100

## 关于登录
- 登录接口
    - 登录接口为： http://192.168.0.105:3000/oauth/token
    - 其他登录方式参见： https://blog.kdyzm.cn/post/24#1-授权码认证模式
    ```js
      client_id: "pan-server-web"
      client_secret: "5EBE2294ECD0E0F08EAB7690D2A6EE69"
      grant_type: "password"
      username: "admin"
      password: "E10ADC3949BA59ABBE56E057F20F883E"
    ```
- 请求头 token验证
    - Authorization字段格式为： Bearer + 空格 + access_token
    ```
      Authorization: “Bearer 85809d55-17a3-47ae-ad0f-0596c379a13e”
    ```
- Authorization
    - ftp与http使用同一张用户表，密码通过md5加密后存入数据库
    - ftp密码依然使用明文传输
    - http密码在前端进行md5加密后使用密文传输，其中client_secret字段也需要加密
    - client_id字段="pan-server-web", secret字段="5EBE2294ECD0E0F08EAB7690D2A6EE69"("secret"加密后的值)

## 问题记录
- 集成SpringSecurity实现用户权限管理
    - swagger中显示自定义登录接口，添加login后置脚本
    - Authorization使用数据库存储
    - 统一异常处理，要区分权限异常与其他参数异常
    - 区分管理员权限
    - 继续OAuth2.0集成工作
- 继续用户管理接口，用户的增删改查，添加角色字段
- 开始编写http文件访问接口
- 研究如何实现everything文件搜索
- 研究如何实现文本编辑功能，如何实现文件预览自动预览功能
- 如何解决swagger参数动态化配置的问题
- 编写和完善README.md文档