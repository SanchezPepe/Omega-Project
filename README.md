# Omega-Project

Chirruper es un servicio de microblogging y una red social. Chirruper permite enviar mensajes de texto plano de longitud corta llamados chirrups. Los usuarios pueden suscribirse para recibir chirrups de otros usuarios – a esto se le llama "seguir". Por defecto, los mensajes de un usuario son recibidos únicamente por sus seguidores. Los usuarios pueden Chirrupear desde una página web así como también consultar los chirrups de los contactos a quienes siguen.

La diferencia principal de Chirruper con respecto a su más cercano competidor (es decir, Twitter) es que Chirruper no almacena un histórico de los chirrups (o mensajes). En Chirruper, una vez que un Chirrupero (es decir, un usuario) lee los mensajes, estos son borrados del servidor. De igual manera, cada vez que un usuario manda un chirrup (o mensaje), este no se almacena en la cuenta del emisor. Con esto Chirruper busca posicionarse con una herramienta que protege la privacidad de sus usuarios.

## Lista de requerimientos:

- Debe tener un registro de nuevos usuarios.

- Debe tener un login para los usuarios registrados.

- Debe manejar sesiones (implementadas con Cookies o HTTP Sessions).

- No se debe permitir que una persona que no hayan iniciado sesión vea páginas o información que no le corresponde. Si hay un intento de acceso no autorizado a una página, se debe redireccionar a la página principal de la aplicación web.

- Cada usuario debe tener una lista de los usuarios a los que está siguiendo y la debe poder consultar.

- El usuario debe poder seguir/añadir a otros usuarios, es decir, suscribirse para recibir chirrups de esos usuarios.

- Con el fin de seguir a un Chirrupero, un usuario debe poder hacer búsquedas de otros usuarios. Las búsquedas serán por (sub)cadena de texto exactas. Si yo busco con la palabra clave “ana”, los resultados de la búsqueda serán un conjunto de registros que contenga la subcadena “ana”, por ejemplo “Mariana” o “Ana Laura”. Una vez desplegados los usuarios de la búsqueda, el usuario que realizó la búsqueda puede escribir/indicar el identificador exacto del usuario a seguir.

- El usuario debe poder borrar a los usuarios que sigue, y así, no recibir más sus mensajes.

- Los usuarios solo reciben mensajes de los usuarios que siguen.

- Los usuarios deben tener una página donde puedan consultar todos los mensajes que emitieron todos los contactos a los que siguen. La página se puede o no actualizar automáticamente. Los mensajes pueden estar o no ordenados.

- Los mensajes desplegados deben mostrar el contenido del mensaje, el emisor y la fecha y hora del mensaje.

- Todo usuario registrado es elegible a ser seguido.

- Todo usuario puede enviar mensajes a sus seguidores.

- Un usuario puede ver quiénes son sus seguidores.

- En caso de que existan múltiples páginas/opciones, debe haber un menú implementado con botones o links. Si es una aplicación web de una sola página, este requerimiento no aplica.

## Otros requerimientos

 - Hacer uso de las siguientes tecnologías: Topics o Queues, Clientes web (HTML, JSP, Servlets), y de forma opcional, se puede utilizar Javascript o algún framework de JavaScript (pero solo en el frontend).

 - Dado que la información de los usuarios y sus contactos se asume permanente, pueden usar archivos o bases de datos.
