DUNGEONEERS

Dungeoneers es una aplicación java que maneja una base de datos que contiene
información sobre aventureros y sus grupos, mazmorras e ítems. Adicionalmente,
permite crear entradas de inventario para los aventureros y resultados
automatizados de misiones.

La aplicación tiene un menú que permite al usuario navegar entre las
siguientes opciones:
1. Character management permite al usuario realizar las siguientes operaciones:
    - Crear aventureros
    - Encontrar aventureros por su nombre
    - Actualizar datos de aventureros
    - Eliminar aventureros
    - Listar todos los aventureros de la base de datos
    - Encontrar al aventurero más fuerte
    - Listar aventureros según su clase
    - Listar aventureros según su ascendencia
2. Item management permite al usuario realizar las siguientes operaciones:
    - Crear ítems
    - Encontrar ítems por su nombre
    - Encontrar el ítem más caro
    - Actualizar datos de ítems
    - Eliminar ítems
    - Listar todos los ítems de la base de datos
    - Listar ítems según su tipo
    - Listar ítems según su rareza
3. Dungeon management permite al usuario realizar las siguientes operaciones:
    - Crear mazmorras
    - Encontrar mazmorras por su nombre
    - Actualizar datos de mazmorras
    - Eliminar mazmorras
    - Listar todas las mazmorras de la base de datos
    - Encontrar la mazmorra más difícil
    - Listar mazmorras según su bioma
    - Listar mazmorras según su dificultad
4. Party management permite al usuario realizar las siguientes operaciones:
    - Crear grupos de aventureros usando aventureros existentes
    - Encontrar grupos de aventureros por su nombre
    - Actualizar datos de grupos de aventureros
    - Eliminar grupos de aventureros
    - Listar todos los grupos de aventureros de la base de datos
    - Encontrar el grupo de aventureros más poderoso
    - Listar grupos de aventureros que contengan un aventurero
    - Listar los aventureros de un grupo
5. Inventory management permite al usuario realizar las siguientes operaciones:
    - Crear entradas de inventario para aventureros
    - Encontrar entradas de inventario por el slot de inventario
    - Listar el inventario de un aventurero
    - Listar el equipo de un aventurero
    - Listar los consumibles de un aventurero
    - Actualizar datos de entradas de inventario
    - Eliminar entradas de inventario
6. Quest management permite al usuario realizar las siguientes operaciones:
    - Resolver automáticamente el resultado de una misión
    - Encontrar misiones por su ID
    - Listar todas las misiones
    - Listar misiones realizadas por un grupo de aventureros
    - Listar misiones realizadas en una mazmorra
    - Listar misiones con éxito
    - Listar misiones fallidas
    - Actualizar datos de misiones
    - Recibir datos concretos de misiones

Instrucciones de uso:
Simplemente ejecute el archivo Main.java y siga las indicaciones sobre qué debe
introducir en la consola para navegar por las opciones o para introducir los datos
que desee. La aplicación filtrará los datos adecuadamente para asegurarse de que
no se produzca ningún error y manejará la base de datos por detrás. 
Por lo general, es recomendable que antes de tratar de realizar una consulta en
la que se necesite un nombre o ID se haya consultado previamente la lista de
elementos para no tener que repetir la consulta cuando no sepa qué introducir.
Asegúrese de que está usando una base de datos MariaDB y que ha introducido los datos 
de conexión correctos al crear la base de datos dungeoneers (estos datos se
encuentran en el archivo application.properties). El programa se encargará de crear
las tablas necesarias en la base de datos y cargará los datos de prueba en ellas
la primera vez que se ejecute.

La documentación generada por javadoc se encuentra en el fichero con el mismo
nombre. Para visualizar la API, abra el fichero index.html en un navegador web.
Disculpe de antemano por la mala implementación de los caracteres especiales.

Se ha elegido usar la librería Lombok para reducir la cantidad de código
necesario en las clases modelo, ya que el uso de las anotaciones simplifica
la creación de constructores, getters, setters, equals, hashcode y toString.
Aun así, en algunos casos es necesaria la implementación de métodos personalizados, 
como en el caso de la clase Character, que necesita un método para generar o corregir
las estadísticas del aventurero, o en la clase Party, que necesita un método
adicional para calcular la fuerza del grupo.

Nótese que el sistema de actualización de mazmorras no funciona.
No hemos logrado identificar la causa.