0.确定vpn已经连接上,在本地配置好jdk1.8、>=mysql 8.0、maven、git客户端、mysql客户端

1.配置Eclipse或IntelliJ IDEA
    
    a.需要配置maven的setting.xml:
    
    <mirror>   
      <id>nexus</id>
      <mirrorOf>*</mirrorOf>   
      <url>http://192.168.100.239:8081/nexus/content/groups/public/</url>   
    </mirror>   
    
    b.配置lombok（百度)

2.获取实例项目(以后的业务服务参照此项目构建)
    
    a.git clone http://gitlab.inuol.com/gitlab/inuol-ark/com-ark-hngxt-product.git
    b.cd com-ark-hngxt-product
    c.git checkout -b dev #建立开发分支，以后提交项目流程建议为：dev->master->origin/master(公司机器上有详细文档）
    d.从Eclipse或IntelliJ IDEA中导入项目

3.创建数据库：CREATE DATABASE IF NOT EXISTS com_ark_hngxt_product CHARSET=utf8;

4.开发代码说明：
    
    a.根据产品文档设计实体
	建议：
	直接设计jpa 实体类（类的属性上请包括swagger @ApiModelProperty注解、javax.validation.constraints属性验证注解、@Column注解）
	数据库表将在实例启动后自动生成。（设计jpa实例类稳定后，关闭自动生成，即：设置spring.jpa.hibernate.ddl-auto: none)
    
    b.代码层级：model、controller、servcie、repository、domain
	model: 类和属性请提供swagger注解以生成文档，javax.validation.constraints属性验证注解，提供可直接从实例类复制
	controller:类和方法请提供swagger注解以生成文档
	servcie:请在类或者方法上提供@Transactional
    
    c.controller层的说明：
	增加操作: method=POST, 请求体提供AddXXX mdoel对象，正常返回响应码:200 ,响应体返回主键id medel
	删除操作: method=DELETE, 请求体提供删除多个id集合，单个删除时请求参数形式提供id，正常返回响应码:204，无响应体返回
	修改操作: 请求体提供UpdateXXX mdoel对象，修改所有jpa实体属性method=PUT(所有数据重置)，修改jpa实体部分属性method=PATCH(大部分情况),正常返回响应码:204，无响应体返回
	查询操作:  method=GET 单个查询通过Path提供id,条件查询请求体提供QueryXXX mdoel对象,正常返回响应码:200 ,单个查询返回XXXDetails model,条件查询根据需要返回分页信息等
	根据业务提供基于restful风格的其它接口api
    
    d.关于权限注解的说明：
	controller的方法，默认要求登录用户才能访问，不登录的访问方法，可在yml文件中配置authsettings.free-urls上添加，权限验证注解：
	@RoleAuthority({"xxxxx"})：表示当前用户分配的了含有xxxxx权限标识的角色,才能访问此方法
	@MenuAuthority({"xxxxx"}) 表示当前用户分配的了含有xxxxx权限标识的菜单(通过角色）,才能访问此方法
   
	e.关于异常：
	系统出错返回数据格式为｛"name":"<异常类名>"，message:"xxxxxxxxxxxxx"},在开发和测试阶段可在yml配置com.ark.dependencies.common: dev,这样返回错误将提供："details"属性，便于开发调试
	通用异常类：
	AuthenticationException：没有登录时使用
	AuthorizationException：没有访问权限时使用
	BasicException：所有自定义异常的基类,可根据需要，继承此类，自定义实现异常类，类名会在返回数据中，即name对应的值，前端可用此值作定制开发
	ResourceException：不能获取数据库、缓存服务等资源时使用
	RestApiException：controller层使用
	ServiceException：service层使用
	UnknowException：未知异常，一般系统内部使用
	
	f.日志说明：
	对于增加、修改、删除操作建议记录日志操作，查询可不作要求
	日志在dev、test模式时会在控制台、文件同时记录日志
	日志在prod模式时只文件中记录日志，路径及文件默认配置为：logs/${spring.cloud.config.name}.log，可根据运维要求设置

