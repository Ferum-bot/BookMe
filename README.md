## Структура проекта

Проект представляет из себя многомодульную структуру.
core - код для работы с БД, сетью, компонентами ОС; низкоуровневое


## Flow работы с проектом, YouTrack, Gitlab, правила MR, выпуск версии

YouTrack, правила оформления MR, условия для вмердживания, выпуск версии

Flow при работе с YouTrack + Gitlab:

1. Для начала работы необходимо изменить статус задачи из OPEN в IN DEVELOPMENT.
2. Перейти на ветку develop:
    ```sh
    git checkout develop
    ```
3. Форкнуть новую ветку, которую назвать по принципу <type: tech/feature/fix>/<task>_<description - короткое название того, для чего ветка>.
    ```sh
    git checkout -b feature/BM1_chat
    ```
4. Добавить свои изменения. Код всегда должен быть предварительно скомпилирован, запущен, и проверен на работоспособность на реальном устройстве.
5. Затрекать гитом через `git add .`
6. Закоммитить. В проекте введены строгие правила написания комментариев к коммитам: "`[<task>] <type: tech/feature/fix>: <description>`"
    ```sh
    git commit -m "[BM-1] feature: chat"
    ```
7. Перейти на develop, сделать `git pull`, вернуться на свою ветку, сребейзить её средствами Android Studio: нижний правый угол, выбрать develop -> "Rebase current onto selected".
8. После ребейза запушить через `git push` либо `git push --force` по ситуации (убедиться что стоим на своей ветке).
9. Оформить Merge Request. Назначить на @erkhabibullina.
10. Перетащить карточку в IN REVIEW. Затрекать время, потраченное на разработку.
11. Сообщить членам команды Android о новом MR, предоставив ссылку.
12. По результатам review MR должен получить минимум 2 approved маркера. Если на MR появляется комментарий (открытый тред), необходимо внести изменения и залить их с помощью force push в текущую ветку задачи. После необходимо ответить на коментарий "исправлено". Только автор коментария может зарезолвить тред.
15. Только когда треды зарезолвлены, аппрувы получены, можно сообщить Android команде о том, что мердж можно вливать.
