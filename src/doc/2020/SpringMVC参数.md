springMVC中对参数的使用有很多种模式

接受参数：

首先我们要区分请求GET和POST的区别

GET请求：@GetMapping(value = "/v1/room")

```
@RequestParam Long roomId 来接受参数
public LiveRoomVO queryByRoomId(HttpServletRequest request, @RequestParam Long roomId)
有时候参数过多可以使用对象接受
public HomePageVo homepage(HttpServletRequest request, HomePageModel homePageModel) {
```

Post请求

处理返回

当value为null的时候key不显示使用@JsonInclude，使用@JsonIgnore不显示该字段。

```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageInfoVo implements IInitPageInfo{

  private Integer pageId;
  
  private Integer pageSize;
  
  @JsonIgnore
  private Integer totalSize;
  
  private boolean lastPage = true;

  @Override
  public void initPageInfo(Integer pageId, Integer pageSize, List data) {
    this.lastPage = data.size() < pageSize;
    this.pageId = pageId;
    this.pageSize = pageSize;
  }
}
```