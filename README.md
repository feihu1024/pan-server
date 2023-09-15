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
    - 登录成功后使用AfterScript自动添加全局header
    ```js
      var tokenData = ke?.response?.data
      if(tokenData && tokenData?.access_token) {
          ke.global.setHeader("Authorization",tokenData.token_type +" "+ tokenData.access_token)
          setTimeout(() => {
              location.href = "/doc.html"
          },800)
          console.log(ke,tokenData)
      }
    ```

- Authorization
    - ftp与http使用同一张用户表，密码通过md5加密后存入数据库
    - ftp密码依然使用明文登录
    - http密码在前端进行md5加密后使用密文传输，其中client_secret字段也需要加密
    - client_id字段="pan-server-web", secret字段="5EBE2294ECD0E0F08EAB7690D2A6EE69"("secret"加密后的值)

## 问题记录
- 开始编写http文件访问接口
- 集成redis缓存
- 统一jackson与fastjson 实现统一格式化null值被自动过滤的问题
- controller层添加异常处理，保证sql错误信息不会输出
- 如何使用微服务架构拆分权限管理模块和文件服务模块
- 研究如何实现everything文件搜索
- 研究如何实现文本编辑功能，如何实现文件预览自动预览功能
- 如何解决swagger参数动态化配置的问题，解决导入到postman中接口参数识别不准确的问题
- 编写和完善README.md文档