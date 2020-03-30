常用约束注解

空值校验

```
@Null @NotNull @NotEmpty @NotBlank

@Null 字段为null
@NotEmpty会将字符串左右空格去除之后判断是否为空
@NotBlank直接判断字符串是否为空
```

范围校验

```
@Min @Size @Digits @Future @Negative

```
分组校验
@Validated({First.class, Second.class})

```
package com.imooc.zhangxiaoxi.validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象实体类
 * 用户信息类
 */
public class UserInfo {

    // 登录场景
    public interface LoginGroup {}

    // 注册场景
    public interface RegisterGroup {}

    // 组排序场景
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class
    })
    public interface Group {}

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空",
            groups = LoginGroup.class)
    private String userId;

    /**
     * 用户名
     * NotEmpty 不会自动去掉前后空格
     */
    @NotEmpty(message = "用户名称不能为空")
    private String userName;

    /**
     * 用户密码
     * NotBlank 自动去掉字符串前后空格后验证是否为空
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20,
            message = "密码长度不能少于6位，多于20位")
    private String passWord;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空",
            groups = RegisterGroup.class)
    @Email(message = "邮箱必须为有效邮箱")
    private String email;

    /**
     * 手机号
     */
    @Phone(message = "手机号不是158后头随便")
    private String phone;

    /**
     * 年龄
     */
    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 60, message = "年龄不能大于60岁")
    private Integer age;

    /**
     * 生日
     */
    @Past(message = "生日不能为未来或当前时间点")
    private Date birthday;

    /**
     * 好友列表
     */
    @Size(min = 1, message = "不能少于1个好友")
    private List<@Valid UserInfo> friends;
    
}

```

```
@RequestMapping("/save")  
public String save(@Validated({Second.class}) User user) {  
} 
```

使用SpringMVC拦截异常

```

```



在项目中可以对参数进行一系列的校验，一般我们在方法的最前面对参数进行校验，这样很简单也易懂，但是有一个最大的问题，就是复用性的问题。当然可以抽象成为一个public的方法提供出去，但是并不符合OOP的思想。

```java
@GetMapping(value = "/v1/balance")
@Response
public BalanceVo balance(HttpServletRequest request, BalanceModel balanceModel) {
  
  Integer subAccountTypeId = balanceModel.getSubAccountTypeId();
  if (subAccountTypeId.equals(SubAccountType.ANDROID.getId()) || subAccountTypeId.equals(SubAccountType.IOS.getId())) {
    throw new IllegalArgumentException("SubAccountType错误");
  }
  ......
}
```

这时候我们可以用java的validation来对其进行校验,只需要加上@SubAccountValidation就可以了,常见的有@NotNull，@NotEmpty等

```java
/**
 * @author wangql
 * @since 2020-03-23 17:04
 */
@Data
public class BalanceModel extends BaseModel {

  @NotNull
  @SubAccountValidation
  private Integer subAccountTypeId;

}
```



```java
/**
 * @author wangql
 * @since 2020-03-23 17:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = SubAccountValidator.class)
public @interface SubAccountValidation {

  String message() default "SubAccountType错误";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
```

```java
/**
 * @author wangql
 * @since 2020-03-23 17:17
 */
public class SubAccountValidator implements ConstraintValidator<SubAccountValidation, Integer> {

  @Override
  public boolean isValid(Integer subAccountType, ConstraintValidatorContext constraintValidatorContext) {
    return subAccountType.equals(SubAccountType.ANDROID.getId()) || subAccountType.equals(SubAccountType.IOS.getId());
  }
}
```

使用validation来实现一个注解，这样就可以对字段进行校验了。这样把逻辑都包装到一个类中，更加符合复用的概念。