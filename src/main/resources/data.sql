INSERT INTO public._user (id, display_name, password, username) VALUES (-1, 'Nathaniel Ellsworth', 'password', 'nate');

INSERT INTO public.account (id, description, approved, rating, remote_url, title) VALUES (-1, 'Savings', 'yes', 'A', 'Joint Account');

INSERT INTO public.permission (id, description, key) VALUES (-1, 'Admin Users', 'ADMIN_ADD_ACCOUNT');
INSERT INTO public.permission (id, description, key) VALUES (-2, 'Grant Purchase', 'USER_REQUIREMENTS_MET');


INSERT INTO public._user_abilities (_user_id, abilities_id) VALUES (-1, -1);
