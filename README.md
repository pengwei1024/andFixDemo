# andFixDemo
andFix demo


### 支持
- 支持修改变量
- 新增代码
- 新增局部变量

### 不支持
- 新增类
```java
 Process: com.apkfuns.andfixdemo, PID: 457
java.lang.NoClassDefFoundError: Failed resolution of: Lcom/apkfuns/andfixdemo/Test;
```

- 新增全局变量
```java
java.lang.RuntimeException: can,t add new Field:a(I), in class :Lcom/apkfuns/andfixdemo/MainActivity;
```

- 修改string.xml，修改manifest，修改xml


- try catch
```java
Process: com.apkfuns.andfixdemo, PID: 18899
java.lang.NoClassDefFoundError: Failed resolution of: Lcom/apkfuns/andfixdemo/MainActivity_CF;
at com.apkfuns.andfixdemo.MainActivity$2_CF.run(MainActivity.java:105)
at android.os.Handler.handleCallback(Handler.java:815)
at android.os.Handler.dispatchMessage(Handler.java:104)
at android.os.Looper.loop(Looper.java:194)
at android.app.ActivityThread.main(ActivityThread.java:5785)
at java.lang.reflect.Method.invoke(Native Method)
at java.lang.reflect.Method.invoke(Method.java:372)
at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1039)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:834)
```                                                                          
