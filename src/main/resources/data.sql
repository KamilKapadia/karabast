-- This script will get executed each time the applications starts (good for testing purposes)

-- Constant tables
DROP TABLE status_code;
DROP TABLE type_code;
DROP TABLE rule_code;
DROP TABLE action_code;

-- The setup tables
DROP TABLE job;
DROP TABLE historical_name;
DROP TABLE content_path;
DROP TABLE rule;
DROP TABLE action;

-- The result tables
DROP TABLE result;
DROP TABLE content;
DROP TABLE content_result;
DROP TABLE last_run;
DROP TABLE historical_data;
DROP TABLE rule_result;



-- Constant Tables
CREATE TABLE status_code (
	id INT PRIMARY KEY NOT NULL, 
	name VARCHAR(30) NOT NULL);

insert into status_code(id, name) values (1, 'UNKNOWN');
insert into status_code(id, name) values (2, 'RUNNING CORRECTLY');
insert into status_code(id, name) values (4, 'SLOWER');
insert into status_code(id, name) values (8, 'WARNING');
insert into status_code(id, name) values (16, 'STALLED');
insert into status_code(id, name) values (32, 'DOWN');

CREATE TABLE type_code (
	id INT PRIMARY KEY NOT NULL, 
	name VARCHAR(15) NOT NULL);
	
insert into type_code(id, name) values (1, 'INTEGER');
insert into type_code(id, name) values (2, 'DECIMAL');
insert into type_code(id, name) values (4, 'STRING');
insert into type_code(id, name) values (8, 'BOOLEAN');

CREATE TABLE rule_code (
	id INT PRIMARY KEY NOT NULL, 
	type_mask INT NOT NULL, 
	name VARCHAR(45) NOT NULL);

insert into rule_code(id, type_mask, name) values (1, 13, 'EQUALS');
insert into rule_code(id, type_mask, name) values (2, 13, 'NOT EQUALS');
insert into rule_code(id, type_mask, name) values (3, 7, 'BETWEEN');
insert into rule_code(id, type_mask, name) values (4, 7, 'NOT BETWEEN');
insert into rule_code(id, type_mask, name) values (5, 7, 'GREATER THAN');
insert into rule_code(id, type_mask, name) values (6, 7, 'LESS THAN');
insert into rule_code(id, type_mask, name) values (7, 5, 'GREATER THAN OR EQUALS');
insert into rule_code(id, type_mask, name) values (8, 5, 'LESS THAN OR EQUALS');
insert into rule_code(id, type_mask, name) values (9, 5, 'IS IN');
insert into rule_code(id, type_mask, name) values (10, 5, 'IS NOT IN');

CREATE TABLE action_code (
	id INT PRIMARY KEY NOT NULL,
	name VARCHAR(120) NOT NULL);
	
insert into action_code(id, name) values(1, 'log');
insert into action_code(id, name) values(2, 'database');
insert into action_code(id, name) values(4, 'sms');
insert into action_code(id, name) values(8, 'email');
insert into action_code(id, name) values(16, 'rss');
insert into action_code(id, name) values(32, 'atom');


