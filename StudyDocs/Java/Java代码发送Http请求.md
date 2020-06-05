### 1.HTTP中GET和POST的区别

> 首先要了解下GET和POST的区别。
>
> HTTP定义了4种与服务器交互方法：GET，POST，PUT，DELETE。URL全称是资源描述符，可以这样认为：一个URL地址，它用于描述一个网络上的资源，而HTTP中的GET，POST，PUT，DELETE可以理解为就对应着对这个资源的查，改，增，删4个操作。GET一般用于获取/查询资源信息，而POST一般用于更新资源信息。GET和POST的区别主要从以下几个方面理解：
>
> + 用途
>   根据HTTP规范，GET用于信息获取，用于获取信息而非修改信息。GET请求一般不应产生副作用。它仅仅是获取资源信息，就像数据库查询一样，不会修改，增加数据，不会影响资源的状态。对同一URL的多个请求应该返回同样的结果。 **POST可能修改变服务器上的资源的请求**。
> + 请求数据的传递方式
>   GET请求的数据会附在URL之后（就是把数据放置在HTTP协议头中），以?分割URL和传输数据，参数之间以&相连，如：buy.jsp?product=apple&amount=1111&verify=%E4%BD%A0%E5%A5%BD。如果数据是英文字母或者数字，原样发送，如果是空格，转换为+，如果是中文或者其他字符，则直接把字符串用BASE64加密，得出如：%E4%BD%A0%E5%A5%BD，其中％XX中的XX为该符号以16进制表示的ASCII。而**POST把提交的数据则放置在是HTTP包的包体中**。
> + 提交数据的大小限制
>   GET方式提交的数据最多只能是1024字节(GET请求中参数是附在url后面的，实际上这个长度是url长度的要求)。理论上POST没有限制，可传较大量的数据。这一点可以先简单这么理解。
> + 安全性
>   POST的安全性要比GET的安全性高。比如，通过GET提交数据，用户名和密码将明文出现在URL上。
> + 常见场景
>    在FORM表单中，Method默认为"GET"。在浏览器地址栏中输入url发生请求都是GET，如果要发送POST请求就需要通过提交form表单来完成。

### 2.Java代码发送GET请求和POST请求

> Java中应该有好多种方式，可以发送GET和POST请求。这里介绍两种：通过HttpURLConnection和通过Apache HttpClient库。

> + 2.1 通过HttpURLConnection发送GET和POST请求

