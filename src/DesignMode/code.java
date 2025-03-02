//Singleton Pattern单例模式
//确保一个类只有一个实例，并提供一个全局访问点
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

//Factory Method Pattern工厂方法模式
//定义一个创建对象的接口，但由子类决定实例化哪个类
public abstract class Product {
    public abstract void use();
}

public class ConcreteProduct extends Product {
    public void use() {
        System.out.println("Using ConcreteProduct");
    }
}

public abstract class Creator {
    public abstract Product factoryMethod();
}

public class ConcreteCreator extends Creator {
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}

//Abstract Factory Pattern抽象工厂模式
//提供一个创建一系列相关或依赖对象的接口，而无需指定它们的具体类
public interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

public class ConcreteFactory1 implements AbstractFactory {
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

public interface ProductA {}
public interface ProductB {}

public class ConcreteProductA1 implements ProductA {}
public class ConcreteProductB1 implements ProductB {}


//Builder Pattern建造者模式
//将一个复杂对象的构建过程与其表示分离，使得同样的构建过程可以创建不同的表示
public class Product {
    private String partA;
    private String partB;

    public void setPartA(String partA) { this.partA = partA; }
    public void setPartB(String partB) { this.partB = partB; }
}

public abstract class Builder {
    protected Product product = new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public Product getResult() { return product; }
}

public class ConcreteBuilder extends Builder {
    public void buildPartA() { product.setPartA("PartA"); }
    public void buildPartB() { product.setPartB("PartB"); }
}

public class Director {
    private Builder builder;
    public void setBuilder(Builder builder) { this.builder = builder; }
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        return builder.getResult();
    }
}


//Prototype Pattern原型模式
//用于创建重复的对象，同时又能保证性能。通过克隆现有的对象实例创建新的实例
public class Prototype implements Cloneable {
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }
}


//Adapter Pattern适配器模式
//将一个类的接口转换成客户希望的另一个接口，使得原本由于接口不兼容而不能一起工作的类可以一起工作
public interface Target {
    void request();
}

public class Adaptee {
    public void specificRequest() {
        System.out.println("Called specificRequest");
    }
}

public class Adapter implements Target {
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    public void request() {
        adaptee.specificRequest();
    }
}

//Bridge Pattern桥接模式
//将抽象部分与它的实现部分分离，使它们都可以独立地变化
public abstract class Abstraction {
    protected Implementor implementor;
    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }
    public abstract void operation();
}

public class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }
    public void operation() {
        implementor.operationImpl();
    }
}

public interface Implementor {
    void operationImpl();
}

public class ConcreteImplementorA implements Implementor {
    public void operationImpl() {
        System.out.println("ConcreteImplementorA operation");
    }
}


//Composite Pattern组合模式
//将对象组合成树形结构以表示“部分-整体”的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性
public abstract class Component {
    public void add(Component c) {}
    public void remove(Component c) {}
    public Component getChild(int i) { return null; }
    public abstract void operation();
}

public class Leaf extends Component {
    public void operation() {
        System.out.println("Leaf operation");
    }
}

public class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    public void add(Component c) {
        children.add(c);
    }

    public void remove(Component c) {
        children.remove(c);
    }

    public Component getChild(int i) {
        return children.get(i);
    }

    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }
}

//Decorator Pattern装饰器模式
//动态地给对象添加一些额外的职责，就增加功能来说，装饰器模式比生成子类更为灵活
public interface Component {
    void operation();
}

public class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}

public abstract class Decorator implements Component {
    protected Component component;
    public Decorator(Component component) {
        this.component = component;
    }
    public void operation() {
        component.operation();
    }
}

public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }
    public void operation() {
        super.operation();
        addedBehavior();
    }
    public void addedBehavior() {
        System.out.println("ConcreteDecorator addedBehavior");
    }
}


//Facade Pattern外观模式
//为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用
public class Subsystem1 {
    public void operation1() {
        System.out.println("Subsystem1 operation1");
    }
}

public class Subsystem2 {
    public void operation2() {
        System.out.println("Subsystem2 operation2");
    }
}

public class Facade {
    private Subsystem1 subsystem1 = new Subsystem1();
    private Subsystem2 subsystem2 = new Subsystem2();

    public void operation() {
        subsystem1.operation1();
        subsystem2.operation2();
    }
}


//Flyweight Pattern享元模式
//运用共享技术有效地支持大量细粒度的对象
import java.util.HashMap;
import java.util.Map;

public interface Flyweight {
    void operation(String extrinsicState);
}