5.关于测试

	a.本机单独测试：http://localhost:9001/doc.html  请求头提供x-auth-token：
	admin用户的测试token值为：
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ0b2tlbkNyZWF0ZVRpbWUiOiIyMDIwLTAzLTAyIDIwOjM4OjAyMSIsInBob25lbnVtYmVyIjoiMTM4MDAwMDAwMDAiLCJkZXB0SWQiOjEwMywiaXNzIjoiY29tLmFyayIsInVzZXJOYW1lIjoi57O757uf566h55CG5ZGYIiwidXNlcklkIjoxLCJkZXB0QXV0aHMiOiJbMTAwLDEwMSwxMDIsMTAzLDEwOCwxMDldIiwicm9sZUF1dGhzIjoiW1wiYWRtaW5cIixcImNvbW1vblwiXSIsIm1lbnVBdXRocyI6IltcInN5c3RlbTp1c2VyOnZpZXdcIixcInN5c3RlbTp1c2VyOnZpZXdcIixcInN5c3RlbTp1c2VyOmFkZFwiLFwic3lzdGVtOnVzZXI6YWRkXCIsXCJzeXN0ZW06dXNlcjplZGl0XCIsXCJzeXN0ZW06dXNlcjplZGl0XCIsXCJzeXN0ZW06dXNlcjpyZW1vdmVcIixcInN5c3RlbTp1c2VyOnJlbW92ZVwiXSIsInBvc3RJZHMiOiJbMSwyXSIsImxvZ2luTmFtZSI6ImFkbWluIiwiZXhwIjoxNTgzMTk1OTAxLCJqdGkiOiIzMmFjNDRjYy0wNDcyLTQ0MGEtYjI2Ny0wOWFlMjM5MDkwOWEiLCJ0b2tlbkV4cGlyZVBlcmlvZCI6NDMyMDAwMDB9.JJZwqBYZiQcNehXjdcjXfquGAQ-cDCl-laiorrHxWJU7ilRE9YG4AG6I4FoKKFysc1ixSc0NFoDfv6mZsfD4Z0nn-Hl9yHb51JmWXWh5Vq5DaDN4urmbie64_vSqUn9Pj5fZZUYXtFeDVY4Xv4ve_S43yueEX69A_N9eo77iWjlgGkbDK4Y9ZXVl02awS_jzNnkYE2TRs0Xvuo_Av7tIxtwRgQnUIyijSk3Z-yuNQhCfzBBOZJUlDsBj54LevbPJ94TVzvOzkbhIUkp_eQxUBF1MCmx9a3vgLW7TPcZhlZSrMWVeXFM1XwLeLb0WVFLHIPcLe7w8V-RtsPtOxW05Dw
	test用户的测试token值为：
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ0b2tlbkNyZWF0ZVRpbWUiOiIyMDIwLTAzLTAyIDIwOjM2OjAyNyIsInBob25lbnVtYmVyIjoiMTM5MDAwMDAwMDAiLCJkZXB0SWQiOjEwNSwiaXNzIjoiY29tLmFyayIsInVzZXJOYW1lIjoi5p2O5YyWIiwidXNlcklkIjoyLCJkZXB0QXV0aHMiOiJbMTAwLDEwMSwxMDIsMTAzLDEwOCwxMDldIiwicm9sZUF1dGhzIjoiW1wiY29tbW9uXCJdIiwibWVudUF1dGhzIjoiW1wic3lzdGVtOnVzZXI6dmlld1wiLFwic3lzdGVtOnVzZXI6YWRkXCIsXCJzeXN0ZW06dXNlcjplZGl0XCIsXCJzeXN0ZW06dXNlcjpyZW1vdmVcIl0iLCJwb3N0SWRzIjoiWzEsMiw0XSIsImxvZ2luTmFtZSI6InRlc3QiLCJleHAiOjE1ODMxOTU3ODcsImp0aSI6Ijc1MDRlMTViLWFjMWEtNDJlNC1iODRiLWNhZDEwYWQ3YmNmYSIsInRva2VuRXhwaXJlUGVyaW9kIjo0MzIwMDAwMH0.ncaFsj3j1HKKX07gNXoePz-hR3fuvxYOG8297uXWE63UtRNaZ8_pcRRuH15ccjAbptSYutxaX-zFrjx1goiVQcrCdD8ssGKrAxR77XOKn2CJcbc95SIt-wvT23EM-WyK0JLIbvqkzE8Mkhj-HLxiqIYzESJTLzcs6PeihT6Oht6xcN6GGcFQL5tprBGLK6ca_y7BaNNpv0MCw3BD6DC0XnaEglLyTcohhf2CYaArFISlKxyeE2MxAYSP5drW4Sr0MLcaAqNk-P3XL_ulzevu184OuKyY-SmQmxTwiT8F556AH5qjrstJXH7SDnGzsMmyYxrZQBuFQythOAR6yz4fPw
     
	b.通过网关(在测试环境）测试：http://<网关地址>/hngxt/product/doc.html,可以访问http://<网关地址/basic/uaa/doc.html页面创建token，作为请求头x-auth-token-user的值提交，注意不是：x-auth-token，注意由于创建token页面有相应的cookie生成，所以不能脱离当前域测试

6.关于代码部署

	直接git push代码回gitlab服务器，到时运维会个每个服务一个重新构建的地址，在相应网上点击重新构建即可
	开发环境：java -jar com-ark-hngxt-product-0.0.1-SNAPSHOT.jar --spring.cloud.config.name=com-ark-hngxt-product-0 --spring.cloud.config.label=master --spring.cloud.config.profile=dev
	测试环境：java -jar com-ark-hngxt-product-0.0.1-SNAPSHOT.jar --spring.cloud.config.name=com-ark-hngxt-product-0 --spring.cloud.config.label=master --spring.cloud.config.profile=test
	生产环境：java -jar com-ark-hngxt-product-0.0.1-SNAPSHOT.jar --spring.cloud.config.name=com-ark-hngxt-product-0 --spring.cloud.config.label=master --spring.cloud.config.profile=prod
	通过配置服务器的使用，配置属性中spring.cloud.config.name中不同数字，可以实现启动多个服务进程,通过网关访问时可实现负载均衡
     
7.其它

	GET：读取（Read）
	POST：新建（Create）
	PUT：更新（Update）
	PATCH：更新（Update），通常是部分更新
	DELETE：删除（Delete）

	http://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html

	1xx：相关信息
	2xx：操作成功
	3xx：重定向
	4xx：客户端错误
	5xx：服务器错误     
     
     