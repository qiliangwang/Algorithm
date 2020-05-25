

grep "ConcurrentModificationException" /var/log/treasure-package-business/treasure-package-business.log.2020-05-19

之前出现了ConcurrentModificationException，升级了版本，改的其实还蛮简单的。之前是这样的

```java
public List<BindVarCondition> getSortedBindVarConditionsSnapshot() {

    Collections.sort(bindVarConditions, new BindVarConditionComparable());

    return currentBindColumns;
}
```

现在新建new了一个新对象copyOnWrite思想的运用。。

```java
public List<BindVarCondition> getSortedBindVarConditionsSnapshot() {

    List<BindVarCondition> currentBindColumns = new ArrayList<BindVarCondition>(bindVarConditions);
    Collections.sort(currentBindColumns, new BindVarConditionComparable());

    return currentBindColumns;
}
```

