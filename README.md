# Hardware Spot

> Um app para você encontrar aquele hardware que falta no seu setup.

![GitHub watchers](https://img.shields.io/github/watchers/SergioFelzener/TSIPi4senac?color=%234C668D&logoColor=%234C668D&style=social)
![GitHub contributors](https://img.shields.io/github/contributors/SergioFelzener/TSIPi4senac?color=%234C668D&logoColor=%234C668D&style=social)

<p align="center">
   <img src=".github/app.svg" width="100%" height="auto"/>
</p>

### Sobre :question:

Projeto desenvolvido para a matéria do 4 semestre, Projeto Integrador, do curso de Técnologia em Sistemas para Internet.

### Tecnologias utilizadas :computer:

- [Laravel](https://laravel.com/)
- [Kotlin](https://kotlinlang.org/)

### Rodando o projeto :running:

Clone o repositório:

```

    git clone https://github.com/SergioFelzener/TSIPi4senac.git

```

#### Configurando o back-end:

Abra o projeto HardwareSpot

```

    cd HardwareSpot


```

Instale todas as depêndencias

```

    composer install

    npm install


```

Criei o seu banco de dados MySQL

```

    CREATE DATABASE Hardware;


```

Faça a configuração de seu arquivo .env

```

    DB_CONNECTION=mysql
    DB_HOST=127.0.0.1
    DB_PORT=3306
    DB_DATABASE=hardware
    DB_USERNAME=<USERNAME>
    DB_PASSWORD=<PASSWORD>

```

Rode todas as migrations

```

    php artisan migrate

```

Faça o link do storage

```

    php artisan storage:link

```

Gere a chave de sua aplicação (.env)

```

    php artisan generate:key

```

Inicie o servidor

```

    php artisan serve

```

Aplicação rodando em http://localhost:8000

#### Rodando o app:

Abra o projeto Mobile e iniciei em um emulador ou em seu próprio celular

### Issues :bug:

Caso encontre algum problema, ou a necessidade de uma funcionalidade, criei uma issue ou envie um PR. :smile:
