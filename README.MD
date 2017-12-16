## NetInterceptor & NoNetInterceptor
1. 没有网络，有缓存，使用缓存（需要判断缓存是否过期），提示网络异常
2. 没有网络，没有缓存，提示网络异常
3. 有网络，有缓存且没有过期，使用缓存
4. 有网络，没有缓存或缓存过期， 请求网络
5. 需要对部分 url 添加可否使用缓存，设置缓存时长
6. 当服务器支持，有网络，没有更新数据,即服务端返回304，读取缓存
7. 当服务器支持，有网络，更新数据，读取网络

## HttpLoggingInterceptor
获取网络请求的耗时，网络出错时保存日志

## CommonInterceptor
添加公共参数

## addNetworkInterceptor() & addInterceptor()
https://www.jianshu.com/p/ba6e219a0af6



