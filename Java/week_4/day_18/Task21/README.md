Hosted on Heroku
Webpage: task21.herokuapp.com
Dump of PostgreSQL db is located in Task21/latest.dump


GETS

task21.herokuapp.com/users
task21.herokuapp.com/classes
task21.herokuapp.com/characters

task21.herokuapp.com/user/{id}
task21.herokuapp.com/classe/{id}
task21.herokuapp.com/character/{class_name}


POST can be done with form-data in Postman

Examples:

- Adding an user:
POST: task21.herokuapp.com/user

       KEY             VALUE
    username&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        1337hacker<br />
    password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        pass123word<br />


- Adding a class:
POST: task21.herokuapp.com/class

       KEY             VALUE
    class_name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        Rogue<br />
     ability&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;          Stealth<br />


- Adding a character:
POST: task21.herokuapp.com/character

       KEY               VALUE
     username&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           1337hacker <br />
    character_name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      hackz1337<br />
      level&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                10<br />
    class_name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            Rogue<br />



