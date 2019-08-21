# switchlibrary
这个库的主要作用是集成了一些webview和原生的交互(仅供内部交流)
直接下载当前library到本地,然后选择Import Module的方式到你的项目 最后别忘记依赖
初始化:
	        SwitchMainEnter.getInstance().initOCR(this,AK,SK);
		1、我们需要进入百度云文字识别的开发平台，进行一系列的注册，登陆。
https://cloud.baidu.com/product/ocr
2、创建应用





创建应用.PNG






填写信息.png

在创建应用的时候，应用名称我们可以随便写一个，但是我们要注意的是：我们在写文字识别包名的时候一定要写我们项目的包名，不然在识别的时候会提示你获取Token出错，接下来我们就直接创建我们的应用。
3、查看管理应用列表





查看管理应用列表.png

这个就是我们刚刚创建的应用，我们需要记住它的API Key和Secret Key，在我们的项目中可能会用到。


	

