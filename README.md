# Fast Food Ordering System
Fast Food Ordering System is an assignment project which I'm currently working on for learning per purposes.

This is a desktop application which developed using Java and Swing for the GUI. Speciality is this system has advanced user interface and user expirience. As well as advanced data validations.

### How the system works?
I designed this system for two types of users (Manager and Cashier). Both have different permisions within the system. At the login page the system recognize the user whether a Manager or a Cashier. Only the Managers can register new users. Both users can take orders.

### Database structure
Used MySQL to create the database and normalized as much as possible. There are 10 tables. They are,
01. login (user_id (PK), username, password, type, status) //type means manager or cashier, status means active or not
02. user (user_id (FK), name, gender, dob (date of birth), phone, email, address)
03. customer (customer_id (PK), name, phone, email)
04. order (order_id (PK), date, total, recived, balance, user_id (FK), customer_id (FK))
05. meal (meal_id (PK), name, unit_price) // Casual meals
06. drink (drink_id (PK), name, unit_price) // drinks
07. dessert (desser_id (PK), name, unit_price) // desserts
08. order_has_meal (order_id (FK), meal_id (FK), qty)
09. order_has_drink (order_id (FK), drink_id (FK), qty)
10. order_has_dessert (order_id (FK), dessert_id (FK), qty)

#### The Fas Food Ordering System is still under constructions. Hope to release the final product soon.
