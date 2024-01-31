### 1. Диаграмма компонентов для варианта использования "Настройка навыка для должности"
![image](https://github.com/nikrykov/PAPS/blob/LabWork3/LabWork3/components-diag.PNG?raw=true)

### 2. Диаграмма последовательностей для варианта использования "Настройка навыка для должности"
![image](https://github.com/nikrykov/PAPS/blob/LabWork3/LabWork3/sequences-diag.PNG?raw=true)

### 3. Модель БД в виде диаграммы классов
Предполагается, что Навыки, Должности и Типы измерений навыков это справочники. В этой работе планируется работать с 2 типами измерений - количественным и качественным. Также предполагается, что навыки для разных должностей
могут измеряться по-разному. Отношение "Диапазон значений измерения навыка" относится к навыкам которые измеряются как количественные. Им задается диапазон возможных значений из чисел.
Отношение "Значения измерения навыка" относится к навыкам, которые измеряются как качественный. Здесь значениями могут выступать строки. Например: "хорошо", "отлично".
![image](https://github.com/nikrykov/PAPS/blob/LabWork3/LabWork3/class-diag.PNG?raw=true)

### 4. Описание использованных принципов разработки
#### 4.1 Клиентское приложение.
1. Принцип KISS (Keep It Simple, Stupid):
   - Код приложения на React.js распределен по компонентам, что делает его более понятным, модульным и легко поддерживаемым.
   - Использование хуков useState и useEffect позволяет более простым способом управлять состоянием компонента.
```jsx
const [positions, setPositions] = useState([]);
const [skillTypes, setSkillTypes] = useState([]);
``` 
2. Принцип YAGNI (You Aren't Gonna Need It):
   - Приложение разрабатывается только с необходимыми элементами - поле для навыка с вводом, поле для должности с выбором и поле для типа навыка с выбором.
   - Форма для каждого типа навыка отображается только после выбора соответствующего типа, и неотображаемые элементы не создаются и не использовуются.
```jsx
      {selectedSkillType === "quantity" && <QuantityTypeForm/>}
      {selectedSkillType === "qualitative" && <QualityTypeForm/>}
```
3. Принцип DRY (Don't Repeat Yourself):
   - Запросы к серверу для получения списка должностей и типов навыков вынесены в отдельные функции useEffect, что позволяет избежать повторения кода.
   - Создана отдельная функция handleSkillTypeChange, чтобы избежать дублирования кода при обработке изменений в поле типа навыка.
```jsx
// Запрос списка должностей и типов навыков с сервера
  useEffect(() => {
    axios.get("/api/positions").then((response) => {
      setPositions(response.data);
    });

    axios.get("/api/skilltypes").then((response) => {
      setSkillTypes(response.data);
    });
  }, []);

  ```
4. Принцип SOLID:
   - Принцип S (Single Responsibility) - Классы и функции выполняют только одну задачу. Компоненты React делятся на отдельные функциональности, обеспечивая логическую разделенность.
   - Принцип O (Open/Closed) - Код легко расширяется с добавлением новых типов навыков без изменения существующего кода. Компонент формы адаптируется под разные типы навыков.
   - Принцип L (Liskov Substitution) - Компоненты принимают и возвращают значения в соответствии с ожидаемыми типами данных.
   - Принцип I (Interface Segregation) - Компоненты имеют четко определенные интерфейсы, что позволяет разделить ответственность и избежать монолитного кода.
   - Принцип D (Dependency Inversion) - Компоненты зависят от абстракций, а не от конкретной реализации. Компоненты React разделены на компоненты и хуки, что обеспечивает высокую гибкость и возможность повторного использования.
#### 4.2 Серверное приложение  
1. Принцип KISS (Keep It Simple, Stupid):
- Контроллер SkillController реализует только необходимые методы для обработки запросов и передачи данных. Он отвечает за обработку запросов списка должностей и типов навыков, а также сохранение данных формы.
2. Принцип YAGNI (You Aren't Gonna Need It):
- Серверное приложение реализует только необходимый функционал для обработки запросов и передачи данных. Нет дополнительных функций, которые не используются в клиентском приложении.
3. Принцип DRY (Don't Repeat Yourself):
- Созданы репозитории PositionRepository и SkillTypeRepository, куда вынесена общая логика доступа к данным списка должностей и типов навыков. Таким образом, избегается дублирование кода для доступа к базе данных.
4. Принцип SOLID:
- Принцип S (Single Responsibility) - Каждый класс имеет свою структуру и отвечает за определенную функциональность. Контроллер отвечает за обработку запросов от клиента, сервисы - за бизнес-логику, репозитории - за доступ к данным.
- Принцип O (Open/Closed) - Классы легко расширяются новыми методами или функциональностью, не изменяя существующий код. Новые методы можно добавить в сервисы или контроллеры без изменения кода контроллера.
- Принцип L (Liskov Substitution) - Классы и методы являются заменяемыми друг с другом, что позволяет использовать интерфейсы и абстракции и отделять зависимости от конкретных реализаций.
- Принцип I (Interface Segregation) - Классы имеют четко определенные интерфейсы, что позволяет разделить ответственность и избежать монолитного кода.
- Принцип D (Dependency Inversion) - Классы зависят от абстракций, а не от конкретных реализаций. Контроллер зависит от интерфейсов репозиториев, что позволяет избежать зависимости от конкретного типа репозитория.
```java
@RestController
@RequestMapping("/api")
public class SkillController {
    private final PositionRepository positionRepository;
    private final SkillTypeRepository skillTypeRepository;

    public SkillController(PositionRepository positionRepository, SkillTypeRepository skillTypeRepository) {
        this.positionRepository = positionRepository;
        this.skillTypeRepository = skillTypeRepository;
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @GetMapping("/skilltypes")
    public List<SkillType> getAllSkillTypes() {
        return skillTypeRepository.findAll();
    }

    @PostMapping("/formdata")
    public void saveFormData(@RequestBody FormData formData) {
        // Обработка и сохранение данных формы в базе данных
    }
}
```

```java
@Service
public class SkillsSettingsServiceImpl implements SkillsSettingsService {
  private final PositionRepository positionRepository;
  private final SkillTypeRepository skillTypeRepository;

  public SkillsSettingsServiceImpl(PositionRepository positionRepository, SkillTypeRepository skillTypeRepository) {
    this.positionRepository = positionRepository;
    this.skillTypeRepository = skillTypeRepository;
  }

  @Override
  public List<Position> getAllPositions() {
    return positionRepository.getAllPositions();
  }

  @Override
  public List<SkillType> getAllSkillTypes() {
    return skillTypeRepository.getAllSkillTypes();
  }

  @Override
  public void saveSkillSettings(String skill, String position, String skillType, String minValue, String maxValue, List<String> qualitativeValues) {
    // Логика сохранения настроек навыков сотрудников
  }
}
```

```java
@Repository
public class PositionRepositoryImpl implements PositionRepository {
  private final JdbcTemplate jdbcTemplate;

  public PositionRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Position> getAllPositions() {
    String sql = "SELECT * FROM positions";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Position.class));
  }

  @Override
  public void insertPosition(Position position) {
    String sql = "INSERT INTO positions (name) VALUES (?)";
    jdbcTemplate.update(sql, position.getName());
  }
}

@Repository
public class SkillTypeRepositoryImpl implements Skill
```

### 5. Дополнительные принципы разработки
#### 5.1 BDUF. Big design up front (масштабное проектирование прежде всего):
Этот принцип разработки предлагает создать подробный план и дизайн проекта до начала его реализации. В случае разрабатываемого клиент-серверного приложения с использованием React.js и Spring Boot, подход BDUF может быть нецелесообразен. Приложение разрабатывается в маленьких итерациях, с учетом Agile-принципов и обратной связи от клиента. Вместо того, чтобы вкладывать массу времени в начальное проектирование, предпочтительнее разработать MVP для демонстрации основной функциональности.
#### 5.2 SoC. Separation of concerns (принцип разделения ответственности):
Принцип разделения ответственности, или SoC, может быть использован для разрабатываемого клиент-серверного приложения. React.js и Spring Boot позволяют разделить обязанности между клиентским и серверным кодом. React.js отвечает за отображение пользовательского интерфейса и обработку пользовательских взаимодействий, в то время как Spring Boot отвечает за обработку запросов, выполнение бизнес-логики и взаимодействие с базой данных. Такое разделение позволяет создавать более модульный, понятный и поддерживаемый код.
#### 5.3 MVP. Minimum viable product (минимально жизнеспособный продукт):
Принцип MVP заключается в создании минимально жизнеспособного продукта, который содержит минимальный набор функций, достаточный для загрузки и запуска и обеспечения базовой ценности для пользователя. В разрабатываемом клиент-серверном приложении можно начать с основных функций, например, ввод навыков, выбор должности и типа навыка. Затем можно поэтапно расширять функциональность, итеративно создавая новые функции и улучшая существующие.
#### 5.4 PoC. Proof of concept (доказательство концепции):
Принцип PoC предлагает создать доказательство концепции для проверки технической или функциональной целесообразности разрабатываемого приложения. В случае клиент-серверного приложения, PoC может быть применен для проверки соединения между клиентской и серверной частями приложения, а также для демонстрации основной функциональности. Это может включать в себя прототипирование интерфейса, проверку запросов к серверу и взаимодействие с базой данных. PoC позволяет убедиться, что выбранный стек технологий подходит для требований проекта и предлагает ожидаемую производительность.

