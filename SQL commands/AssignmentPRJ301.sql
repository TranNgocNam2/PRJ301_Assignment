/*Insert into Plants(PID, PName, price, imgPath, description, status, CateID) values (1, 'Hoa lan', 1000, 'image/hoalan.jpg', 'Hoa lan', 1, 1)
select * from Plants
/*delete from Plants where PName = 'Hoa lan'*/
select * from Categories
Insert into Categories(CateName) values ('Rose')
/*delete from Categories where CateID = 5*/
DBCC CHECKIDENT (Plants, PID, 1);


	select * from OrderDetails
	/* delete from OrderDetails where DetailId = 12 */
	/*DBCC CHECKIDENT ('OrderDetails', RESEED, 0);*/
	
	/*delete from Orders where OrderID = 4*/
	select * from Orders
	/*DBCC CHECKIDENT ('Orders', RESEED, 0);*/


	select * from Plants

	Select * from Accounts*/
	select accID, email, password, fullname, phone, status, role from Accounts where fullname like  '%z%'
	Select * from Accounts