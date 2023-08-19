CREATE TABLE IF NOT EXISTS content(
    id INTEGER AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    descr text,
    statuses varchar(20) NOT NULL,
    content_type varchar(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url varchar(255),
    primary key(id)
);

INSERT INTO content(title,descr,statuses,content_type,date_created)
VALUES ('My blog post','This is my first blog post','IDEA','ARTICLE',CURRENT_TIMESTAMP);