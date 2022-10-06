DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS compilations CASCADE;
DROP TABLE IF EXISTS eventsofcompilation CASCADE;


CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT uq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS events (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    annotation VARCHAR(2000) NOT NULL,
    category BIGINT NOT NULL,
    confirmed_requests BIGINT,
    created_on timestamp WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(7000) NOT NULL,
    event_date timestamp WITHOUT TIME ZONE NOT NULL,
    initiator BIGINT NOT NULL,
    location BIGINT NOT NULL,
    paid boolean NOT NULL DEFAULT FALSE,
    participant_limit BIGINT NOT NULL DEFAULT 0,
    published_on timestamp WITHOUT TIME ZONE NOT NULL,
    request_moderation boolean NOT NULL DEFAULT FALSE,
    state VARCHAR(64) NOT NULL,
    title VARCHAR(120) NOT NULL,

    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS locations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    lat float,
    lon float,

    CONSTRAINT pk_loc PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(64) NOT NULL,

    CONSTRAINT pk_cat PRIMARY KEY (id),
    CONSTRAINT uq_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS requests (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created timestamp WITHOUT TIME ZONE NOT NULL,
    event BIGINT,
    requester BIGINT,
    status VARCHAR,

    CONSTRAINT pk_req PRIMARY KEY (id),
    CONSTRAINT uq_event_of_user UNIQUE(event, requester)
);

CREATE TABLE IF NOT EXISTS compilations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pinned boolean NOT NULL DEFAULT FALSE,
    title VARCHAR NOT NULL,

    CONSTRAINT pk_comp PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS event_compilation (
    compilation BIGINT NOT NULL,
    event BIGINT NOT NULL,

    CONSTRAINT pk_EvOfCom PRIMARY KEY (compilation, event)
);



ALTER TABLE events ADD FOREIGN KEY (initiator) REFERENCES users (id);

ALTER TABLE events ADD FOREIGN KEY (location) REFERENCES locations (id);

ALTER TABLE events ADD FOREIGN KEY (category) REFERENCES categories (id);

ALTER TABLE requests ADD FOREIGN KEY (event) REFERENCES events (id);

ALTER TABLE requests ADD FOREIGN KEY (requester) REFERENCES users (id);

ALTER TABLE eventsOfCompilation ADD FOREIGN KEY (compilation) REFERENCES compilations (id);

ALTER TABLE eventsOfCompilation ADD FOREIGN KEY (event) REFERENCES events (id);