-- The setup tables
CREATE TABLE job (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	name VARCHAR(30) NOT NULL unique, 
	tier VARCHAR(20), 
	location VARCHAR(30), 
	cluster VARCHAR(30), 
	server VARCHAR(100), 
	application VARCHAR(100), 
	target_url VARCHAR(256),
    active BOOL DEFAULT true,
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historical_name (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL, 
	name VARCHAR(80) NOT NULL,
	value_path VARCHAR(256) NOT NULL,
	type_code_id INT NOT NULL,
	active BOOL DEFAULT true,
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_hn_job_id    
    FOREIGN KEY (JOB_ID) 
    REFERENCES JOB (ID) 
    ON DELETE CASCADE
);

CREATE TABLE content_path (
	id LONG PRIMARY KEY auto_increment NOT NULL, 
	job_id LONG NOT NULL,
	name_path VARCHAR(256), 
	mime_type_path VARCHAR(256),
	content_path VARCHAR(256),
	content_disk_dir VARCHAR(128),
	active BOOL DEFAULT true,
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE rule (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL, 
	status_type_code INT NOT NULL, 
	type_code_id INT NOT NULL, 
	name VARCHAR(80) NOT NULL,
	value_path VARCHAR(256) NOT NULL,
	rule_code int NOT NULL, 
	expected_value VARCHAR(256),
	active BOOL DEFAULT true,
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE action (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL,
	type_mask INT NOT NULL,
	action_mask INT NOT NULL,
	active BOOL DEFAULT true,
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);



-- The result tables
CREATE TABLE result (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL, 
	run LONG NOT NULL, 
	status_type_code INT NOT NULL, 
	exec_time DOUBLE NOT NULL, 
	TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE content (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL DEFAULT 'file.bin',
	mime_type VARCHAR(256) NOT NULL DEFAULT 'application/octet-stream',
	content_length LONG NOT NULL,
	crc32 LONG NOT NULL, 
	adler32 LONG NOT NULL, 
	md5 VARCHAR(32) NOT NULL, 
	SHA512 VARCHAR(128) NOT NULL);

CREATE TABLE content_result (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	result_id LONG NOT NULL, 
	content_id LONG NOT NULL);

CREATE TABLE last_run (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	job_id LONG NOT NULL, 
	unknown LONG, 
	running LONG, 
	SLOWER LONG, 
	WARNING LONG, 
	STALLED LONG, 
	DOWN long,
	 CONSTRAINT fk_lr_job_id
     FOREIGN KEY (job_id)
     REFERENCES job (id)
     ON DELETE CASCADE
	);

CREATE TABLE historical_data (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	historical_name_id LONG NOT NULL, 
	result_id LONG NOT NULL,
	long_value LONG,
	double_value DOUBLE,
	string_value VARCHAR(256),
	boolean_value BOOL,
	 CONSTRAINT fk_hd_result_id
     FOREIGN KEY (result_id) 
     REFERENCES RESULT (ID)
     ON DELETE CASCADE
);

CREATE TABLE rule_result (
	id LONG PRIMARY KEY auto_increment NOT NULL,
	result_id LONG NOT NULL, 
	rule_id LONG NOT NULL, 
	condition_met int NOT NULL, 
	reason VARCHAR(256) NOT NULL);

	
-- Foreign Key Constraints

--ALTER TABLE historical_name
--    ADD CONSTRAINT fk_hn_job_id    
--    FOREIGN KEY (JOB_ID) 
--    REFERENCES JOB (ID) ON DELETE CASCADE;	

ALTER TABLE historical_name
    ADD FOREIGN KEY (TYPE_CODE_ID) 
    REFERENCES TYPE_CODE (ID);
    
ALTER TABLE content_path
    ADD FOREIGN KEY (JOB_ID) 
    REFERENCES JOB (ID) ON DELETE CASCADE;	    
    
ALTER TABLE rule
    ADD FOREIGN KEY (JOB_ID) 
    REFERENCES JOB (ID) ON DELETE CASCADE;    

ALTER TABLE rule
    ADD FOREIGN KEY (STATUS_TYPE_CODE) 
    REFERENCES STATUS_CODE (ID);        
    
ALTER TABLE rule
    ADD FOREIGN KEY (TYPE_CODE_ID) 
    REFERENCES TYPE_CODE (ID);
    
ALTER TABLE rule
    ADD FOREIGN KEY (RULE_CODE) 
    REFERENCES RULE_CODE (ID);      
    
ALTER TABLE action
    ADD FOREIGN KEY (JOB_ID) 
    REFERENCES JOB (ID) ON DELETE CASCADE;        

    
ALTER TABLE result
    ADD FOREIGN KEY (JOB_ID) 
    REFERENCES JOB (ID) ON DELETE CASCADE;     

ALTER TABLE result
    ADD FOREIGN KEY (STATUS_TYPE_CODE) 
    REFERENCES STATUS_CODE (ID);
    
ALTER TABLE content_result
	ADD FOREIGN KEY (RESULT_ID)
	REFERENCES RESULT (ID) ON DELETE CASCADE;

ALTER TABLE content_result	
	ADD FOREIGN KEY (CONTENT_ID)
	REFERENCES CONTENT (ID) ON DELETE CASCADE;

--ALTER TABLE last_run
--    ADD FOREIGN KEY (JOB_ID) 
--    REFERENCES JOB (ID) ON DELETE CASCADE;     	

ALTER TABLE last_run
    ADD FOREIGN KEY (unknown) 
    REFERENCES RESULT (ID);    
    
ALTER TABLE last_run
    ADD FOREIGN KEY (RUNNING) 
    REFERENCES RESULT (ID);    

ALTER TABLE last_run
    ADD FOREIGN KEY (SLOWER) 
    REFERENCES RESULT (ID);    

ALTER TABLE last_run
    ADD FOREIGN KEY (WARNING) 
    REFERENCES RESULT (ID);    

ALTER TABLE last_run
    ADD FOREIGN KEY (STALLED) 
    REFERENCES RESULT (ID);    
    
ALTER TABLE last_run
    ADD FOREIGN KEY (DOWN) 
    REFERENCES RESULT (ID);  
    
ALTER TABLE rule_result
    ADD FOREIGN KEY (result_id) 
    REFERENCES RESULT (ID);  
    
ALTER TABLE rule_result
    ADD FOREIGN KEY (rule_id) 
    REFERENCES RULE (ID);  

--ALTER TABLE historical_data
--    ADD CONSTRAINT fk_hd_result_id
--    FOREIGN KEY (result_id) 
--    REFERENCES RESULT (ID);  
    
--ALTER TABLE historical_data
--    ADD CONSTRAINT fk_hd_hn_id    
--    FOREIGN KEY (historical_name_id) 
--    REFERENCES HISTORICAL_NAME (ID) 
--    ON DELETE CASCADE;  
    
-- Insert some test data 
    
insert into job (name, application) values ('test-job-1', 'test-app');
insert into job (name, tier, location, cluster, server, application, target_url) values ('test-job-2','DEV','SF','tests','localhost','tester','http://localhost:8080'); 

insert into historical_name (job_id, name, value_path, type_code_id) values (1,	'size',	'.$size',	1);
insert into historical_name (job_id, name, value_path, type_code_id) values (1,	'response',	'.$response',	4);
insert into historical_name (job_id, name, value_path, type_code_id) values (2,	'size',	'.$size',	1);
insert into historical_name (job_id, name, value_path, type_code_id) values (2,	'height',	'.$height',	1);
insert into historical_name (job_id, name, value_path, type_code_id) values (2,	'width',	'.$width',	1);

insert into content_path (job_id, name_path, mime_type_path, content_path, content_disk_dir) values (1, 'content', '.$mimetype', '.$content', '/opt');
insert into content_path (job_id, name_path, mime_type_path, content_path, content_disk_dir) values (1, 'header', '.$mimetype', '.$header', '/opt');
insert into content_path (job_id, name_path, mime_type_path, content_path, content_disk_dir) values (2, 'test', '.$mimetype', '.$test', '/var');

insert into rule (job_id, status_type_code, type_code_id, name, value_path, rule_code, expected_value) values (1, 2, 1, 'key', '.$key', 1, '5');
insert into rule (job_id, status_type_code, type_code_id, name, value_path, rule_code, expected_value) values (1, 2, 1, 'key2', '.$key2', 1, '5');
insert into rule (job_id, status_type_code, type_code_id, name, value_path, rule_code, expected_value) values (2, 32, 1, 'key3', '.$key3', 1, '5');

insert into action (job_id, type_mask, action_mask) values (1, 3, 1);
insert into action (job_id, type_mask, action_mask) values (2, 5, 3);

insert into result (job_id,	run, status_type_code, exec_time) values (1, 1, 1, 1.2);
insert into result (job_id,	run, status_type_code, exec_time) values (1, 2, 1, 3.2);
insert into result (job_id,	run, status_type_code, exec_time) values (2, 1, 1, 2.45);

insert into content (name, mime_type, content_length, crc32, adler32, md5, sha512) values ('test1', 'application/octet-stream', 45, 12345, 54321, 'md5zz', 'sha512zz');
insert into content (name, mime_type, content_length, crc32, adler32, md5, sha512) values ('test2', 'application/image', 99, 12346, 64321, 'md5zy', 'sha512zy');
insert into content (name, mime_type, content_length, crc32, adler32, md5, sha512) values ('test3', 'application/text', 10, 12347, 74321, 'md5zx', 'sha512zx');

insert into content_result(result_id, content_id) values (1, 1);
insert into content_result(result_id, content_id) values (2, 3);
insert into content_result(result_id, content_id) values (3, 2);
insert into content_result(result_id, content_id) values (1, 3);

insert into LAST_RUN (JOB_ID, UNKNOWN) values (1, 2);
insert into LAST_RUN (JOB_ID, UNKNOWN) values (2, 3);

insert into RULE_RESULT (result_id, rule_id, condition_met, reason) values (1, 1, true, 'test reason');
insert into RULE_RESULT (result_id, rule_id, condition_met, reason) values (1, 2, true, 'another test reason');

insert into HISTORICAL_DATA (historical_name_id, result_id, long_value) values (1, 1, 777);

-- Constant tables
select * from status_code;
select * from type_code;
select * from rule_code;
select * from action_code;

-- The setup tables
select * from job;
select * from historical_name;
select * from content_path;
select * from rule;
select * from action;

-- The result tables
select * from result;
select * from content;
select * from content_result;
select * from last_run;
select * from historical_data;
select * from rule_result;