public class ConcreteFlyweight implements Flyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    public void operation(String extrinsicState) {
        System.out.println("IntrinsicState: " + intrinsicState + ", ExtrinsicState: " + extrinsicState);
    }
}

public class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
        }
        return flyweights.get(key);
    }
}


//Proxy Pattern代理模式
//为其他对象提供一种代理以控制对这个对象的访问
public interface Subject {
    void request();
}

public class RealSubject implements Subject {
    public void request() {
        System.out.println("RealSubject request");
    }
}

public class Proxy implements Subject {
    private RealSubject realSubject;

    public Proxy() {
        this.realSubject = new RealSubject();
    }

    public void request() {
        System.out.println("Proxy request");
        realSubject.request();
    }
}

//Chain of Responsibility Pattern职责链模式
//为解除请求的发送者和接收者之间的耦合，而使多个对象都有机会处理这个请求。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止
abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(int request);
}

class ConcreteHandler1 extends Handler {
    public void handleRequest(int request) {
        if (request < 10) {
            System.out.println("ConcreteHandler1 handled request " + request);
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("ConcreteHandler2 handled request " + request);
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setSuccessor(handler2);

        handler1.handleRequest(5);
        handler1.handleRequest(15);
        handler1.handleRequest(25);
    }
}


//Command Pattern命令模式
//将一个请求封装为一个对象，从而使您可以用不同的请求对客户进行参数化，对请求排队或记录请求日志，以及支持可撤销的操作
interface Command {
    void execute();
}

class Light {
    public void on() {
        System.out.println("Light is on");
    }
    public void off() {
        System.out.println("Light is off");
    }
}

class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.on();
    }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.off();
    }
}

class RemoteControl {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void pressButton() {
        command.execute();
    }
}

public class CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);
        remote.pressButton();
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}

//Interpreter Pattern解释器模式
//给定一个语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中的句子
interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    public boolean interpret(String context) {
        return context.contains(data);
    }
}

class OrExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

public class InterpreterPatternDemo {
    public static void main(String[] args) {
        Expression isMale = new TerminalExpression("Male");
        Expression isFemale = new TerminalExpression("Female");

        Expression isPerson = new OrExpression(isMale, isFemale);

        System.out.println("Is John a person? " + isPerson.interpret("John is a Male"));
    }
}

//Iterator Pattern迭代器模式
//提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示
interface Iterator {
    boolean hasNext();
    Object next();
}

interface Container {
    Iterator getIterator();
}

class NameRepository implements Container {
    public String names[] = {"Robert", "John", "Julie", "Lora"};

    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {
        int index;

        public boolean hasNext() {
            return index < names.length;
        }

        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for (Iterator iter = namesRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            System.out.println("Name: " + name);
        }
    }
}

//Mediator Pattern中介者模式
//用一个中介对象来封装一系列对象之间的交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互
class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(user.getName() + ": " + message);
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}

public class MediatorPatternDemo {
    public static void main(String[] args) {
        User john = new User("John");
        User jane = new User("Jane");

        john.sendMessage("Hello, Jane!");
        jane.sendMessage("Hi, John!");
    }
}


//Memento Pattern备忘录模式
//在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便以后恢复对象到以前的状态
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

class CareTaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}

public class MementoPatternDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}


//Observer Pattern观察者模式
//定义对象间的一种一对多的依赖关系，以便当一个对象的状态发生改变时，所有依赖于它的对象都会得到通知并自动更新
import java.util.ArrayList;
import java.util.List;

abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public```java
    void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}

class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()));
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}


//State Pattern状态模式
//允许对象在内部状态改变时改变它的行为，对象看起来好像修改了它的类
interface State {
    void doAction(Context context);
}

class StartState implements State {
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}

class StopState implements State {
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString() {
        return "Stop State";
    }
}

class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}

public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}

//Strategy Pattern策略模式
//定义一系列算法，把它们一个个封装起来，并且使它们可互相替换。本模式使得算法可独立于使用它的客户而变化
interface Strategy {
    int doOperation(int num1, int num2);
}

class OperationAdd implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationSubtract implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMultiply implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}

//Template Method Pattern模版方法模式
//定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //模板方法
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

class Cricket extends Game {
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

class Football extends Game {
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}


//Visitor Pattern访问者模式
//表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作
interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}

class Keyboard implements ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Monitor implements ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Mouse implements ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Computer implements ComputerPart {
    ComputerPart[] parts;

    public Computer() {
        parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
    }

    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}

interface ComputerPartVisitor {
    void visit(Computer computer);
    void visit(Mouse mouse);
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
}

class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }
}

public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}

