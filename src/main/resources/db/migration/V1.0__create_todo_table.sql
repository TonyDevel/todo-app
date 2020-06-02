CREATE TABLE todo (
    id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500) NOT NULL,
    created_at DATE NOT NULL,
    due_date DATETIME NOT NULL,
    done BOOLEAN NOT NULL,

    CONSTRAINT pk_todo PRIMARY KEY (id)
);