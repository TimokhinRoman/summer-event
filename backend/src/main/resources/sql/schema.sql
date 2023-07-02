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
    id          INT PRIMARY KEY AUTO_INCREMENT,
    eventId     INT                         NOT NULL,
    type        ENUM ('ACTION', 'QUESTION') NOT NULL,
    name        VARCHAR(255)                NOT NULL,
    description MEDIUMTEXT   DEFAULT NULL,
    answer      VARCHAR(255) DEFAULT NULL,

    INDEX (eventId),
    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE
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
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(255) NOT NULL,
    owner INT          NOT NULL,

    UNIQUE (name),
    FOREIGN KEY (owner) REFERENCES User (id)
);

CREATE TABLE EventTeamUser
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    event INT NOT NULL,
    team  INT NOT NULL,
    user  INT NOT NULL,

    UNIQUE (event, user),

    FOREIGN KEY (event) REFERENCES Event (id),
    FOREIGN KEY (team) REFERENCES Team (id),
    FOREIGN KEY (user) REFERENCES User (id)
);

CREATE TABLE EventTeamChooser
(
    event INT NOT NULL,
    team  INT NOT NULL,

    UNIQUE (event),

    FOREIGN KEY (event) REFERENCES Event (id),
    FOREIGN KEY (team) REFERENCES Team (id)
);