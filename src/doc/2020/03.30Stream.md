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