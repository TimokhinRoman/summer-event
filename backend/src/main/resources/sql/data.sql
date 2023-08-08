START TRANSACTION;

INSERT INTO Event (id, name, description, status) VALUES (1, '2023', 'Боги всех времён и народов', 'PENDING');

INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (1, 1, 'ACTION', 'Поджигай', 222, 171, '<div class="my-3">
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
</div>', null, 0, null, 'fire', '
<div class="poem">
    <p>
    Мой путь устлан пеплом - безжизненным, серым,<br>
    растущим день ото дня.<br>
    Не нужен ни воздух, ни сон и проблемы<br>
    не трогают вовсе меня.<br>
    </p>
    <p>
    Я в угли и дым превращаю гектары<br>
    забытой богами земли,<br>
    Элементалем они меня звали, <br>
    лишь только завидев огни.<br>
    </p>
    <p>
    И я здесь сожгу каждый чертовый метр<br>
    хоть плачь ты, хоть жалко скули.<br>
    Пощады достойны подобные мне,<br>
    возьми и костер запали. <br>
    </p>
    <p>
    Я жду тебя, путник, за древом девяток<br>
    в безжизненной серой пыли.<br>
    Используя только веревку и палки<br>
    огонь для меня разбуди.<br>
    </p>
</div>');
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (2, 1, 'ACTION', 'Кидай быстрей', 484, 380, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>каждая команда встает выстраивается в собственный круг;</li>
            <li>круги всех команд должны быть одинакового размера;</li>
            <li>между членами команд должно быть не меньше 3 метров (точный промежуток определят организаторы на месте);</li>
            <li>фрисби отдается капитану, капитан делает первый бросок.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>за отведенное время каждая команда должна пройти как можно большее количество полных кругов.</li>
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
</div>', null, 0, null, 'wind', '<div class="poem">
    <p>
        Пора оседлать вам стихию!<br>
        Сказал я ворвавшись в ваш круг.<br>
        И рядом с тобой ностальгию,<br>
        почувствовал остро, мой друг.<br>
    </p>
    <p>
        Упрямый, как дикий торнадо<br>
        сметал на своем я пути:<br>
        деревья, дома и машины<br>
        стремясь поскорее уйти.<br>
    </p>
    <p>
        Но здесь я хотел бы остаться<br>
        и нрав свой в узде удержать:<br>
        забрать-передать-взяться<br>
        и ветер попутный поймать.<br>
    </p>
    <p>
        Я Элементаль воздушный,<br>
        и я объявляю турнир<br>
        чем больше кругов ты накрутишь,<br>
        тем больший ты мой кумир.<br>
    </p>
</div>');
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (3, 1, 'ACTION', 'Дикая охота', 193, 215, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>в конкурсе участвуют кентавр и ловец;</li>
            <li>два человека от одной команды объединяются в кентавра, человек от другой команды является ловцом;</li>
            <li>в раунде принимают участие одновременно только две команды.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
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
</div>', null, 0, null, 'hunt', null);
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (4, 1, 'ACTION', 'Отгадай, откопай', 121, 467, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>подсказка, где именно искать клад спрятана в криптекс;</li>
            <li>команда должна первой угадать кодовое слово из композиции, зашифрованной задом-наперед, и открыть криптекс;</li>
            <li>команда, которая считает, что угадала слово, подходит к организаторам и просит криптекс;</li>
            <li>по одной попытке подряд у каждой команды;</li>
            <li>команда, которая открыла криптекс, получает подсказку и 30 секунд форы на поиск;</li>
            <li>через 30 секунд остальным командам отдается подсказка;</li>
            <li>снаряжение вручается команде, которая первой найдет место.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>найти клад.</li>
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
</div>', null, 0, null, 'earth', '<div class="poem">
    <p>
        Я хожу через скалы и землю
        словно это живая вода,
        простофилям с небес я не внемлю
        моей мощи боялись всегда.
    </p>
    <p>
        Эти плечи как горные цепи,
        и с дубину размером кулак
        и всегда я доподлинно знаю,
        если где-то скрывается враг.
    </p>
    <p>
        Я подобен холму или камню,
        слышу каждый несущийся звук,
        поднимаюсь пред ним как преграда
        отражая его силой рук.
    </p>
    <p>
        Я стихия, я сила природы
        и древнее, чем старый грааль
        имя Терра мне!  долгие годы
        меня звали Элементаль.
    </p>
</div>');
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (5, 1, 'ACTION', 'В яблочко', 344, 523, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>напротив каждой команды своя мишень;</li>
            <li>у команды есть по 15 бросков;</li>
            <li>за каждое попадание команде начисляются очки: 50 - центр, 25 - внутренний круг и 10 - внешний круг.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>кинуть топор и воткнуть его в мишень;</li>
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
</div>', null, 0, null, 'war', null);
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (6, 1, 'ACTION', 'Освежись', 374, 557, '<div class="my-3">
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
</div>', null, 0, null, 'water', '<div class="poem">
    <p>
        Я - тихая радость Шалассы
        и мой безмятежен покой,
        я не принимаю отказы -
        сегодня вы вступите в бой.
    </p>
    <p>
        Готовьте свои "арбалеты"
        и стрелы из яркой воды,
        раскрасьте своих оппонентов
        цветные оставив следы.
    </p>
    <p>
        Зовут меня Элементалем -
        наездником дикой волны,
        и раз уж мы здесь повстречались
        погрузимся в море игры!
    </p>
</div>
');
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (7, 1, 'ACTION', 'Смотри, что пьёшь!', 392, 515, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <span>стаканы пронумерованы и стоят на горизонтальной плоскости;</span>
    <span>в конкурсе 10 раундов (по количеству напитков);</span><span>на один раунд выделяется по одному человеку от команды;</span>
    <span>каждый доброволец пробует напиток;</span>
    <span>новый раунд - следующий напиток.</span>
</div>
<div class="my-3">
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
</div>', null, 0, null, 'wine', null);
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (8, 1, 'ACTION', 'Это шок', 160, 264, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>по одному участнику от команды;</li>
            <li>учатник надевает шокер-браслет;</li>
            <li>в ходе конкурса задаются вопросы по божественной теме;</li>
            <li>озвучивается вопрос, если участник знает ответ, он должен поднять руку;</li>
            <li>если ответ неправильный - участника бьет током;</li>
            <li>далее возможность ответить получает участник другой команды, если ответ неправильный - его бьет током;</li>
            <li>на каждый вопрос по две попытки на ответ, если один из участников дал правильный ответ, то второй не получает баллов за ответ.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>быстро и правильно отвечать на вопросы.</li>
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
</div>', null, 0, null, 'shock', null);
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (9, 1, 'ACTION', 'Остренькое', 364, 492, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <span>определить двоих участников от команды.</span>
</div>
<div class="my-3">
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
</div>', null, 0, 4, 'trick', null);
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (10, 1, 'ACTION', 'Лучше не шуми', 39, 524, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>по одному добровольцу от команды;</li>
            <li>добровольцу завязывают глаза и дают в руки веник;</li>
            <li>члены команды соперника встают вокруг противника;</li>
            <li>у каждого из них на одну ногу привязано по пищащей курице.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
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
</div>', null, 0, null, 'music', null);
INSERT INTO Task (id, eventId, type, name, mapX, mapY, description, answer, completed, parentTaskId, image, preview) VALUES (11, 1, 'ACTION', 'Кто тут красавчик', 384, 463, '<div class="my-3">
    <span class="font-bold">Условия:</span>
    <div>
        <ul>
            <li>быть в клевом костюме или просто быть клевым.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Задача:</span>
    <div>
        <ul>
            <li>эффектно пройтись под музыку по импровизированному подиуму.</li>
        </ul>
    </div>
</div>
<div class="my-3">
    <span class="font-bold">Снаряжение:</span>
    <span>костюм, харизма, музыка.</span>
</div>
<div class="my-3">
    <span class="font-bold">Критерий, по которому определяется победитель:</span>
    <span>побеждает команда, где больше всего людей в костюмах, а также где больше участников прошлось по подиуму.</span>
</div>', null, 0, null, 'beauty', null);

COMMIT;