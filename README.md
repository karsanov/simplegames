Simplegames -- небольшое веб приложение, в котором задействован стек Java EE технологий, в том числе использованы фреймворки Spring и Hibernate.

Проект небольшого размера и многие вещи в нём представлены весьма символически. Так, например, работа с базой данных сведена к созданию в оперативной памяти базы hsql с одной единственной таблицей и тремя записями в ней. Тем не менее, этого достаточно, чтобы появилась возможность задействовать в проекте Hibernate, пусть и всего с одним репозиторием.

Таким образом данный проект не про глубокое понимание какой-либо технологии, но про применение базового стека.

Конфигурация веб-приложения сделана с помощью java кода, в web.xml только замаплен springSecurityFilterChain на соответсвующий класс.

Во время инициализации Hibernate в оперативной памяти создаётся база hsql, которая инициализируется двумя скриптами. В последствии работа с базой происходит только через Hibernate.

База данных используется для регистрации в системе пользователей. Для простоты в базе уже зарегистрированы три пользователя, их пароли (1234) зашифрованы в bCrypt без применения спайса.

В системе работает бин UserPollService, который отслеживает список залогиненных пользователей и не позволяет дважды входить в систему.

В приложении доступна одна игра: Black Jack. Сама игра, колода карт и собственно карты смоделированы в простом Java core и к ним, по существу, можно приспособить любой интерфейс, не только веб. В игре не ведётся счёт и не сохраняются результаты.

Компоненты игры сами создают объекты друг друга с помощью new, но при этом каждая игра существует в рамках одной веб-сессии.

Все прочие элементы системы связаны друг с другом посредством интерфейсов и аннотаций @Autowired, реализуя таким образом принцип "слабого связывания" (loose coupling).

Данные игры выводятся на jsp страницы. Для адкватного представления элементов игры используется css.