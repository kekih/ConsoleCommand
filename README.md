# ConsoleCommand

Простой Paper-плагин для Minecraft **26.3**, который позволяет выполнять команды от имени консоли через `/console`.

## Команда

```
/console <команда>
```

Пример:
```
/console say Привет от консоли!
/console op PlayerName
/console ban PlayerName Читы
```

Команда выполняется **от имени серверной консоли** (с полными правами).

## Права

| Право | Описание | По умолчанию |
|-------|----------|--------------|
| `console.use` | Разрешает использовать `/console` | `op` |

Дай право через LuckPerms или другой плагин:
```
lp user <ник> permission set console.use true
```

## Установка

1. Скачай готовый `.jar` из [Releases](https://github.com/kekih/ConsoleCommand/releases) (когда появится) **или** собери сам.
2. Положи в папку `plugins/`
3. Перезапусти сервер.

## Сборка

Требуется **Java 25**.

```bash
./gradlew build
```

Готовый плагин будет в `build/libs/ConsoleCommand-1.0.0.jar`

## Требования

- Paper / Purpur / Folia 26.3+
- Java 25+

## Автор

Сделано для удобства администрирования серверов.
