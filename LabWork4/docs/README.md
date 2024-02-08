# Лабораторная работа № 4: "Проектирование REST API"
## Документация по API
### 1. getSettingsQuantitySkillsByPosition [GET]
#### Описание ####
Получает настройки всех навыков с количественным измерением для должности 
#### Параметры ####
**idPosition**
- Тип данных: Long
- Описание: Идентификатор должности
#### Результат ####
Массив объектов со следующими полями:
- idConnection (Long): идентификатор настройки значений навыка для должности 
- idPosition (Long): идентификатор должности
- namePosition (String): название должности
- idSkill (Long): идентификатор навыка
- nameSkill (String): название навыка
- idTypeMeasurement (Long): идентификатор типа измерения навыка
- nameTypeMeasurement (Long): название типа измерения навыка
- minValue (Double): минимально допустимое значение навыка для должности
- idInterval (Long): идентификатор диапазона настройки значений навыка для должности 
- minValueInterval (int): минимальное значение для навыка при измерении
- maxValueInterval (int): максимальное значение для навыка при измерении

### 2. getSettingsQuantitySkillsByPositionSkill [GET]
#### Описание ####
Получает настройки указанного навыка с количественным измерением для должности 
#### Параметры ####
**idPosition**
- Тип данных: Long
- Описание: Идентификатор должности

**idSkill**
- Тип данных: Long
- Описание: Идентификатор навыка
#### Результат ####
Объект со следующими полями:
- idConnection (Long): идентификатор настройки значений навыка для должности 
- idPosition (Long): идентификатор должности
- namePosition (String): название должности
- idSkill (Long): идентификатор навыка
- nameSkill (String): название навыка
- idTypeMeasurement (Long): идентификатор типа измерения навыка
- nameTypeMeasurement (Long): название типа измерения навыка
- minValue (Double): минимально допустимое значение навыка для должности
- idInterval (Long): идентификатор диапазона настройки значений навыка для должности 
- minValueInterval (int): минимальное значение для навыка при измерении
- maxValueInterval (int): максимальное значение для навыка при измерении

### 3. saveQuantityMeasurementSkill [POST]
#### Описание ####
Добавляет настройки измерения навыка с количественным измерением для должности 
#### Параметры ####
**quantityValuesSkill**
- Тип данных: QuantityValuesSkill 
- Описание: Объект со свойствами 
	- idConnection: тип данных - Long, описание - идентификатор связи.
	- minValue: тип данных - Double, описание - минимальное значение для навыка в должности.
	- maxValue: тип данных - Double, описание - максимальное значение для навыка в должности.
#### Результат ####
Строка: Настройки навыка успешно добавлены.

### 4. saveQualityMeasurementSkill [POST]
#### Описание ####
Добавляет настройки указанного навыка с количественным измерением для должности 
#### Параметры ####
**qualityValuesSkill**
- Тип данных: QualityValuesSkill
- Описание: Объект со свойствами 
	- idConnection: тип данных - Long, описание - идентификатор связи.
	- value: тип данных - String, описание - значение для навыка в должности.
	- order: тип данных - Long, описание - порядковый номер значения в измерении навыка.
#### Результат ####
Строка: Настройки навыка успешно добавлены.

### 5. updateQuantityMeasurementSkill [PUT]
#### Описание ####
Редактирует настройки измерения навыка с количественным измерением для должности 
#### Параметры ####
**quantityValuesSkill**
- Тип данных: QuantityValuesSkill 
- Описание: Объект со свойствами
	- idInterval: тип данных - Long, описание - идентификатор интервала.
	- idConnection: тип данных - Long, описание - идентификатор связи.
	- minValue: тип данных - Double, описание - минимальное значение для навыка в должности.
	- maxValue: тип данных - Double, описание - максимальное значение для навыка в должности.
#### Результат ####
Строка: Настройки навыка успешно обновлены.