> > 这种方式基本上算是java原生的，不需要导入任何jar包依赖就可以运行。代码如下：
> >
> > ```import java.io.*;
> >  import java.net.HttpURLConnection;
> >  import java.net.MalformedURLException;
> >  import java.net.URL;
> >  /**
> >  \* Created by chengxia on 2018/12/4.
> >  */
> >  public class HttpURLConnectionDemo {
> >    public String doPost(String URL){
> >      OutputStreamWriter out = null;
> >      BufferedReader in = null;
> >      StringBuilder result = new StringBuilder();
> >      HttpURLConnection conn = null;
> >      try{
> >        URL url = new URL(URL);
> >        conn = (HttpURLConnection) url.openConnection();
> >        conn.setRequestMethod("POST");
> >        //发送POST请求必须设置为true
> >        conn.setDoOutput(true);
> >        conn.setDoInput(true);
> >        //设置连接超时时间和读取超时时间
> >        conn.setConnectTimeout(30000);
> >        conn.setReadTimeout(10000);
> >        conn.setRequestProperty("Content-Type", "application/json");
> >        conn.setRequestProperty("Accept", "application/json");
> >        //获取输出流
> >        out = new OutputStreamWriter(conn.getOutputStream());
> >        String jsonStr = "{\"qry_by\":\"name\", \"name\":\"Tim\"}";
> >        out.write(jsonStr);
> >        out.flush();
> >        out.close();
> >        //取得输入流，并使用Reader读取
> >        if (200 == conn.getResponseCode()){
> >          in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
> >          String line;
> >          while ((line = in.readLine()) != null){
> >            result.append(line);
> >            System.out.println(line);
> >          }
> >        }else{
> >          System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
> >        }
> >      }catch (Exception e){
> >        e.printStackTrace();
> >      }finally {
> >        try{
> >          if(out != null){
> >            out.close();
> >          }
> >          if(in != null){
> >            in.close();
> >          }
> >        }catch (IOException ioe){
> >          ioe.printStackTrace();
> >        }
> >      }
> >      return result.toString();
> >    }
> > 
> > public String doGet(String URL){
> >      HttpURLConnection conn = null;
> >      InputStream is = null;
> >      BufferedReader br = null;
> >      StringBuilder result = new StringBuilder();
> >      try{
> >        //创建远程url连接对象
> >        URL url = new URL(URL);
> >        //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
> >        conn = (HttpURLConnection) url.openConnection();
> >        conn.setRequestMethod("GET");
> >        //设置连接超时时间和读取超时时间
> >        conn.setConnectTimeout(15000);
> >        conn.setReadTimeout(60000);
> >        conn.setRequestProperty("Accept", "application/json");
> >        //发送请求
> >        conn.connect();
> >        //通过conn取得输入流，并使用Reader读取
> >        if (200 == conn.getResponseCode()){
> >          is = conn.getInputStream();
> >          br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
> >          String line;
> >          while ((line = br.readLine()) != null){
> >            result.append(line);
> >            System.out.println(line);
> >          }
> >        }else{
> >          System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
> >        }
> >      }catch (MalformedURLException e){
> >        e.printStackTrace();
> >      }catch (IOException e){
> >        e.printStackTrace();
> >      }catch (Exception e){
> >        e.printStackTrace();
> >      }finally {
> >        try{
> >          if(br != null){
> >            br.close();
> >          }
> >          if(is != null){
> >            is.close();
> >          }
> >        }catch (IOException ioe){
> >          ioe.printStackTrace();
> >        }
> >        conn.disconnect();
> >      }
> >      return result.toString();
> >    }
> > 
> > public static void main(String[] args) throws Exception {
> >      HttpURLConnectionDemo http = new HttpURLConnectionDemo();
> >      
> >      System.out.println("Testing 1 - Do Http GET request");
> >      http.doGet("http://localhost:8080");
> > 
> > System.out.println("\nTesting 2 - Do Http POST request");
> >      http.doPost("http://localhost:8080/json");
> > 
> > }
> > 
> > }
> > ```
> >
> > 运行的输出如下：
> >
> > ```
> > Testing 1 - Do Http GET request
> > <html>
> >   <head>
> >     <title>Hello World!</title>
> >   </head>
> >   <body>
> >     <h1>Hello Word!</h1>
> >   </body>
> > </html>
> > Testing 2 - Do Http POST request
> > [{name:'Kobe', team:‘Lakers’},{name:'Tim', team:‘Spurs’}]
> > Process finished with exit code 0
> > 
> > ```
> >
> > 从这个例子的代码中就可以看出，GET请求向服务器发送的数据，都放在url中，这样在发送请求是不用向请求正文中写入数据。而POST请求在发送时，必须先将发送的数据，写入到请求正文中。下面的apache httpclient实现中，也能看出这个区别。
>
> + 2.2通过Apache HttpClient发送GET和POST请求
>
>   > 这里需要用到Apache HttpClient的依赖包，所以要先去[官网](http://hc.apache.org/downloads.cgi)下载依赖的jar包：
>   >
>   > Apache官网下载依赖jar包
>   >
>   > 解压之后，将lib目录下所有的jar包文件，导入到工程的依赖目录(我曾经天真的以为只需要一个httpclient-4.5.6.jar，然而在编译时各种报错，全部导入到就好了)：
>   >
>   > 下载的jar包解压之后
>   >
>   > 全部导入到工程的依赖目录中
>   >
>   > 最后，Java代码如下：
>   >
>   > ```
>   > import org.apache.http.HttpEntity;
>   > import org.apache.http.client.ClientProtocolException;
>   > import org.apache.http.client.config.RequestConfig;
>   > import org.apache.http.client.methods.CloseableHttpResponse;
>   > import org.apache.http.client.methods.HttpGet;
>   > import org.apache.http.client.methods.HttpPost;
>   > import org.apache.http.entity.StringEntity;
>   > import org.apache.http.impl.client.CloseableHttpClient;
>   > import org.apache.http.impl.client.HttpClients;
>   > import org.apache.http.util.EntityUtils;
>   > import java.io.IOException;
>   > /**
>   >  * Created by chengxia on 2018/12/5.
>   >  */
>   > public class ApacheHttpClientDemo {
>   >     public String doGet(String url){
>   >         CloseableHttpClient httpClient = null;
>   >         CloseableHttpResponse response = null;
>   >         String result = "";
>   >         try{
>   >             //通过默认配置创建一个httpClient实例
>   >             httpClient = HttpClients.createDefault();
>   >             //创建httpGet远程连接实例
>   >             HttpGet httpGet = new HttpGet(url);
>   >             //httpGet.addHeader("Connection", "keep-alive");
>   >             //设置请求头信息
>   >             httpGet.addHeader("Accept", "application/json");
>   >             //配置请求参数
>   >             RequestConfig requestConfig = RequestConfig.custom()
>   >                     .setConnectTimeout(35000) //设置连接主机服务超时时间
>   >                     .setConnectionRequestTimeout(35000)//设置请求超时时间
>   >                     .setSocketTimeout(60000)//设置数据读取超时时间
>   >                     .build();
>   >             //为httpGet实例设置配置
>   >             httpGet.setConfig(requestConfig);
>   >             //执行get请求得到返回对象
>   >             response = httpClient.execute(httpGet);
>   >             //通过返回对象获取返回数据
>   >             HttpEntity entity = response.getEntity();
>   >             //通过EntityUtils中的toString方法将结果转换为字符串，后续根据需要处理对应的reponse code
>   >             result = EntityUtils.toString(entity);
>   >             System.out.println(result);
>   > }catch (ClientProtocolException e){
>   >             e.printStackTrace();
>   >         }catch (IOException ioe){
>   >             ioe.printStackTrace();
>   >         }catch (Exception e){
>   >             e.printStackTrace();
>   >         }finally {
>   >             //关闭资源
>   >             if(response != null){
>   >                 try {
>   >                     response.close();
>   >                 }catch (IOException ioe){
>   >                     ioe.printStackTrace();
>   >                 }
>   >             }
>   >             if(httpClient != null){
>   >                 try{
>   >                     httpClient.close();
>   >                 }catch (IOException ioe){
>   >                     ioe.printStackTrace();
>   >                 }
>   >             }
>   >         }
>   >         return result;
>   >     }
>   >     public String doPost(String url){
>   >         //创建httpClient对象
>   >         CloseableHttpClient httpClient = HttpClients.createDefault();
>   >         CloseableHttpResponse response = null;
>   >         String result = "";
>   >         try{
>   >             //创建http请求
>   >             HttpPost httpPost = new HttpPost(url);
>   >             httpPost.addHeader("Content-Type", "application/json");
>   >             //创建请求内容
>   >             String jsonStr = "{\"qry_by\":\"name\", \"name\":\"Tim\"}";
>   >             StringEntity entity = new StringEntity(jsonStr);
>   >             httpPost.setEntity(entity);
>   >             response = httpClient.execute(httpPost);
>   >             result = EntityUtils.toString(response.getEntity(),"utf-8");
>   >             System.out.println(result);
>   >         }catch (Exception e){
>   >             e.printStackTrace();
>   >         }finally {
>   >             //关闭资源
>   >             if(response != null){
>   >                 try {
>   >                     response.close();
>   >                 }catch (IOException ioe){
>   >                     ioe.printStackTrace();
>   >                 }
>   >             }
>   >             if(httpClient != null){
>   >                 try{
>   >                     httpClient.close();
>   >                 }catch (IOException ioe){
>   >                     ioe.printStackTrace();
>   >                 }
>   >             }
>   >         }
>   >         return result;
>   >     }
>   >     public static void main(String[] args) throws Exception {
>   >         ApacheHttpClientDemo http = new ApacheHttpClientDemo();
>   > System.out.println("Testing 1 - Do Http GET request");
>   >         http.doGet("http://localhost:8080");
>   > System.out.println("\nTesting 2 - Do Http POST request");
>   >         http.doPost("http://localhost:8080/json");
>   > }
>   > }
>   > 
>   > ```
>   >
>   > 由于是向相同的url发送请求，这个例子的输出和前面的例子是一样的。

**参考**

- [How to send HTTP      request GET/POST in Java](https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/)
            分别介绍了Standard HttpURLConnection和Apache HttpClient     library发送http请求的方法，有例子。
- [Java - sending HTTP      parameters via POST method easily](https://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily)
            这个链接中提到了get请求参数通过url传递，post通过正文传递。
            In a GET request, the parameters are sent as part of the URL.
            In a POST request, the parameters are sent as a body of the request,     after the headers.
- [浅谈HTTP中Get与Post的区别](http://www.cnblogs.com/hyddd/archive/2009/03/31/1426026.html)
- [Java      通过HttpURLConnection Post方式提交json，并从服务端返回json数据](https://blog.csdn.net/qq_26552805/article/details/78361413)
            一个通过HTTPURLconnection发送json数据的例子

