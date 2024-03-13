# Лабораторная работа № 6: "Использование шаблонов проектирования".

## Порождающие шаблоны
Строитель (Builder) - паттерн строитель используется для поэтапного создания сложных объектов. В этом примере, использовался паттерн строитель для создания объекта Skill по шагам, устанавливая шкалу измерения и минимальный уровень навыка.
Фабричный метод (Factory Method) - используется для создания объектов, который скрывает детали создания от клиентского кода. В этом случае был использован фабричный метод SkillFactory.createSkill для создания экземпляра класса Skill.
Одиночка (Singleton) - паттерн одиночка используется для создания только одного экземпляра класса. В данном примере был использован паттерн одиночка для класса SkillFactory, чтобы гарантировать, что есть только одна фабрика для создания навыков.
```
// Код интерфейса для работы с навыками
public interface Skill {
    void setScaleType(ScaleType scaleType);
    void setMinimumLevel(int minLevel);
}
```
```
// Реализация интерфейса Skill
public class SkillImpl implements Skill {
    private ScaleType scaleType;
    private int minLevel;

    @Override
    public void setScaleType(ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    @Override
    public void setMinimumLevel(int minLevel) {
        this.minLevel = minLevel;
    }
}
```
```
// Класс фабрики для создания навыков
public class SkillFactory {
    private static SkillFactory instance;

    // Приватный конструктор для применения паттерна одиночка
    private SkillFactory() {
        // Логика инициализации фабрики
    }

    // Метод для получения экземпляра фабрики
    public static SkillFactory getInstance() {
        if (instance == null) {
            instance = new SkillFactory();
        }
        return instance;
    }

    // Метод для создания навыка
    public Skill createSkill() {
        // Логика создания и настройки навыка
        Skill skill = new SkillImpl();

        // Логика установки шкалы измерения и минимального уровня навыка

        return skill;
    }
}
```

```
// Класс должности
@Data
public class Position {
    private String positionName;
    private Skill skill;
}
```

