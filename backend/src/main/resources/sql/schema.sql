CREATE TABLE Event
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description MEDIUMTEXT   NOT NULL
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

CREATE TABLE ActiveEvent
(
    active  ENUM ('ACTIVE') NOT NULL PRIMARY KEY DEFAULT 'ACTIVE',
    eventId INT             NOT NULL,

    FOREIGN KEY (eventId) REFERENCES Event (id) ON DELETE CASCADE
);