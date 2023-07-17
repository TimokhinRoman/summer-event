CREATE TABLE Event
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255)                                    NOT NULL,
    description MEDIUMTEXT                                      NOT NULL,
    status      ENUM ('CREATED', 'PENDING', 'STARTED', 'ENDED') NOT NULL DEFAULT 'CREATED'
);

CREATE TABLE ActiveEvent
(
    active  ENUM ('ACTIVE') NOT NULL PRIMARY KEY DEFAULT 'ACTIVE',
    eventId INT             NOT NULL,

    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE
);

CREATE TABLE Task
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    eventId      INT                         NOT NULL,
    type         ENUM ('ACTION', 'QUESTION') NOT NULL,
    name         VARCHAR(255)                NOT NULL,
    description  MEDIUMTEXT                           DEFAULT NULL,
    answer       VARCHAR(255)                         DEFAULT NULL,
    mapX         INT                         NOT NULL,
    mapY         INT                         NOT NULL,
    completed    BOOLEAN                     NOT NULL DEFAULT 0,
    parentTaskId INT                                  DEFAULT NULL,

    INDEX (eventId),
    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE,
    FOREIGN KEY (parentTaskId) REFERENCES Task (id)
);

CREATE TABLE TaskSelected
(
    eventId INT NOT NULL,
    taskId  INT NOT NULL,

    UNIQUE (eventId),

    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE,
    FOREIGN KEY (taskId) REFERENCES Task (id) ON DELETE CASCADE
);

CREATE TABLE User
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    `admin`  BOOLEAN      NOT NULL DEFAULT FALSE,

    UNIQUE (email)
);

CREATE TABLE Team
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(255) NOT NULL,
    userId INT          NOT NULL,

    UNIQUE (name),
    FOREIGN KEY (userId) REFERENCES User (id)
);

CREATE TABLE EventTeamUser
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    eventId INT NOT NULL,
    teamId  INT NOT NULL,
    userId  INT NOT NULL,

    UNIQUE (eventId, userId),

    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE,
    FOREIGN KEY (teamId) REFERENCES Team (id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES User (id) ON DELETE CASCADE
);

CREATE TABLE EventTeamChooser
(
    eventId INT NOT NULL,
    teamId  INT NOT NULL,

    UNIQUE (eventId),

    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE,
    FOREIGN KEY (teamId) REFERENCES Team (id) ON DELETE CASCADE
);