### 6. updateQualityMeasurementSkill [PUT]
#### Описание ####
Редактирует настройки указанного навыка с количественным измерением для должности 
#### Параметры ####
**qualityValuesSkill**
- Тип данных: QualityValuesSkill
- Описание: Объект со свойствами
	- idValue: тип данных - Long, описание - идентификатор значения. 
	- idConnection: тип данных - Long, описание - идентификатор связи.
	- value: тип данных - String, описание - значение для навыка в должности.
	- order: тип данных - Long, описание - порядковый номер значения в измерении навыка.
#### Результат ####
Строка: Настройки навыка успешно обновлены.

### 7. deleteQuantityMeasurementSkill [DELETE]
#### Описание ####
Удаляет настройки навыка с количественным измерением для должности 
#### Параметры ####
**idConnection**
- Тип данных: Long
- Описание: Идентификатор сявзи
#### Результат ####
Строка: Настройки навыка успешно удалены.

### 8. deleteQualityMeasurementSkill [DELETE]
#### Описание ####
Удаляет настройки навыка с количественным измерением для должности 
#### Параметры ####
**idConnection**
- Тип данных: Long
- Описание: Идентификатор связи
#### Результат ####
Строка: Настройки навыка успешно удалены.


## Тестирование API
### 1. getSettingsQuantitySkillsByPosition [GET]
#### Запрос и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/e3b598b3-d4ca-474a-9c0f-aa3294d2dca8)
#### Код автотестов
![image](https://github.com/nikrykov/PAPS/assets/121154680/c4175329-9cbf-4394-9794-33607974515b)

### 2. getSettingsQuantitySkillsByPositionSkill [GET]
#### Запрос и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/9a04545e-2d38-4abc-815b-05f4bb497ad8)
#### Код автотестов
![image](https://github.com/nikrykov/PAPS/assets/121154680/da51ecac-c169-4376-abbd-92429cd85adb)

### 3. saveQuantityMeasurementSkill [POST]
#### Запрос и тело запроса 
![image](https://github.com/nikrykov/PAPS/assets/121154680/d95f275a-8094-49c6-847c-f3e36c28ac90)
#### Код автотестов и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/3102de32-112a-4d4b-af77-39053c04163c)

### 4. saveQualityMeasurementSkill [POST]
#### Запрос и тело запроса
![image](https://github.com/nikrykov/PAPS/assets/121154680/33d7a879-70b8-469e-8a17-c92c7e6bfe46)
#### Код автотестов и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/18ad1813-2bc0-4c2f-a046-8e1c7c0214b4)

### 5. updateQuantityMeasurementSkill [PUT]
#### Запрос и тело запроса
![image](https://github.com/nikrykov/PAPS/assets/121154680/9c93d326-fa6e-4194-8fae-608b0f422da5)
#### Код автотестов и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/a414484e-a387-44c9-9179-d16d98bf9f24)

### 6. updateQualityMeasurementSkill [PUT]
#### Запрос и тело запроса
![image](https://github.com/nikrykov/PAPS/assets/121154680/65a6be94-9af4-42d5-bdf6-dc9e5f31d6a8)
#### Код автотестов и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/7a22fa91-7ee9-401e-8f18-9e1f8d97fa3b)

### 7. deleteQuantityMeasurementSkill [DELETE]
#### Запрос и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/26480787-26b9-4d5b-a087-bf7f38c4b06a)
#### Код автотестов
![image](https://github.com/nikrykov/PAPS/assets/121154680/911a413a-1b2a-4211-a53b-53149716d3c0)

### 8. deleteQualityMeasurementSkill [DELETE]
#### Запрос и тело ответа
![image](https://github.com/nikrykov/PAPS/assets/121154680/011d5574-fb6f-4039-9a81-e3c1c1d238f4)
#### Код автотестов
![image](https://github.com/nikrykov/PAPS/assets/121154680/4441248f-708c-4fc3-96ef-d81d495b33e2)
