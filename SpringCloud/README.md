## Nombre proyecto Maven
**block16-spring-cloud**

Primero se creará una aplicación backend con dos entidades:
<br>**Cliente.**<br>
Con las variables:
Id, nombre, apellido, edad, email y teléfono.

Los endpoints serán un crud básico, crear borrar, actualizar y buscar tanto por id como obtener todos los clientes.

**Viaje (Es un autobús de 40 plazas).**<br>
Con las variables:  
Id, origin, destination, departureDate, arrivalDate,  passenger y status.

Los endpoints serán los siguientes:     
Crud básico para cada viaje.
- Añadir pasajero a viaje. Usaremos el id de cada uno, se verá de la siguiente forma:
  - localhost:8080/trip/addPassenger/4/8

            
- Haremos una cuenta de pasajeros que hay en cada viaje, ya que al añadir un pasajero a cada viaje deberá de limitarse a la cuenta de 40 pasajeros.
  - localhost:8080/passenger/count/{tripId}
  

- Un endpoint para cambiar la disponibilidad del viaje ya que es posible que el autobús se averíe.
  - localhost:8080/trip/{tripId}/{tripStatus}


- Finalmente crearemos un endpoint que nos indique la disponibilidad del viaje.
    - localhost:8080/trip/verify/{tripId}

Hemos de tener en cuenta que ambas entidades están conectadas a una base de datos.  
Segundo haremos la aplicación backend-Front que constará de la siguiente entidad:


**Ticket. ( Se guarda en base de datos diferente a la de viajes o clientes)** 

Con las variables:  
id, passengerId, passengerName, passenger Lastname, passgener Email, tripOrigin, tripDestination, departureDate y arrivalDate.  
Tendremos que crear dos entidades a parte exactamente iguales que las del backend pero sin acceso a la base de datos, ya que con RestTemplate recogeremos variables de tipo cliente y viaje.    



Como es un caso de práctica resumimos los endpoint a uno solo, que será el que cree el ticket, obteniendo el pasajero a través del id y añadiendolo al viaje.
- localhost:8080/generateTicket/{userId}/{tripId}

Para esta parte necesitaremos conocer el uso de RestTemplate o Feign, que hará uso de la aplicación backend ejecutándose de forma simultánea a esta.

Para esta aplicación se podrá usar la misma base de datos u otra diferente (si es así mejor, se entenderá mejor por que los microservicios son tan usados).

Tercero crearemos la aplicación eureka-naming-server.   
Como has visto en la teoría poco hay que explicar en la hora de su desarrollo.  
Cuarto crearemos la aplicación api-gateway, que nos abrirá los puertos y permitirá relacionar nuestros microservicios con eureka.   

Finalmente dockeriza esta aplicación, respetando como dependen de las bases de datos y entre sí, es recomendable utilizar docker-compose.
