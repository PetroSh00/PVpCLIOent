# Быстрая установка в одну папку (как просили)

Готовая папка версии уже подготовлена:
- `client-mod/1.21.1-client-mod/`

## Что сделать
1. Собери проект (`gradle build` или `./gradlew build`, если добавишь wrapper).
2. Возьми jar из `build/libs/`.
3. Положи его в `client-mod/1.21.1-client-mod/` и назови `1.21.1-client-mod.jar`.
4. Скопируй папку `client-mod/1.21.1-client-mod/` целиком в:
   - Windows: `%APPDATA%/.minecraft/versions/`
   - Linux: `~/.minecraft/versions/`
5. В лаунчере выбери версию `1.21.1-client-mod`.

## Важно
- Базовая Forge-версия `1.21.1-forge-52.1.10` должна быть уже установлена в лаунчере,
  потому что custom json использует `inheritsFrom`.
