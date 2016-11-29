
INSERT INTO public.the_user (id, display_name, password, username) VALUES (-1, 'Nate Ellsworth', 'password', 'nate');



INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-1, 0, 0.0025, 0, '2016-10-03', 5000, 'EB to US Treasury Dept', 126000, 9900, 0.25, '3 Months', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-2, 5000, 0, 5000, '2016-10-03', 0, 'EB from Checking #2694317712', 0, 0, 0, '-', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-3, 0, 0.005, 0, '2016-08-15', 10000, 'EB to US Treasury Dept', 121000, 10700, 0.54, '6 Months', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-4, 10000, 0, 10000, '2016-08-15', 0, 'EB from Checking #2694317712', 0, 0, 0, '-', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-5, 0, 0.001, 0, '2016-07-04', 1000, 'EB to US Treasury Dept', 111000, 33513.44, 0.28, '1 Month', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-6, 0, 0.001, 0, '2016-07-04', 1000, 'EB to US Treasury Dept', 110000, 33513.44, 0.28, '1 Month', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-7, 0, 0.001, 0, '2016-07-04', 1000, 'EB to US Treasury Dept', 109000, 33513.44, 0.28, '1 Month', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-8, 0, 0.001, 0, '2016-07-04', 1000, 'EB to US Treasury Dept', 108000, 33513.44, 0.28, '1 Month', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-9, 4000, 0, 4000, '2016-07-04', 0, 'EB from Checking #2694317712', 0, 0, 0, '-', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-10, 0, 0.0025, 0, '2016-06-07', 1000, 'EB to US Treasury Dept', 107000, 10300, 0.26, '3 Months', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-11, 0, 0.0025, 0, '2016-06-07', 1000, 'EB to US Treasury Dept', 106000, 10300, 0.26, '3 Months', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-12, 0, 0.005, 0, '2016-06-07', 5000, 'EB to US Treasury Dept', 105000, 11100, 0.56, '6 Months', 'Savings', 'Transfer');
INSERT INTO public.account (id, available_balance, bank_rate, credit, date, debit, description, posted_balance, rate_difference, t_bill_rate, term, title, type) VALUES (-13, 7000, 0, 7000, '2016-06-07', 0, 'EB from Checking #2694317712', 0, 0, 0, '-', 'Savings', 'Transfer');


INSERT INTO public.permission (id, description, key) VALUES (-1, 'Admin User (ability to add other users)', 'ADMIN_ADD_USER');
INSERT INTO public.permission (id, description, key) VALUES (-2, 'Allow Treasury Bill Purchases', 'USER_BUY_TBILLS');
INSERT INTO public.permission (id, description, key) VALUES (-3, 'Allow User to Edit Transactions', 'USER_EDIT_TRANSACTIONS');


INSERT INTO public.the_user_abilities (the_user_id, abilities_id) VALUES (-1, -1);
INSERT INTO public.the_user_abilities (the_user_id, abilities_id) VALUES (-1, -2);
INSERT INTO public.the_user_abilities (the_user_id, abilities_id) VALUES (-1, -3);


