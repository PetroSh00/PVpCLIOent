# Forge 1.21.1 Custom Client (QoL) — безопасный гайд

Я не могу помогать с разработкой чит-клиента (KillAura, AutoClicker, обходы античита и т.п.).

Ниже — **полный практический шаблон** для Forge-based **кастомного клиентского билда** под 1.21.1,
который запускается как отдельная версия в лаунчере (`1.21.1-MyClient-QoL`) и использует Forge + Mixins,
но только для легитимных QoL-фич.

## 1) Возможно ли это на Forge 1.21.1 в принципе?
Да. Делается через:
1. Forge MDK проект (клиентский мод-код, UI, keybinds, события).
2. Сборку jar.
3. Создание отдельной записи в `versions/<custom_name>/` с собственным `<custom_name>.json` и `<custom_name>.jar`.
4. Наследование от forge-профиля в `inheritsFrom`.

### Ограничения vs Fabric
- На Forge тяжелее lifecycle и больше boilerplate.
- Mixin на Forge работает, но обычно нужен аккуратный Gradle-конфиг + refmap.
- Fabric проще для «тонких» клиентских инъекций, Forge удобнее если нужны forge-события/экосистема.

## 2) Структура проекта
См. текущую структуру:

- `build.gradle`
- `settings.gradle`
- `gradle.properties`
- `src/main/resources/META-INF/mods.toml`
- `src/main/resources/myclient.mixins.json`
- `src/main/java/com/myclient/MyClientMod.java`
- `src/main/java/com/myclient/core/*`
- `src/main/java/com/myclient/modules/*`

## 3) Что уже реализовано в шаблоне
- База модульной системы (`Module`, `Category`, `ModuleManager`).
- Регистрация клавиши Right Shift (ребинд делается в controls).
- Заготовка открытия меню на событии client tick.
- Два безопасных модуля-заглушки: `SprintAssist`, `BrightnessBoost`.

> Если нужно, можно расширить это в полноценный ClickGUI (категории, слайдеры, бинды),
> но без функций автоматизированного PvP/атаки.

## 4) Сборка
```bash
./gradlew build
```
Готовый jar обычно в `build/libs/`.

## 5) Как сделать отдельную версию для launcher (не просто mods/)

### Шаги
1. Установи Forge 1.21.1 в лаунчер обычным способом (чтобы появилась forge-версия).
2. Найди `%APPDATA%/.minecraft/versions/` (Windows) или `~/.minecraft/versions/`.
3. Создай папку, например:
   - `versions/1.21.1-MyClient-QoL/`
4. Скопируй туда:
   - `1.21.1-MyClient-QoL.jar` (обычно копия forge client jar с нужным именем)
   - `1.21.1-MyClient-QoL.json`
5. В JSON укажи `id`, `inheritsFrom` (forge версия), `mainClass/arguments` оставь от forge-базы.
6. В `libraries` добавь/обнови ссылку на твой собранный артефакт (или включи его в classpath через tweaker-цепочку forge-профиля).
7. Запусти лаунчер и выбери новую версию.

### Минимальный пример custom JSON (схематично)
```json
{
  "id": "1.21.1-MyClient-QoL",
  "inheritsFrom": "1.21.1-forge-52.1.10",
  "type": "release",
  "releaseTime": "2025-01-01T00:00:00+00:00",
  "time": "2025-01-01T00:00:00+00:00"
}
```

> Практически удобнее сначала скопировать forge json и менять только `id`/пути, чтобы не сломать аргументы запуска.

## 6) Как добавить легитимный GUI
- Создай `Screen` (например `ClientMenuScreen`).
- В `onClientTick` по `OPEN_MENU.consumeClick()` вызывай `Minecraft.getInstance().setScreen(...)`.
- Для настроек сделай `Setting<T>` + слайдеры (speed, distance и т.д.) только для non-cheat функционала.

## 7) Важно
Я не предоставляю инструкции/код для:
- AutoClicker
- KillAura / AimAssist
- обходов античита
- скрытных чит-механик

Могу помочь перевести этот шаблон в:
- легальный PvE/QoL клиент,
- accessibility-инструменты,
- performance/visual suite,
- или dev-client для тестирования своих модов.
