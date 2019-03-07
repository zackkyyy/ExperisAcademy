Examples:

- Adding an user:
POST: http://localhost:8080/user

    {
        "username": "xX_Dragon_Xx",
        "password": "password123"
    }


- Adding a character:
POST: http://localhost:8080/character

    {
        "username": "xX_Dragon_Xx",
        "character_name": "richkid",
        "level": 10,
        "className": "Hunter"
    }


- Adding a class:
POST: http://localhost:8080/class

    {
    	"name": "Hunter",
    	"ability": "Multi shot"
    }