```
// Пример использования
public class Main {
    public static void main(String[] args) {
        // Создание экземпляра фабрики
        SkillFactory skillFactory = SkillFactory.getInstance();

        // Создание навыка для должности
        Skill skill = skillFactory.createSkill();

        // Создание должности и присвоение навыка
        Position position = new Position();
        position.setSkill(skill);
    }
}
```
![image](https://github.com/nikrykov/PAPS/assets/121154680/58e3d805-f967-40da-82c3-5361afcabf4f)

## Структурные шаблоны
В данном примере были учтены следующие принципы при реализации:

- Адаптер: класс BinaryScale адаптирует LinearScale для измерения навыков по бинарной шкале.
- Компоновщик: класс JobPosition представляет должность, которая может включать в себя несколько навыков.
- Декоратор: нет использования декоратора в данном примере(дополнить).
- Фасад: класс SkillManagementServiceImpl является фасадом, предоставляющим простой интерфейс для работы с навыками и должностями.
В этом примере создается новый класс-декоратор TaskRatingSkillDecorator, который оборачивает базовый навык и добавляет функциональность измерения навыка на основе оценок задач сотрудника. Сервис TaskRatingService используется для того, чтобы получить оценку задач для указанного сотрудника, и затем преобразовать эту оценку в уровень навыка.
```
// Интерфейс для шкалы измерения навыка
public interface Scale {
    int measureSkill(int level);
}

// Классы для различных типов шкал измерения
public class LinearScale implements Scale {
    @Override
    public int measureSkill(int level) {
        return level;
    }
}

public class BinaryScale implements Scale {
    @Override
    public int measureSkill(int level) {
        return level >= 5 ? 1 : 0;
    }
}

// Интерфейс для навыка
public interface Skill {
    String getName();
    void setName(String name);
    void setScale(Scale scale);
    int getRequiredLevel();
    void setRequiredLevel(int requiredLevel);
    int measureSkillLevel(int employeeId);
}

// Классы для навыка
public class BasicSkill implements Skill {
    private String name;
    private Scale scale;
    private int requiredLevel;

    // Конструкторы, геттеры и сеттеры

    @Override
    public int measureSkillLevel(int employeeId) {
        // Логика измерения уровня навыка для сотрудника с employeeId
        return 0;
    }
}

public class AdvancedSkill implements Skill {
    // Аналогично BasicSkill
}

// Интерфейс для должности
public interface Position {
    void addSkill(Skill skill);
    void setSkillScale(Skill skill, Scale scale);
    void setSkillRequiredLevel(Skill skill, int requiredLevel);
    int getRequiredLevelForSkill(Skill skill);
}

// Класс для должности
public class JobPosition implements Position {
    private Map<Skill, Scale> skills = new HashMap<>();
    private Map<Skill, Integer> requiredLevels = new HashMap<>();

    @Override
    public void addSkill(Skill skill) {
        skills.put(skill, null);
    }

    @Override
    public void setSkillScale(Skill skill, Scale scale) {
        skills.put(skill, scale);
    }

    @Override
    public void setSkillRequiredLevel(Skill skill, int requiredLevel) {
        requiredLevels.put(skill, requiredLevel);
    }

    @Override
    public int getRequiredLevelForSkill(Skill skill) {
        return requiredLevels.get(skill);
    }
}

// Интерфейс сервиса для управления навыками и должностями
public interface SkillManagementService {
    void addSkillToPosition(String positionId, String skillName);
    void setSkillScaleForPosition(String positionId, String skillName, Scale scale);
    void setSkillRequiredLevelForPosition(String positionId, String skillName, int requiredLevel);
    int getRequiredLevelForSkillInPosition(String positionId, String skillName);
}

// Реализация обертки сервиса управления навыками и должностями
public class SkillManagementServiceImpl implements SkillManagementService {
    private Map<String, Position> positions = new HashMap<>();

    @Override
    public void addSkillToPosition(String positionId, String skillName) {
        Position position = positions.get(positionId);
        if (position != null) {
            Skill skill = new BasicSkill();
            skill.setName(skillName);
            position.addSkill(skill);
        }
    }

    @Override
    public void setSkillScaleForPosition(String positionId, String skillName, Scale scale) {
        Position position = positions.get(positionId);
        if (position != null) {
            Skill skill = getSkillByName(position, skillName);
            if (skill != null) {
                skill.setScale(scale);
            }
        }
    }

    @Override
    public void setSkillRequiredLevelForPosition(String positionId, String skillName, int requiredLevel) {
        Position position = positions.get(positionId);
        if (position != null) {

            Skill skill = getSkillByName(position, skillName);
            if (skill != null) {
                skill.setRequiredLevel(requiredLevel);
            }
        }
    }

    @Override
    public int getRequiredLevelForSkillInPosition(String positionId, String skillName) {
        Position position = positions.get(positionId);
        if (position != null) {
            Skill skill = getSkillByName(position, skillName);
            if (skill != null) {
                return position.getRequiredLevelForSkill(skill);
            }
        }
        return 0;
    }

    private Skill getSkillByName(Position position, String skillName) {
        for (Skill skill : position.getSkills()) {
            if (skill.getName().equals(skillName)) {
                return skill;
            }
        }
        return null;
    }
}

// Декоратор для измерения навыка на основе оценок задач сотрудника
public class TaskRatingSkillDecorator implements Skill {
    private Skill skill;
    private TaskRatingService taskRatingService;

    public TaskRatingSkillDecorator(Skill skill, TaskRatingService taskRatingService) {
        this.skill = skill;
        this.taskRatingService = taskRatingService;
    }

    @Override
    public String getName() {
        return skill.getName();
    }

    @Override
    public void setName(String name) {
        skill.setName(name);
    }

    @Override
    public void setScale(Scale scale) {
        skill.setScale(scale);
    }

    @Override
    public int getRequiredLevel() {
        return skill.getRequiredLevel();
    }

    @Override
    public void setRequiredLevel(int requiredLevel) {
        skill.setRequiredLevel(requiredLevel);
    }

    @Override
    public int measureSkillLevel(int employeeId) {
        int taskRating = taskRatingService.getRatingForEmployee(employeeId);
        int automaticLevel = // Логика преобразования оценки задач в уровень навыка
        int manualLevel = skill.measureSkillLevel(employeeId);
        return Math.max(automaticLevel, manualLevel);
    }
}
```
![image](https://github.com/nikrykov/PAPS/assets/121154680/1cb156e6-fafb-4beb-bb97-85bd9eb29cd9)

## Поведенческие шаблоны
В этом случае были учтены следующие принципы при реализации:
- Цепочка обязанностей: используется паттерн для выбора обработчика запроса добавления навыка в должность в зависимости от имени навыка.
- Команда: используется паттерн для представления операций (установка типа шкалы измерения навыка) в виде объекта Command, который можно хранить, передавать и выполнять в удобное время.
- Наблюдатель: используется паттерн для уведомления об изменении уровня навыка с помощью интерфейса SkillLevelObserver и метода update().
- Исполнитель: представлен классом SkillEvaluator для оценки и изменения уровня навыка с использованием наблюдателей.
- Шаблонный метод: нет использования шаблонного метода в данном примере.
```
// Интерфейс для обработчика запроса добавления навыка
public interface SkillAddHandler {
    void setNext(SkillAddHandler handler);
    void handleRequest(String positionId, String skillName);
}

// Базовый класс для обработчиков запроса добавления навыка
public abstract class AbstractSkillAddHandler implements SkillAddHandler {
    private SkillAddHandler nextHandler;

    @Override
    public void setNext(SkillAddHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(String positionId, String skillName) {
        if (canHandle(skillName)) {
            addSkillToPosition(positionId, skillName);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(positionId, skillName);
        } else {
            throw new RuntimeException("Cannot add skill");
        }
    }

    protected abstract boolean canHandle(String skillName);
    protected abstract void addSkillToPosition(String positionId, String skillName);
}

// Классы-обработчики для конкретных навыков
public class JavaSkillAddHandler extends AbstractSkillAddHandler {
    @Override
    protected boolean canHandle(String skillName) {
        return skillName.equals("java");
    }

    @Override
    protected void addSkillToPosition(String positionId, String skillName) {
        // Логика добавления навыка Java для должности с positionId
    }
}

public class PythonSkillAddHandler extends AbstractSkillAddHandler {
    @Override
    protected boolean canHandle(String skillName) {
        return skillName.equals("python");
    }

    @Override
    protected void addSkillToPosition(String positionId, String skillName) {
        // Логика добавления навыка Python для должности с positionId
    }
}

// Класс для применения паттерна Цепочка Обязанностей
public class SkillAddHandlerChain {
    private SkillAddHandler handlerChain;

    // Конструктор, в котором создается цепочка обработчиков
    public SkillAddHandlerChain() {
        SkillAddHandler javaHandler = new JavaSkillAddHandler();
        SkillAddHandler pythonHandler = new PythonSkillAddHandler();

        javaHandler.setNext(pythonHandler); // Установка следующего обработчика

        handlerChain = javaHandler;
    }

    public void handleRequest(String positionId, String skillName) {
        handlerChain.handleRequest(positionId, skillName);
    }
}

// Команда для установки типа шкалы измерения навыка
public class SetSkillScaleCommand implements Command {
    private Skill skill;
    private Scale scale;

    public SetSkillScaleCommand(Skill skill, Scale scale) {
        this.skill = skill;
        this.scale = scale;
    }

    @Override
    public void execute() {
        skill.setScale(scale);
    }
}

// Класс Invoker для выполнения команд
public class SkillCommandInvoker {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
        commands.clear();
    }
}

// Интерфейс Наблюдатель для наблюдения изменений в уровне навыка
public interface SkillLevelObserver {
    void update(int skillLevel);
}

// Класс Исполнитель для оценивания уровня навыка
public class SkillEvaluator {
    private List<SkillLevelObserver> observers = new ArrayList<>();

    public void addObserver(SkillLevelObserver observer) {

        observers.add(observer);
    }

    public void evaluateSkillLevel(int skillLevel) {
        for (SkillLevelObserver observer : observers) {
            observer.update(skillLevel);
        }
    }
}

// Конкретный наблюдатель для реагирования на изменение уровня навыка
public class SkillLevelObserverImpl implements SkillLevelObserver {
    @Override
    public void update(int skillLevel) {
        // Логика реакции на изменение уровня навыка
    }
}

// Пример использования паттернов в сервисном классе
public class SkillManagementService {
    private SkillAddHandlerChain skillAddHandlerChain;
    private SkillCommandInvoker commandInvoker;
    private SkillEvaluator skillEvaluator;

    public SkillManagementService(SkillAddHandlerChain skillAddHandlerChain, SkillCommandInvoker commandInvoker, SkillEvaluator skillEvaluator) {
        this.skillAddHandlerChain = skillAddHandlerChain;
        this.commandInvoker = commandInvoker;
        this.skillEvaluator = skillEvaluator;
    }

    public void addSkillToPosition(String positionId, String skillName) {
        skillAddHandlerChain.handleRequest(positionId, skillName);
    }

    public void setSkillScale(Skill skill, Scale scale) {
        commandInvoker.addCommand(new SetSkillScaleCommand(skill, scale));
        commandInvoker.executeCommands();
    }

    public void evaluateSkillLevel(int skillLevel) {
        skillEvaluator.evaluateSkillLevel(skillLevel);
    }
}
```
![image](https://github.com/nikrykov/PAPS/assets/121154680/58a6b96d-c6a6-4d38-9312-b53a24e2738d)

