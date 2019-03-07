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
    username        1337hacker
    password        pass123word


- Adding a class:
POST: task21.herokuapp.com/class

       KEY             VALUE
    class_name         Rogue
     ability          Stealth


- Adding a character:
POST: task21.herokuapp.com/character

       key               value
     username          1337hacker
  character_name        hackz1337
      level                10
    class_name           Rogue



