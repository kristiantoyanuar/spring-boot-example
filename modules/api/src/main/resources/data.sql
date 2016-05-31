insert into user_account (id, email, username, password) values
  (1, 'admin@somewhere.com', 'admin', '$2a$06$Ci6H7eaVno04rjyX8qtWaOUT38T32TIQiNz3R4QuBVL9cJxLfODRG'); -- password is 'ultrasecret'

insert into student (first_name, last_name, code) values
  ('John', 'Doe', 'S_01');

insert into api_client(client_id, client_name, is_client_secret_required, is_scoped) values
  ('ui_client', 'Default UI Client', false, false);

insert into api_client_resources(client_id, resource) values
  ('ui_client', 'USER_RESOURCE');

insert into api_client_grant_type(client_id, authorized_grant_type) values
  ('ui_client', 'client_credentials');

insert into api_client_redirect_uri (client_id, redirect_uri) values
  ('ui_client', 'https://www.getpostman.com/oauth2/callback');