# План по JDBC

1. Рассматриваем подключение к базе данных (Connection)
2. CRUD (Statements)
   1. Простой запрос на создание таблицы
   2. Запрос на вставку данных в таблицу
   3. Запрос на получение данных из таблицы (ResultSet)
   4. Запрос на обновление данных в таблице
   5. Запрос на удаление данных из таблицы
3. Пул подключений (DataSource)
4. Транзакции
   1. ACID
   2. Уровни изоляции
   

Foobar is a Python library for dealing with word pluralization.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage

```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.