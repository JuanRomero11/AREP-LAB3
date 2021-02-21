# AREP-LAB3


# TALLER CLIENTES Y SERVICIOS
# Profesor: Luis Daniel Benavides
# AREP - Arquitecturas Empresariales 2021-1

En este proyecto mvn puede encontrar dos retos cumplidos propuestos en la materia como lo fue desarrolloar un servidor web que resiva mutiples solicitudes si hacer uso de ningun framework como miniSpark o spring, ademas este proyecto estara conectado a una base de datos postgresql desplegada en Heroku en la cual obtendremos los datos de la tabla "carro" y se maparean estos datos en un html y posteriormente buscar este y ver los datos de la tabla, cabe recalcar que aqui tambien podra encontrar varias imagenes de ese tema de carros antiguos y nuevos, esta tematica se trato para darle hacer un poco mas interesante, tambien podra encontrar un documento de latex en el cual hay una explicacion detallada de todo el diseño.

A continuacion se presentan los dos que se solucionaron:

RETO 1
Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor retornar todos los archivos solicitados, incluyendo páginas html e imágenes. Construya un sitio web con javascript para probar su servidor. Despliegue su solución en Heroku. NO use frameworks web como Spark o Spring use solo Java y las librerías para manejo de la red.

RETO 2 (AVANZADO)
Usando su  servidor y java (NO use frameworks web como Spark o Spring). Escriba un framework similar a Spark que le permita publicar servicios web "get" con funciones lambda y le permita acceder a recursoso estáticos como páginas, javascripts, imágenes, y CSSs. Cree una aplicación que conecte con una base de datos desde el servidor para probar su solución. Despliegue su solución en Heroku.
 
## Prerrequisitos

Como primera medida se debera intalar el JDK y maven y si es necesario un editor como ECLIPSE,NETBEANS,etc. Si solo se quiere ejecutar el proyecto de manera local solo se necesitara el JDK y maven. Para el JDK JAVA se instala la versión 8, a continuacion se adjuntara el link donde muestran las instrucciones detalladas de como descargar este y ademas en esta mismo link se podra realizar la descarga y futura instalacion, de igual forma se adjunta el link de la pagina oficial de maven en el cual esta el instructivo detallado y los paquetes necesarios para la version del sistema operativo en el cual se ejecutara, y por ultimo es recomdable descargar la aplicacion git tambien se anexara el link desde la pagina oficial, se anexan los links debido a que se especifican mas en cada uno con respecto a cada sistema operativo y no uno en especifico.

### Instalacion

Descargar JDK e instrucciones de instalacion
https://www.oracle.com/technetwork/es/java/javase/downloads/index.html
Descargar maven e instrucciones de instalacion
https://maven.apache.org/download.cgi
Descargar Git e instrucciones de instalacion
https://git-scm.com/downloads

## Construccion
Este proyecto basicamente esta construido en maven y el editor que se utilizo fue NetBeans, teniendo el JDK antes mencionado, especificamente se utilizo el lenguaje Java, con el mini framework spark y el despliegue se realizo en heroku.

## Diseño
Diagramas del diseño

![alt text](https://github.com/JuanRomero11/AREP-LAB3/blob/main/images/principalArep.PNG)

![alt text](https://github.com/JuanRomero11/AREP-LAB3/blob/main/images/model.PNG)

![alt text](https://github.com/JuanRomero11/AREP-LAB3/blob/main/images/Persistence.PNG)

![alt text](https://github.com/JuanRomero11/AREP-LAB3/blob/main/images/persitenceImpl.PNG)


## Descripcion del diseño
Esta se encuentra en el documento latex aqui se encuentra con extension pdf, es el unico archivo con esta extension en este repositorio.

## Como usar el Proyecto
En este mismo repositorio puede clonar o descargar el proyecto a traves de la aplicacion git ya instalada en su computador. Recomendacion, Si se clona utilizar el comando :

        git clone https://github.com/JuanRomero11/AREP-LAB3.git
        
Despues de que el proyecto esta clonado se accede a la consola del computador en este caso accedemos a la terminal de comandos de Windows(cmd) y entramos directamente en la carpeta en donde esta nuestro proyecto y como primer paso compilamos con el comando 

        mvn package

      
## Despliegue

[![Deployed to Heroku](https://www.herokucdn.com/deploy/button.png)](https://immense-brook-58674.herokuapp.com/index.html)

## Integracion continua

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://app.circleci.com/pipelines/github/JuanRomero11/AREP-LAB3)

## Ejecución de Pruebas

Para ejecutar las pruebas se pueden utilizar dos comandos:

          mvn package
          
          mvn test
          
Despues de ello se ejecutan todas las pruebas realizadas en la aplicacion

resultados de las pruebas realizando directamente en la pagina desplegada:

![alt text](https://github.com/JuanRomero11/AREP-LAB3/blob/main/images/caminoneta.PNG)
![alt text](https://github.com/JuanRomero11/AREP-LAB3/blob/main/images/charger.PNG)

consulta en la base de datos fue efectiva :
https://immense-brook-58674.herokuapp.com/DescripcionCarros.html

## Autor
Juan Guillermo Romero 
## License
Este codigo tiene una licencia Apache License 2.0 la cual se detalla en https://github.com/JuanRomero11/AREP-LAB3/blob/main/LICENSE
