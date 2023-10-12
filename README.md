# Financial Calculator

Приветствую! Этот проект представляет собой простой финансовый калькулятор, разработанный на Java с использованием Java Swing для графического интерфейса. Калькулятор поддерживает основные математические операции, такие как сложение и вычитание.

## Запуск приложения

1. **Подготовка к запуску**:

   Прежде чем запустить приложение, убедитесь, что у вас установлена Java Development Kit (JDK). Для компиляции проекта выполните следующую команду из корневой директории проекта:

   ```bash
   java -version
   ```

    Если версия JDK не соответствует требуемой, убедитесь, что у вас установлена версия JDK 21 или выше.

2. **Исполняемый JAR файл**:

   Для запуска приложения, найдите исполняемый JAR файл. Выполните следующие команды из корневой директории проекта:

   2.1. **Перейдите в папку `bin` и просмотрите содержимое**:

      ```bash
      cd bin
      dir
      ```

   Теперь вы должны видеть файл `FinancialCalculator.jar`. Если по какой-то причине этого файла нет, перейдите к шагу 2.2.

   2.2. **Компиляция проекта**:

   Для компиляции проекта выполните следующую команду из корневой директории проекта:

      ```bash
      javac -d bin src/**/*.java
      ```

   2.3. **Создание исполняемого JAR файла**:

   Для создания исполняемого JAR файла, выполните следующую команду:

      ```bash
      jar cfm FinancialCalculator.jar manifest.mf -C bin .
      ```

   Теперь у вас должен быть файл `FinancialCalculator.jar`.

3. **Запуск приложения**:

   Вы можете запустить приложение, используя следующую команду:

   ```bash
   java -jar FinancialCalculator.jar
   ```

   После этого приложение откроется и будет готово к использованию.

## Ограничение на ввод и результат

Калькулятор ограничивает вводимые числа и результат вычислений в диапазоне от -1,000,000,000,000.000000 до +1,000,000,000,000.000000. Если итоговый результат превышает указанный диапазон чисел, вы увидите сообщение о переполнении.

## Автор

* **Шушкевич Дмитрий**
