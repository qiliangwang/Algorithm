在ts中可以如下使用构造函数

```typescript
class Person {
    
    public name: String;
    
    constructor(name: String) {
        this.name = name;
    }
}
```

当然有一种简化的写法

```typescript
class Person {
    constructor(public name: String) {}
}
```

ts中可以使用装饰器来扩展类的能力，先看类的装饰器

```typescript
function testDecorator(flag: boolean) {
    if (flag) {
        return function (constructor: any) {
            constructor.prototype.getName = () => {
                console.log('test decorator')
            }
        }
    } else {
        return function (constructor: any) {};
    }
}

@testDecorator(true)
class Test {}

const test = new Test();
(test as any).getName();
```

我们意识到这样有个问题，我们使用了prototype，那么如何不使用prototype呢，其实也是很简单的。我们可以使用继承，但是当我们这样写的时候，之前我们知道有个叫做泛型的东西

```typescript
function helloDecorator1(constructor: any) {
    return class extends constructor {};
}
```

加上之后会报错 TS2507: Type 'T' is not a constructor function type.只需要简单修改一下

```typescript
function helloDecorator<T>(constructor: T) {
    return class extends constructor {};
}
```

```
<T extends new(...args: any[]) => any>
```

写成这样就没什么问题了。仔细观察T继承了new(...args: any[]) => any这个东西，而这个东西new就是构造函数（参数为any的数组，返回值为any）

```typescript
function helloDecorator<T extends new(...args: any[]) => any>(constructor: T) {
    return class extends constructor {};
}
```

```typescript
function helloDecorator<T extends new(...args: any[]) => any>(constructor: T) {
    return class extends constructor {
        name = 'wangql';
        
        getName() {
            return this.name;
        }
    };
}
                        
@helloDecorator
class Test {
    name: string;
    constructor(name: string) {
        this.name = name;
    }
}

const test = new Test('zhh');

console.log(test)
```

刚刚写的装饰器woek了

```
class_1 { name: 'wangql' }
```

再看方法的装饰器

```typescript
function getNameDecorator(target: any, key: string) {
    console.log(target, key)
}

class Test {
    name: string;
    constructor(name: string) {
        this.name = name;
    }
    @getNameDecorator
    getName() {
        return this.name;
    }
}
```

对于普通方法 target对应的是类的prototype, key是函数名称

```
C:\Users\Administrator\WebstormProjects\ts-learn>ts-node src\simple_decorator.ts
Test { getName: [Function] } 'getName'
```

如果是静态方法的话，要怎么处理呢。

```typescript
class Test {
    name: string;
    constructor(name: string) {
        this.name = name;
    }

    @getNameDecorator
    static getName() {
        return '123';
    }
}
```

```
C:\Users\Administrator\WebstormProjects\ts-learn>ts-node src\simple_decorator.ts
{ [Function: Test] getName: [Function] } 'getName'
```

我们可以看到静态方法对应的是类的构造函数

其实还有第三个参数

```typescript
function getNameDecorator(target: any, key: string, descriptor: PropertyDescriptor) {
    console.log(target, key)
}
```

```typescript
class Test {
    name: string;
    constructor(name: string) {
        this.name = name;
    }

    @getNameDecorator
    getName() {
        return this.name;
    }
}

const test = new Test('zhh');
test.getName = () => {
    return '123';
};
```

如上可以看到，我们其实可以修改test的方法，而很多时候我们不希望这样修改。这个时候需要第三个参数了

```typescript
function getNameDecorator(target: any, key: string, descriptor: PropertyDescriptor) {
    descriptor.writable = false;
}
```

这样函数就不能被修改了

```
C:\Users\Administrator\WebstormProjects\ts-learn>ts-node src\simple_decorator.ts
Test { getName: [Function] } 'getName'
{ [Function: Test] getName: [Function] } 'getName'

C:\Users\Administrator\WebstormProjects\ts-learn\src\simple_decorator.ts:51
test.getName = () => {
            ^
TypeError: Cannot assign to read only property 'getName' of object '#<Test>'
    at Object.<anonymous> (C:\Users\Administrator\WebstormProjects\ts-learn\src\simple_decorator.ts:51:13)
    at Module._compile (module.js:653:30)
    at Module.m._compile (C:\Users\Administrator\AppData\Roaming\npm\node_modules\ts-node\src\index.ts:836:23)
    at Module._extensions..js (module.js:664:10)
    at Object.require.extensions.(anonymous function) [as .ts] (C:\Users\Administrator\AppData\Roaming\npm\node_modules\ts-node\src\index.ts:839:12)
    at Module.load (module.js:566:32)
    at tryModuleLoad (module.js:506:12)
    at Function.Module._load (module.js:498:3)
    at Function.Module.runMain (module.js:694:10)
    at main (C:\Users\Administrator\AppData\Roaming\npm\node_modules\ts-node\src\bin.ts:226:14)
```

当然也是可以通过这个参数来修改函数本身的

```typescript
function getNameDecorator(target: any, key: string, descriptor: PropertyDescriptor) {
    descriptor.value = function() {
        return 'wangql'
    }
}
```



接着看

```typescript
class Person {
    constructor() {
    }
    private _name: string;

    public get name() {
        return this._name;
    }

    public set name(name: string) {
        this._name = name;
    }
}

let person = new Person();

// person._name = "apple";  // 无法访问到_name变量

person.name = "apple";

console.log(person.name);  // 输出 apple
```

