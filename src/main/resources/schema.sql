CREATE TABLE ACCOUNTS (
    ACCOUNT_ID      BIGINT PRIMARY KEY NOT NULL,
    PASSWORD        VARCHAR(255) NOT NULL,
    EMAIL           VARCHAR(255) NOT NULL,
    PHONE           VARCHAR(255) NOT NULL,
    ENABLED         CHAR(1) DEFAULT 'Y',
    CREATED_BY      VARCHAR(255) NOT NULL, 
    CREATED_DATE    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_BY      VARCHAR(255),
    UPDATED_DATE    TIMESTAMP
);

CREATE TABLE ROLES (
	ROLE_ID         BIGINT PRIMARY KEY NOT NULL,
	ROLE_NAME       VARCHAR(255) NOT NULL,
	CREATED_BY      VARCHAR(255) NOT NULL,
    CREATED_DATE    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_BY      VARCHAR(255),
    UPDATED_DATE    TIMESTAMP
);

CREATE TABLE ACCOUNTS_ROLES (
	ROLE_ID         BIGINT NOT NULL,
    ACCOUNT_ID      BIGINT NOT NULL,
    CREATED_DATE    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATE    TIMESTAMP,
    
    PRIMARY KEY (ROLE_ID, ACCOUNT_ID),
    CONSTRAINT fk_user_roles_id1 foreign key (ACCOUNT_ID) references ACCOUNTS (ACCOUNT_ID),
    CONSTRAINT fk_user_roles_id2 foreign key (ROLE_ID) references ROLES (ROLE_ID)
);

---oauth client details table
create table OAUTH_CLIENT_DETAILS (
  CLIENT_ID VARCHAR(256) PRIMARY KEY,
  RESOURCE_IDS VARCHAR(256),
  CLIENT_SECRET VARCHAR(256),
  SCOPE VARCHAR(256),
  AUTHORIZED_GRANT_TYPES VARCHAR(256),
  WEB_SERVER_REDIRECT_URI VARCHAR(256),
  AUTHORITIES VARCHAR(256),
  ACCESS_TOKEN_VALIDITY INTEGER,
  REFRESH_TOKEN_VALIDITY INTEGER,
  ADDITIONAL_INFORMATION VARCHAR(4096),
  AUTOAPPROVE VARCHAR(256)
);

--oauth client token
create table oauth_client_token (
  TOKEN_ID VARCHAR(256),
  TOKEN BYTEA,
  AUTHENTICATION_ID VARCHAR(256) PRIMARY KEY,
  USER_NAME VARCHAR(256),
  CLIENT_ID VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA
);

create table oauth_code (
  code VARCHAR(256), authentication BYTEA
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);