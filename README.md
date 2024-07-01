# Job Portal Backend Application

[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)

Java Backend Project Using Spring Boot

## Demo API URL

Check Postman Link Below for API Documentation

### Admin Routes 
```
https://api.jobs.flyingdesk.be/admin/v1
```

### User Routes
```
https://api.jobs.flyingdesk.be/v1
```


## Entity Relationship Diagram - v1

![ERD](/docs/diagrams/ERD_v1.png)

## Technologies used

* [Spring Boot](https://start.spring.io/) - Framework Java (back-end)
* [IntelliJ Idea Ultimate](https://www.jetbrains.com/idea/) - Integrated Development Environment
* [Jira](https://www.atlassian.com/fr/software/jira) - Kanban Board
* [Teams](https://www.microsoft.com/fr-be/microsoft-teams/group-chat-software) - Meetings, Daily Scrums
* [Lucidchart](https://www.lucidchart.com/) - Sketching, Diagrams

## Links

* [Postman](https://red-flare-210812.postman.co/workspace/Team-Workspace~fd077c8c-607a-44d8-8194-1144e17e2de4/collection/34945946-9ddb3f05-8812-46d8-8ff1-211624cdeb89)
* [GitHub Repo](https://github.com/faisalhotak/tftic-labo-back)
* [Our Jira Kanban Board](https://faisalhotak.atlassian.net/jira/software/projects/TFTIC/boards/1?atlOrigin=eyJpIjoiZjMxNmE3NmY4YTA0NDAwNjg1OGI5ZWUxZWNjOTkwNGYiLCJwIjoiaiJ9)
* [Teams Group](https://teams.microsoft.com/l/team/19%3ASJQYdSXeaU0iEI-nQ3D-I10jVeCOKtG5zrIUmlqIB7k1%40thread.tacv2/conversations?groupId=5db116f2-8545-4714-8730-1dbf2ae12098&tenantId=9c523e69-1868-4f28-826a-993ddf8f33a8)
* [Lucidchart DB Scheme](https://lucid.app/lucidchart/dd9fbb79-3f62-4566-b903-0dd6a7f0f424/edit?invitationId=inv_de88d91d-03a9-4988-8682-ec63c181dd3b)

## Contributors

* **Kevin JOEGHMANS** [@kevinjoeghmans](https://github.com/keivy-git)
* **Faisal HOTAK** [@faisalhotak](https://github.com/faisalhotak)
* **Nathan SANCKE** [@nathansancke](https://github.com/Lopidurs)
* **Tomas MENDES** [@tomasmendes](https://github.com/tomashm9)

## How to run the project

### Add environment variables in your IDE

⚠️ <b><u>Make sure to update the variables according to your setup</u></b> ⚠️

In IntelliJ IDEA, you can add environment variables by following these steps:

1. Go to `Run` > `Edit Configurations...`
2. Click on the `Environment Variables` field
3. If you don't have any environment variables yet, click on the `Modify options` button
4. You will see under `Operating System` -> `Environment Variables` click on it
5. <b>Copy-paste</b> the environment variables there (and possibly update them according to your setup)

Copy-paste the following environment variables in your IDE:

```
### Server Configuration ###
SERVER_PORT=8080

### Database Configuration ###
DATABASE_URL=jdbc:postgresql://localhost:5432/job_portal
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=12345
DATABASE_DRIVER=org.postgresql.Driver

# This will every time drop and create the database schema
DATABASE_DDL_AUTO=create

DATABASE_SHOW_SQL=true
DATABASE_FORMAT_SQL=true

### JWT Configuration ###
JWT_ALGORITHM=HmacSHA256

# Secret key for JWT that needs to be more than 256 bits (meaning 32 characters or more)
JWT_SECRET=TFTIC_LABO_SECRET_KEY_1234567890

# 24 hours expiration time for JWT in milliseconds
JWT_EXPIRE_AT=86400000

# Front End Cors
ANGULAR_ALLOWED_ORIGINS=http://localhost:4200
```
