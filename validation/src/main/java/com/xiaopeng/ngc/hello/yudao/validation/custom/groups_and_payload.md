在Java的Bean Validation API中，`groups`和`payload`是注解的两个属性，它们提供了额外的元数据来控制和扩展校验行为。

### groups

`groups`属性允许你指定校验分组，这样你可以根据不同的上下文执行不同的校验。例如，你可能希望在创建对象时应用一组校验规则，在更新对象时应用另一组规则。

#### 示例：

```java
public class User {

    @NotNull(groups = ValidationGroups.Create.class)
    @Null(groups = ValidationGroups.Update.class)
    private Long id;

    @NotNull
    @Size(min = 5, max = 200, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    // getters and setters
}

public interface ValidationGroups {
    interface Create {}
    interface Update {}
}

// 创建用户时的校验
Set<ConstraintViolation<User>> createViolations = validator.validate(user, ValidationGroups.Create.class);

// 更新用户时的校验
Set<ConstraintViolation<User>> updateViolations = validator.validate(user, ValidationGroups.Update.class);
```

在这个例子中，`id`字段在创建用户时必须为`null`，而在更新用户时必须不为`null`。`name`字段在创建和更新时都需要满足相同的校验规则。

### payload

`payload`属性用于客户端指定校验违规的严重程度或其他任何元数据。`payload`不会影响校验逻辑，它仅用于携带客户端定义的元数据。

#### 示例：

```java
public class User {

    @NotNull(message = "Name cannot be null", payload = Severity.Info.class)
    private String name;

    // getters and setters
}

public class Severity {
    public static class Info implements Payload {}
    public static class Error implements Payload {}
}

// 校验用户
Set<ConstraintViolation<User>> violations = validator.validate(user);

for (ConstraintViolation<User> violation : violations) {
    Payload payload = violation.getConstraintDescriptor().getPayload().iterator().next();
    if (payload instanceof Severity.Info) {
        // 处理信息级别的违规
    } else if (payload instanceof Severity.Error) {
        // 处理错误级别的违规
    }
}
```

在这个例子中，如果`name`字段为`null`，违规的严重程度被标记为`Info`。在处理违规时，你可以检查违规的`payload`来决定如何响应。

总结来说，`groups`用于根据上下文执行不同的校验，而`payload`用于为校验违规提供额外的元数据，这些元数据可以在校验后被用来决定如何处理这些违规。