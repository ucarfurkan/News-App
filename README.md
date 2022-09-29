# News App
 
News App is a simulation of a desktop application in which you can read the news.

There is 3 user type in this application. Admin, Writer and Member.

Firstly, you will see a Sign In screen. You can Sign In here with your username and password. If you don't have an account, you can create one from the "Sign Up" button. If you sign up, your account will be a "member" type of user. Users are connected with the database in the "user" table, it will be checked from there. According to the login information you have entered, you will be directed to the screen of which user type the account is.

## If you are an Admin

If you are logged in as admin, you will see an interface with 3 different tabs, "Users", "News" and "Categories".

### Users Tab
You can see all the users here. You can add, update or delete a user. If you delete a user, every news added by that user will be deleted as well. You can search for a user with a name, username, or user type in the search panel.

### News Tab
You can see all the news added by writers here. You can only delete them. You can search for a news with a Writer ID, category, headline or text in the search panel.

### Categories Tab
You can see all the category types here. You can add or delete a category. If you delete a category, every news belong to that category will be deleted as well, and you will see a warning about it. Categories are enum, so every news must belong to one of the categories which is in here.

## If you are a Writer

If you are logged in as writer, you will see an interface with 3 different tabs, "Add News" and "My Posted News".

### Add News Tab
You can add news here. Headline, Text, and Category fields are necessary to add a news.

### My Posted News Tab
You can see all the news you posted before in here. You can update or delete them.

## If you are a Member

If you are logged in as a member or signed up for a new account, you will see only one tab. In this tab, you can see and read all the news added by writers in here with details like the writer's name and category. When you click the "Read" button available for every news, the panel in which you can read the news will be opened. You can search for news with Headline, Text, Writer ID, or Category in the search panel. 
