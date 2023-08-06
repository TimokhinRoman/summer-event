START TRANSACTION;

INSERT INTO Event (id, name, description, status) VALUES (1, '2023', 'Боги всех времён и народов', 'PENDING');

INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (1, 1, 'ACTION', 'Поджигай', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <span>с помощью выданного снаряжения получить огонь и зажечь бумагу.</span>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>дощечки, лук, трут.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>задача выполнена быстрее другой команды.</span>
</div>', null, 68, 216, 0, null, 'fire', 'Мой путь устлан пеплом - безжизненным, серым,
растущим день ото дня.
Не нужен ни воздух, ни сон и проблемы
не трогают вовсе меня.
Я в угли и дым превращаю гектары
забытой богами земли,
Элементалем они меня звали,
лишь только завидев огни.
И я здесь сожгу каждый чертовый метр
хоть плачь ты, хоть жалко скули
Пощады достойны подобные мне,
возьми и костер запали.
Я жду тебя, путник, за древом девяток
в безжизненной серой пыли
используя только веревку и палки
огонь для меня разбуди.');
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (2, 1, 'ACTION', 'Кидай быстрей', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>каждая команда встает в свой круг, фрисби отдается капитану;</li>
            <li>за отведенное время каждая команда должна пройти как можно большее кол-во полных кругов.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>фрисби.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>при выполнении задачи за отведенное время команда проходит больше кругов, чем команда-соперник.</span>
</div>', null, 130, 313, 0, null, 'wind', 'Пора оседлать вам стихию!
Сказал я ворвавшись в ваш круг.
И рядом с тобой ностальгию,
почувствовал остро, мой друг.
Упрямый, как дикий торнадо
сметал на своем я пути:
деревья, дома и машины
стремясь поскорее уйти.
Но здесь я хотел бы остаться
и нрав свой в узде удержать:
забрать-передать-взяться
и ветер попутный поймать.
Я Элементаль воздушный,
и я объявляю турнир
чем больше кругов ты накрутишь,
тем больший ты мой кумир.');
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (3, 1, 'ACTION', 'Дикая охота', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>в конкурсе участвуют два человека от одной команды № 1 и один человек от другой команды № 2;</li>
            <li>два человека от команды № 1 объединяются в кентавра, человек от команды № 2 - ловец;</li>
            <li>ловец должен с помощью лассо поймать кентавра.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>лассо, костюм кентавра.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>задача выполнена быстрее другой команды.</span>
</div>', null, 200, 540, 0, null, 'hunt', null);
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (4, 1, 'ACTION', 'Отгадай, откопай', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>найти клад;</li>
            <li>подсказка, где именно искать клад спрятана в криптекс;</li>
            <li>команда должна первой угадать название песни, зашифрованной задом-наперед и открыть криптекс;</li>
            <li>по одной попытке подряд у каждой команды;</li>
            <li>первой начинает команда, которая выбрала конкурс.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>лопата, отвёртка.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>быстрее всех нашел клад.</span>
</div>', null, 252, 165, 0, null, 'earth', 'Я хожу через скалы и землю
словно это живая вода,
простофилям с небес я не внемлю
моей мощи боялись всегда:
Эти плечи как горные цепи,
и с дубину размером кулак
и всегда я доподлинно знаю,
если где-то скрывается враг.
Я подобен холму или камню,
слышу каждый несущийся звук,
поднимаюсь пред ним как преграда
отражая его силой рук,
Я стихия, я сила природы
и древнее, чем старый грааль
имя Терра мне!  долгие годы
меня звали Элементаль.');
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (5, 1, 'ACTION', 'В яблочко', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>кинуть топор и воткнуть его в мишень;</li>
            <li>у каждой команды есть по 15 бросков;</li>
            <li>за каждое попадание команде начисляются очки: 50 - центр, 25 - внутренний круг и 10 - внешний круг.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>мишень, топор.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>команда, которая набрала больше всего очков.</span>
</div>', null, 292, 516, 0, null, 'war', null);
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (6, 1, 'ACTION', 'Освежись', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <span>водная перестрелка из пушек. Команды перестреливаются цветной водой, стараясь уворачиваться от выстрелов соперников. Игра продолжается до тех пор, пока не закончится цветная вода.</span>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>ёмкость с цветной водой, водная пушка, белая накидка.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>команда, которая меньше всего была разукрашена.</span>
</div>', null, 255, 603, 0, null, 'water', 'Я - тихая радость Шалассы
и мой безмятежен покой,
я не принимаю отказы -
сегодня вы вступите в бой.
Готовьте свои "арбалеты"
и стрелы из яркой воды,
раскрасьте своих оппонентов
цветные оставив следы.
Зовут меня Элементалем -
наездником дикой волны,
и раз уж мы здесь повстречались
погрузимся в море игры!');
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (7, 1, 'ACTION', 'Смотри, что пьёшь!', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <span>угадать, что за жидкость в пронумерованных стаканах и написать в своем ответном листе вариант.</span>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>непрозрачные стаканы с жидкостями, трубочки, бланки для ответов.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>команда, которая больше всего дала правильных ответов.</span>
</div>', null, 156, 240, 0, null, 'wine', null);
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (8, 1, 'ACTION', 'Это шок', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>по одному участнику от команды;</li>
            <li>на участника одевается браслет;</li>
            <li>в ходе конкурса задаются вопросы по божественной теме;</li>
            <li>задается вопрос, если участник знает ответ, он должен поднять руку. Участник озвучивает ответ, если он неправильный - его бьет током. Далее возможность ответить получает другой участник. Если отвечает неправильно - его бьет током. На каждый вопрос по две попытки на ответ. Если одним из участников дан правильный ответ, то второй не получает баллов за ответ.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>шокер ошейник.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>команда, которая дала больше всего правильных ответов.</span>
</div>', null, 100, 400, 0, null, 'shock', null);
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (9, 1, 'ACTION', 'Остренькое', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <span>съесть лапшу быстрее другой команды.</span>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>пачка лапши.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>побеждает команда, лапша которой съедена быстрее всего.</span>
</div>', null, 180, 100, 0, 4, 'trick', null);
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (10, 1, 'ACTION', 'Лучше не шуми', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>по одному добровольцу от команды;</li>
            <li>добровольцу завязывают глаза и дают в руки веник;</li>
            <li>члены команды соперника встают в круг вокруг противника с завязанными глазами;</li>
            <li>у каждого из них на одну ногу привязано по пищащей курице;</li>
            <li>игрок с завязанными глазами должен салить игроков команды соперника.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>пищащие курицы, веник, повязка на глаза.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>побеждает команда, чей доброволец засалил как можно больше участников команды противника.</span>
</div>', null, 270, 200, 0, null, 'music', null);
INSERT INTO Task (id, eventId, type, name, description, answer, mapX, mapY, completed, parentTaskId, image, preview) VALUES (11, 1, 'ACTION', 'Кто тут красавчик', '<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>быть в клевом костюме;</li>
            <li>эффектно пройтись под музыку по импровизированному подиуму.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>костюм, музыка.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>побеждает команда, где больше всего людей в костюмах, а также где больше участников прошлось по подиуму.</span>
</div>', null, 220, 300, 0, null, 'beauty', null);

COMMIT;